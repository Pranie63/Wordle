package edu.virginia.cs.gui;

import edu.virginia.cs.wordle.DefaultDictionaryFactory;
import edu.virginia.cs.wordle.Wordle;
import edu.virginia.cs.wordle.WordleDictionary;
import edu.virginia.cs.wordle.WordleImplementation;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.virginia.cs.wordle.LetterResult;

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

    @FXML
    private Label invalidLabel;

    @FXML
    private Button restartGameYes;

    @FXML
    private Button restartGameNo;

    private WordleDictionary dictionary;

    private int textFieldIndex = 0;

    public int SubmittedGuesses = 0;


    public void initialize() {
        restartGameNo.setVisible(false);
        restartGameYes.setVisible(false);
        restartGameYes.setOnMouseClicked((MouseEvent event) -> {
            wordle = new WordleImplementation();
            textFields.clear();
            textFieldIndex = 0;
            SubmittedGuesses = 0;
            initialize();
        });
        restartGameNo.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });
        displayValidWord();
        dictionary = new DefaultDictionaryFactory().getDefaultGuessesDictionary();
        TextField textField;
        Label label;

        for (int i = 0; i < 30; i++) {
            textField = new TextField();
            label = new Label();
            textFields.add(textField);

            textField.setStyle("-fx-display-caret: false;" + "-fx-alignment: center;" +
                            "-fx-pref-width: 500px;" + "-fx-pref-height: 500px;" + "-fx-font-size: 25px;" + "-fx-border-width: 2px;" + "-fx-border-color: lightgray;");


            textField.setFocusTraversable(false);
            textField.setMouseTransparent(true);


            root.add(textField, i % 5, i / 5);

        }

        root.setFocusTraversable(true);
        root.requestFocus();
        root.setOnKeyPressed((KeyEvent event) -> {
            gridPaneHandler(event);
        });
    }

    public void gridPaneHandler(KeyEvent event) {
        TextField curtextField = textFields.get(textFieldIndex);
        if (textFieldIndex % 5 == 4 && event.getCode().equals(KeyCode.ENTER) && curtextField.textProperty().getValue().length() == 1) {
            if (newAnswerController(textFieldIndex)) {
                checkLetters(wordle.getAnswer());
            }
            if(wordle.isWin()){
                displayWin();
            }
            else if(wordle.isLoss()){
                displayLoss();
            }
        }
        else if (event.getCode().equals(KeyCode.BACK_SPACE)) {
            if (curtextField.textProperty().getValue().length() == 1 || textFieldIndex % 5 == 0) {
                curtextField.textProperty().setValue("");
                curtextField.setStyle(curtextField.getStyle() + "-fx-border-color: lightgray;");
            }
            else if (curtextField.textProperty().getValue().length() < 1) {
                textFieldIndex--;
                textFields.get(textFieldIndex).textProperty().setValue("");
                textFields.get(textFieldIndex).setStyle(curtextField.getStyle() + "-fx-border-color: lightgray;");
            }
        }
        else if (event.getCode() == KeyCode.TAB) {
            event.consume();
        }
        else if (validInput(event.getText())) {
            if (!validInput(curtextField.textProperty().getValue())) {
                curtextField.textProperty().setValue(event.getText().toUpperCase());
                curtextField.setStyle(curtextField.getStyle() + "-fx-border-color: gray;");
                if (textFieldIndex % 5 != 4) {
                    textFieldIndex++;
                }
            }
        }
    }

    public boolean validInput(String str) {
        return str.length() == 1 && str.toUpperCase().charAt(0) >= 'A' && str.toUpperCase().charAt(0) <= 'Z';
    }

    public void handleKeyPressed() {

    }

    public void checkLetters(String word) {

        String x = "";

        for (int i = SubmittedGuesses * 5; i < SubmittedGuesses * 5 + 5; i++) {
            x += textFields.get(i).getText();

        }
        LetterResult[] results = wordle.submitGuess(x);

        for (int c = 0; c < results.length; c++) {
            if (results[c] == LetterResult.YELLOW) {
                textFields.get(c + SubmittedGuesses * 5).setStyle(textFields.get(c + SubmittedGuesses * 5).getStyle() + "-fx-control-inner-background: #FFC425; -fx-text-fill: white;" + "-fx-border-color: #FFC425;");
            } else if (results[c] == LetterResult.GREEN) {
                textFields.get(c + SubmittedGuesses * 5).setStyle(textFields.get(c + SubmittedGuesses * 5).getStyle() + "-fx-control-inner-background: #019A01; -fx-text-fill: white;" + "-fx-border-color: #019A01");
            } else {
                textFields.get(c + SubmittedGuesses * 5).setStyle(textFields.get(c + SubmittedGuesses * 5).getStyle() + "-fx-control-inner-background: gray; -fx-text-fill: white;" + "-fx-border-color: gray;");
            }

        }
        SubmittedGuesses++;

        CheckForGameState();
    }

    public void CheckForGameState() {
        if (wordle.isWin()) {
            disableTextFields();
            restartGameNo.setVisible(true);
            restartGameYes.setVisible(true);
        } else if (wordle.isLoss()) {
            disableTextFields();
            restartGameNo.setVisible(true);
            restartGameYes.setVisible(true);
        }
    }

    public void disableTextFields() {
        for (TextField textField : textFields) {
            textField.setEditable(false);
        }
        root.setFocusTraversable(false);
    }

    public void answerController(int index) {
        if (validWord(index)) {
            displayValidWord();
            if(index < 29) {
                textFields.get(index + 1).requestFocus();
            }
        }
        else {
            displayInvalidWord();;
            for (int j = index-4; j < index+1; j++) {
                textFields.get(j).textProperty().setValue("");
            }
            textFields.get(index-4).requestFocus();
        }
    }

    public boolean newAnswerController(int index) {
        if (validWord(index)) {
            displayValidWord();
            if(index < 29) {
                textFieldIndex++;
            }
            return true;
        }
        else {
            displayInvalidWord();;
            for (int j = index-4; j < index+1; j++) {
                textFields.get(j).textProperty().setValue("");
                textFields.get(j).setStyle(textFields.get(j).getStyle() + "-fx-border-color: lightgray;");
            }
            textFieldIndex -= 4;
            return false;
        }
    }

    public void displayInvalidWord(){

        invalidLabel.setText("Invalid Word, please enter a new one!");

    }

    public void displayValidWord(){

        invalidLabel.setText("");

    }

    public void displayWin(){

        invalidLabel.setText("Congratulations, you have won!");

    }

    public void displayLoss(){

        invalidLabel.setText("I'm sorry, you have lost. The correct answer was '" + wordle.getAnswer()+"'.");

    }

    public boolean validWord(int index) {
        String input = textFields.get(index-4).getText() +
                textFields.get(index-3).getText() +
                textFields.get(index-2).getText() +
                textFields.get(index-1).getText() +
                textFields.get(index).getText();
        return dictionary.containsWord(input);
    }



}










