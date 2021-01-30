package lesson2.server.chat.auth;

import java.sql.SQLException;

public interface AuthService {

    void start();
    void stop();

    String getNickByLoginPass(String login, String password);

    String isLoginExist(String login);

    String isNicknameExist(String nickname);

    Boolean registerNewUser(String login, String password, String nickname);

    Boolean changeNickname(String oldNickname, String newNickname);
}
