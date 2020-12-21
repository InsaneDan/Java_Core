package client;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    Button btnSend;
    @FXML
    ListView<String> listChatHistory;
    @FXML
    ListView<String> listUsers;
    @FXML
    TextArea txtArea;


    public void btnSendClick() {
        msgSend();
    }

    public void initialize() {
        listUsers.setItems(FXCollections.observableArrayList(Main.USERS_TEST_DATA));
        txtArea.setOnKeyPressed(this::handle);
    }

    private void msgSend() {
        listChatHistory.getItems().add(txtArea.getText());
        txtArea.clear();
    }

    private void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            msgSend();
        }
    }
}