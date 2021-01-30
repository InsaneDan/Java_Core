package lesson2.server.chat.handler;


import lesson2.clientserver.Command;
import lesson2.clientserver.CommandType;
import lesson2.clientserver.commands.*;
import lesson2.server.chat.MyServer;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import static lesson2.clientserver.Command.*;

public class ClientHandler {

    private final MyServer myServer;
    private final Socket clientSocket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String nickname;

    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.myServer = myServer;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        in  = new ObjectInputStream(clientSocket.getInputStream());
        out = new ObjectOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authentication();
                readMessages();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    System.err.println("Не удалось закрыть соединение");
                    logger.warning("Не удалось закрыть соединение");
                }
            }
        }).start();
    }

    private void authentication() throws IOException {

        String login = null;
        String password = null;

//TODO Homework JAVA2_8: Unsubscribe by timer
        int timeOut = 10;
        System.out.printf("Таймаут без авторизации (секунд): %d \n", timeOut);

        Timer timer = new Timer();
        ClientHandler cl = this;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (nickname == null) {
                    try {
//                        System.out.println("Авторизация не выполнена, соединение закрыто.");
                        logger.info("Авторизация не выполнена, соединение закрыто.");
                        cl.closeConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(task, timeOut * 1000); //выполнить задачу через timeOut секунд

        while (true) {
            Command command = readCommand();

            if (command == null) {
                continue;
            }

            if (command.getType() != CommandType.AUTH && command.getType() != CommandType.REG) {
                continue;
            }

            if (command.getType() == CommandType.AUTH) {
                AuthCommandData data = (AuthCommandData) command.getData();
                login = data.getLogin();
                password = data.getPassword();

                nickname = myServer.getAuthService().getNickByLoginPass(login, password);
                if (nickname == null) {
                    sendCommand(errorCommand("Некорректные логин или пароль!"));
                    continue;
                }

                if (myServer.isNickBusy(nickname)) {
                    sendCommand(errorCommand("Такой пользователь уже вошел в чат!"));
                    continue;
                }


            } else {

                NewUserCommandData data = (NewUserCommandData) command.getData();

                login = data.getLogin();
                if (myServer.getAuthService().isLoginExist(login) != null) {
                    sendCommand(errorCommand("Логин занят!"));
                    continue;
                }

                password = data.getPassword();

                nickname = data.getNickname();
                if (myServer.getAuthService().isNicknameExist(nickname) != null) {
                    sendCommand(errorCommand("Никнейм занят!"));
                    continue;
                }

                if (!myServer.getAuthService().registerNewUser(login, password, nickname)) {
                    sendCommand(errorCommand("Ошибка регистрации пользователя!"));
                    continue;
                }
            }

            sendCommand(authOkCommand(nickname, login));
            setNickname(nickname);
            myServer.broadcastMessage(String.format("Пользователь '%s' зашел в чат!", nickname), null);
            myServer.subscribe(this);
            return;
        }
    }

    public void sendCommand(Command command) throws IOException {
        out.writeObject(command);
    }

    private Command readCommand() throws IOException {
        Command command = null;
        try {
            command = (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Не удалось получить данные класса Command");
            logger.warning("Не удалось получить данные класса Command");
            e.printStackTrace();
        }
        return command;
    }

    private void readMessages() throws IOException {
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }

            switch (command.getType()) {
                case PRIVATE_MESSAGE: {
                    PrivateMessageCommandData data = (PrivateMessageCommandData) command.getData();
                    String receiver = data.getReceiver();
                    String message = data.getMessage();
                    myServer.sendPrivateMessage(this, receiver, message);
                    break;
                }
                case PUBLIC_MESSAGE: {
                    PublicMessageCommandData data = (PublicMessageCommandData) command.getData();
                    String message = data.getMessage();
                    myServer.broadcastMessage(message, this);
                    break;
                }
                case SET_NICKNAME: {
                    NicknameCommandData data = (NicknameCommandData) command.getData();
                    String newNickname = data.getNickname();

                    if (myServer.getAuthService().isNicknameExist(newNickname) != null) {
                        sendCommand(errorCommand("Никнейм занят!"));
                    } else {
                        boolean result = myServer.getAuthService().changeNickname(nickname, newNickname);
                        if (result) {
                            String oldNickname = nickname;
                            nickname = newNickname;
                            sendCommand(changeNicknameOkCommand(nickname));
                            myServer.usersListUpdate();
                            myServer.broadcastMessage(String.format("Пользователь '%s' изменил ник на '%s'!", oldNickname, nickname), null);
                        } else {
                            sendCommand(errorCommand("Ошибка! Никнейм не изменен."));
                        }
                    }
                    break;
                }
                case END:
                    myServer.broadcastMessage("Пользователь '" + nickname + "' покинул чат.", null);
                    return;
                default:
                    logger.severe("Неизвестная команда: " + command.getType());
                    throw new IllegalArgumentException("Unknown command type: " + command.getType());
            }
        }
    }

    private void closeConnection() throws IOException {
        myServer.unsubscribe(this);
        clientSocket.close();
    }


    public void sendMessage(String message) throws IOException {
        sendCommand(Command.messageInfoCommand(message));
    }

    public void sendMessage(String sender, String message) throws IOException {
        sendCommand(clientMessageCommand(sender, message));
    }

    public String getNickname() {
        return nickname;
    }

    private void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
