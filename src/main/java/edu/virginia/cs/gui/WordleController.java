package edu.virginia.cs.gui;

import edu.virginia.cs.wordle.DefaultDictionaryFactory;
import edu.virginia.cs.wordle.Wordle;
import edu.virginia.cs.wordle.WordleDictionary;
import edu.virginia.cs.wordle.WordleImplementation;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private WordleDictionary dictionary;

    public int SubmittedGuesses = 0;


    public void initialize() { //sets up event handlers for every textfield
        dictionary = new DefaultDictionaryFactory().getDefaultGuessesDictionary();
        TextField textField;
        Label label;

        for (int i = 0; i < 30; i++) {
            textField = new TextField();
            label = new Label();
            textFields.add(textField);
            //textFields.get(0).requestFocus();
            labels.add(label);
//            textField = textFields.get(i);
//            label = labels.get(i);
            final int finalIndex = i;


            TextField finalTextField = textField;
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (validInput(observable.getValue())) {
                    finalTextField.textProperty().setValue(newValue.toUpperCase());
                    if (finalIndex%5 != 4) {
                        textFields.get(finalIndex+1).requestFocus();
                    }
                } else {
                    finalTextField.textProperty().setValue(oldValue);
                }
            });
            if (i%5 == 4) {
                int finalI = i;
                textField.setOnKeyPressed((KeyEvent event) -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        answerController(finalI);
                        checkLetters(wordle.getAnswer());
                    }
                });
            }
            textField.setStyle("-fx-display-caret: false;" + "-fx-alignment: center");
            root.add(textField, i%5, i/5);

        }
        textFields.get(0).requestFocus();
    }

    public boolean validInput(String str) {
        return str.length() <= 1 && str.toUpperCase().charAt(0) >= 'A' && str.toUpperCase().charAt(0) <= 'Z';
    }
    public void handleKeyPressed()
    {
//        for (int i = 0; i < textFields.size(); i++) {
//
//            final int index = i;
//            final int currentIndex = i;
//            TextField textField = textFields.get(i);
//           // Label label = labels.get(i);
//        //System.out.println(textFields.get(i));
//            textField.setOnKeyTyped(event -> {
//                String text = textField.getText();
//                System.out.println(text);
//                System.out.println("This is our currentIndex" +  currentIndex);
//                if (text.length() >= 1 && index % 5 != 4) {
//                    textField.setText(text.substring(0, 1));
//                    System.out.println("we have reached here");
//                    System.out.println("OUr current index is" +currentIndex);
//
//                    int nextIndex = index + 1;
//                    if (nextIndex < textFields.size()) {
//                        textFields.get(nextIndex).requestFocus();
//                    }
//                }
//            });
//
//        }
    }
    public void checkLetters(String word) {
        //LetterResult[] results = wordle.submitGuess();
        String x = "";

        for (int i = SubmittedGuesses * 5; i < SubmittedGuesses * 5 + 5; i++) {
            x += textFields.get(i).getText();

        }
        LetterResult[] results = wordle.submitGuess(x);
        System.out.println("THIS IS SDE TESTING");
        for (int c = 0; c < results.length; c++) {
            if (results[c] == LetterResult.YELLOW) {

                textFields.get(c + SubmittedGuesses * 5).setStyle("-fx-control-inner-background: yellow; -fx-text-fill: white;");
            } else if (results[c] == LetterResult.GREEN) {
                textFields.get(c + SubmittedGuesses * 5).setStyle("-fx-control-inner-background: green; -fx-text-fill: white;");
            } else {
                textFields.get(c + SubmittedGuesses * 5).setStyle("-fx-control-inner-background: gray; -fx-text-fill: white;");
            }

        }
        SubmittedGuesses++;
        if (wordle.isWin()) {
            for (TextField tf : textFields) {
                tf.setEditable(false);
            }
        }
        if(SubmittedGuesses==6 ||wordle.isLoss())
        {
            System.out.println("Your game's over");
        }
    }



    public void answerController(int index) {
        if (validWord(index)) {
            textFields.get(index+1).requestFocus();
            for (int j = index-4; j < index+1; j++) {
                textFields.get(j).setEditable(false);
            }
        }
        else {
            for (int j = index-4; j < index+1; j++) {
                textFields.get(j).textProperty().setValue("");
            }
            textFields.get(index-4).requestFocus();
        }
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










