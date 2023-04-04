package edu.virginia.cs.gui;

import edu.virginia.cs.wordle.Wordle;
import edu.virginia.cs.wordle.WordleImplementation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordleController {
    Wordle wordle = new WordleImplementation();

    @FXML
    private Label welcomeText;

    @FXML
    private GridPane root = new GridPane();

    @FXML
    private Label label00;

    @FXML
    private List<TextField> textFields = new ArrayList<>(); //stores all textfields

    @FXML
    private List<Label> labels = new ArrayList<>(); //stores all labels

    public void initialize(){ //sets up event handlers for every textfield
        for (int i = 0; i < textFields.size(); i++) {
            TextField textField = textFields.get(i);
            Label label = labels.get(i);

            textField.textProperty().addListener((observable, oldValue, newValue) ->
                    label.setText("entry:" + newValue));
        }
        }
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








