package lesson2.server.chat.auth;

import lesson2.server.chat.handler.ClientHandler;

import java.sql.*;
import java.util.logging.Logger;

public class BaseAuthService implements AuthService {

// TODO: 29.12.2020 Небезопасное хранение паролей в открытой БД. Хранить пароли в виде hash (jBCrypt, что-то другое ???)

    private static Connection connection;
    private static Statement stmt;

    private static final Logger logger = Logger.getLogger(BaseAuthService.class.getName());

    @Override
    public void start() {
        try {
            connect();
//            System.out.println("Запущен сервис авторизации");
            logger.info("Запущен сервис авторизации");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }

    @Override
    public void stop() {
        disconnect();
//        System.out.println("Сервис авторизации остановлен");
        logger.info("Сервис авторизации остановлен");
    }

    @Override
    public String getNickByLoginPass(String login, String password) {
        return sqlSelect(String.format("SELECT nickname FROM auth WHERE login = '%s' AND psw = '%s'", login, password));
    }

    @Override
    public String isLoginExist(String login) {
        return sqlSelect(String.format("SELECT login FROM auth WHERE login = '%s'", login));
    }

    @Override
    public String isNicknameExist(String nickname) {
        return sqlSelect(String.format("SELECT nickname FROM auth WHERE nickname = '%s'", nickname));
    }

    private String sqlSelect(String sqlSelectQuery) {
        try {
            ResultSet rs = stmt.executeQuery(sqlSelectQuery);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean registerNewUser(String login, String password, String nickname) {
        try {
            stmt.executeUpdate(String.format("INSERT INTO auth (login, psw, nickname) VALUES ('%s', '%s', '%s');", login, password, nickname));
            logger.info("Зарегистрирован новый пользователь: " + nickname);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
            return false;
        }
    }

    public Boolean changeNickname (String oldNickname, String newNickname) {
        try {
            stmt.executeUpdate(String.format("UPDATE auth SET nickname='%s' WHERE nickname='%s';", newNickname, oldNickname));
            logger.info("Пользователь " + oldNickname + " изменил никнейм на " + newNickname);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
            return false;
        }
    }

    private static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            // TODO: заменить на относительный путь к файлу базы данных
            connection = DriverManager.getConnection("jdbc:sqlite://C:\\GeekBrains\\JAVA\\JAVA 3\\src\\main\\java\\lesson2\\server\\db\\main.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }

    private static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
