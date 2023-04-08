package edu.virginia.cs.gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class WordleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("WordleController.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Wordle Game");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();


    }
}

