package lesson2.client.models;

import javafx.application.Platform;
import lesson2.client.ClientChat;
import lesson2.client.controllers.ViewController;
import lesson2.clientserver.Command;
import lesson2.clientserver.commands.*;

import java.io.*;
import java.net.Socket;

import static lesson2.clientserver.Command.*;

public class Network {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8189;

    private String host;
    private int port;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;
    private ClientChat clientChat;
    private String nickname;
    private String login;

    public Network() {
        this(SERVER_ADDRESS, SERVER_PORT);
    }

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Network(ClientChat clientChat) {
        this();
        this.clientChat = clientChat;
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e) {
            System.err.println("Соединение не было установлено!");
            e.printStackTrace();
            return false;
        }
    }

    public void sendPrivateMessage(String receiver, String message) throws IOException {
        sendCommand(privateMessageCommand(receiver, message));
    }

    public void sendMessage(String message) throws IOException {
        sendCommand(publicMessageCommand(nickname, message));
    }

    private void sendCommand(Command command) throws IOException {
        outputStream.writeObject(command);
    }

    public void waitMessages(ViewController viewController) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Command command = readCommand();
                    if (command == null) {
                        continue;
                    }

                    if (clientChat.getState() == ClientChatState.AUTHENTICATION) {
                        processAuthResult(command);
                    } else {
                        processMessage(viewController, command);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Соединение было потеряно!");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void processMessage(ViewController viewController, Command command) {
        switch (command.getType()) {
            case INFO_MESSAGE: {
                MessageInfoCommandData data = (MessageInfoCommandData) command.getData();
                Platform.runLater(() -> {
                    viewController.appendMessage(data.getMessage());
                });
                break;
            }
            case CLIENT_MESSAGE: {
                ClientMessageCommandData data = (ClientMessageCommandData) command.getData();
                String sender = data.getSender();
                String message = data.getMessage();
                Platform.runLater(() -> {
                    viewController.appendMessage(String.format("%s: %s", sender, message));
                });
                break;
            }
            case ERROR: {
                ErrorCommandData data = (ErrorCommandData) command.getData();
                Platform.runLater(() -> {
                    ClientChat.showNetworkError(data.getErrorMessage(), "Server error", null);
                });
                break;
            }
            case UPDATE_USERS_LIST: {
                UpdateUsersListCommandData data = (UpdateUsersListCommandData) command.getData();
                Platform.runLater(() -> {
                    clientChat.updateUsers(data.getUsers());
                });
                break;
            }
            case SET_NICKNAME_OK: {
                NicknameOkCommandData data = (NicknameOkCommandData) command.getData();
                Platform.runLater(() -> {
                    clientChat.changeNicknameTitle(data.getNickname());
                });
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown command type: " + command.getType());
        }
    }

    private void processAuthResult(Command command) {
        switch (command.getType()) {
            case AUTH_OK: {
                AuthOkCommandData data = (AuthOkCommandData) command.getData();
                nickname = data.getNickname();
                Platform.runLater(() -> {
                    clientChat.activeChatDialog(nickname);
                });
                break;
            }
            case ERROR:
                ErrorCommandData data = (ErrorCommandData) command.getData();
                Platform.runLater(() -> {
                    ClientChat.showNetworkError(data.getErrorMessage(), "Auth error", null);
                });
                break;
            default:
                throw new IllegalArgumentException("Unknown command type: " + command.getType());
        }
    }

    public void close() {
        try {
            sendCommand(endCommand());
            if (socket != null && socket.isConnected()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Command readCommand() throws IOException {
        Command command = null;
        try {
            command = (Command) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to read Command class");
            e.printStackTrace();
        }
        return command;
    }

    public void sendAuthMessage(String login, String password) throws IOException {
        sendCommand(authCommand(login, password));
    }

    public void sendNewUserMessage(String login, String password, String nickname) throws IOException {
        sendCommand(regCommand(login, password, nickname));
    }

    public void sendNewNicknameMessage(String nickname) throws IOException {
        sendCommand(changeNicknameCommand(nickname));
    }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }
}
