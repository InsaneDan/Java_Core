package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static final List<String> USERS_ARRAY = List.of("User1", "User2", "User3", "User4", "User5");


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Geekbrains Chat");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
