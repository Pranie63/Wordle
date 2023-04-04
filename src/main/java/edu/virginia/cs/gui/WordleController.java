package edu.virginia.cs.gui;

import edu.virginia.cs.wordle.Wordle;
import edu.virginia.cs.wordle.WordleImplementation;
import javafx.beans.property.StringProperty;
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
    private List<TextField> textFields = new ArrayList<>(30); //stores all textfields

    @FXML
    private List<Label> labels = new ArrayList<>(); //stores all labels

    public void initialize() { //sets up event handlers for every textfield
//        root.setGridLinesVisible(false);
//        root.getChildren().addAll(new TextField());
        TextField textField;
        Label label;
        for (int i = 0; i < 30; i++) {
            textField = new TextField();
            label = new Label();
            textFields.add(textField);
            labels.add(label);
//            textField = textFields.get(i);
//            label = labels.get(i);

            TextField finalTextField = textField;
//            textField.textProperty().addListener((observable, oldValue, newValue) -> {
//                validInput(observable.getValue()) ? finalTextField.textProperty().setValue(newValue) : finalTextField.textProperty().setValue(oldValue)
//                    });
            root.add(textField, i%5, i/5);
        }
    }

    public boolean validInput(String str) {
        return str.length() <= 1 && str.toUpperCase().charAt(0) >= 'A' && str.toUpperCase().charAt(0) <= 'Z';
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









