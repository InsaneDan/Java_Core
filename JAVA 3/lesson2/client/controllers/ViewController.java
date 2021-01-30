package lesson2.client.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lesson2.client.ClientChat;
import lesson2.client.models.History;
import lesson2.client.models.Network;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ViewController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    @FXML
    private TextField changeNicknameField;
    @FXML
    private Button btnApplyNicknameChange;
    @FXML
    private Button btnCancelNicknameChange;
    @FXML
    private MenuItem menuItemChangeNick;
    @FXML
    private HBox boxNickname;
    @FXML
    private HBox chatTitle;


    private Network network;
    private Stage primaryStage;

    private String selectedRecipient;
    private History historyLog = null;
    @FXML
    public void initialize() {

        //usersList.setItems(FXCollections.observableArrayList(ClientChat.USERS_TEST_DATA));

        usersList.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = usersList.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                usersList.requestFocus();
                if (! cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell ;
        });
    }

    @FXML
    private void sendMessage() {
        String message = textField.getText();
        appendMessage("Я: " + message);
        textField.clear();

        try {
            if (selectedRecipient != null) {
                network.sendPrivateMessage(selectedRecipient, message);
            } else {
                network.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "Failed to send message";
            ClientChat.showNetworkError(e.getMessage(), errorMessage, primaryStage);
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void appendMessage(String message) {
        chatHistory.appendText(message);
        historyLog.saveChatHistory(message);
        chatHistory.appendText(System.lineSeparator());
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public TextField getTextField() {
        return textField;
    }

    public void changeNickname(ActionEvent actionEvent) {
        nickNameMenu(true);
    }

    public void cancelNicknameChange(ActionEvent actionEvent) {
        nickNameMenu(false);
    }

    private void nickNameMenu (Boolean show) {
        chatTitle.setVisible(!show);
        boxNickname.setVisible(show);
        boxNickname.setFillHeight(show);
        if (show) {
            chatTitle.prefHeight(0);
            boxNickname.prefHeight(25);
            changeNicknameField.requestFocus();
        } else {
            chatTitle.prefHeight(25);
            boxNickname.prefHeight(0);
            textField.requestFocus();
            changeNicknameField.clear();
        }
    }

    public void applyNicknameChange(ActionEvent actionEvent) throws IOException {
        String newNickname = changeNicknameField.getText();
        network.sendNewNicknameMessage(newNickname);
        changeNicknameField.clear();
    }

    // наверное, экономичнее было бы использовать ReversedLinesFileReader (Apache Commons IO)?
    public void restoreChatHistory(int nMessagesToLoad) {

        historyLog = new History(network.getLogin());
        String msg = null;
        List<String> historyList = historyLog.getAllChatHistory();

        // добавляем N сообщений в chatHistory
        int startPosition = 0;
        if (historyList.size() > nMessagesToLoad) { startPosition = historyList.size() - nMessagesToLoad; }
        for (int i = startPosition; i < historyList.size(); i++) {
            msg = historyList.get(i);
            try {
                chatHistory.appendText(new String(msg.concat("\n").getBytes("Windows-1251"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


}