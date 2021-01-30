package lesson2.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lesson2.client.ClientChat;
import lesson2.client.models.Network;

import java.io.IOException;

public class AuthController {

    private static final String AUTH_CMD = "/auth"; // "/auth login password"

    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField nicknameField;
    @FXML
    private Label nicknameLabel;

    private Network network;
    private Boolean regMode = false;

    @FXML
    public void executeAuth(ActionEvent actionEvent) {

        String login = loginField.getText();
        String password = passwordField.getText();
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            ClientChat.showNetworkError("Логин и пароль обязательны!", "Валидация", null);
            return;
        }

        String nickname = nicknameField.getText();;
        if (regMode && (nickname == null || nickname.isBlank())) {
            ClientChat.showNetworkError("Для регистрации никнейм обязателен!", "Валидация", null);
            return;
        }

        try {
            if (regMode) {
                network.sendNewUserMessage(login, password, nickname);
            } else {
                network.sendAuthMessage(login, password);
            }
            network.setLogin(login);

        } catch (IOException e) {
            ClientChat.showNetworkError(e.getMessage(), "Auth error!", null);
            e.printStackTrace();
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void showNicknameField(ActionEvent actionEvent) {
        regMode = !nicknameField.isVisible();
        nicknameField.setVisible(regMode);
        nicknameLabel.setVisible(regMode);
    }
}
