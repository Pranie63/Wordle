package edu.virginia.cs.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.layout.RowConstraints;

public class WordleController {
    @FXML
    private Label welcomeText;

//    @FXML
//    private TextField textField0;
//    @FXML
//    private Label errorLabel;

    @FXML
    private GridPane wordlePane;
    int rowCount = 6;
    int columnCount = 5;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!")
        ;
    }
//    @FXML
//    private void checkWordAvailability()
//    {
//        String letter0 = textField0.getText();
//        if (letter0.length() > 1 || (letter0.charAt(0)>=97 && letter0.charAt(0)<=122)) {
//            textField0.setEditable(false);
//        }
//    }
//
//    @FXML
//    protected void onTextEntryEnter(KeyEvent event) {
//        if (event.getCode().equals(KeyCode.A)) {
//            try {
//                textField0.setText(textField0.getText().strip());
//                errorLabel.setText("");
//                textField0.setDisable(true);
//            } catch (NumberFormatException e) {
//                errorLabel.setText("Error: Enter a valid int");
//            }
//        }
//    }
}








