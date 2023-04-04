package edu.virginia.cs.gui;

import edu.virginia.cs.wordle.WordleImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import edu.virginia.cs.wordle.*;

public class WordleController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField WordleField;
    @FXML
    private TextField textField0;

    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;

    private boolean Locked1 = false;
    private boolean Locked2 = false;
    private boolean Locked3 = false;
    private boolean Locked4 = false;
    private boolean Locked5 = false;


    @FXML
    private GridPane gridpane;

    @FXML
    private Label errorLabel;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!")
        ;
    }

    @FXML
    private void LetterChecking() {

        if(isValidWord(textField1.getText()+textField2.getText()+textField3.getText()+textField4.getText()+textField5.getText()))
        {
            textField1.setEditable(false);
            textField2.setEditable(false);
            textField3.setEditable(false);
            textField4.setEditable(false);
            textField5.setEditable(false);
        }
    }
    
    private boolean isValidWord(String word)
    {
        Wordle wordle = new WordleImplementation();
        if(word.length() ==5)
        {
            try {
                wordle.submitGuess(word);
            }

            catch(IllegalWordException e){
                
            }
            

        return false;
    }








    @FXML
    private void onKeyTyped(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        int caretPosition = textField.getCaretPosition();
        if (caretPosition == 1) {
            // Move focus to next TextField
            switch (textField.getId()) {
                case "textField0":
                    textField1.requestFocus();
                    break;
                case "textField1":
                    textField2.requestFocus();
                    break;
                case "textField2":
                    textField3.requestFocus();
                    break;
                case "textField3":
                    textField4.requestFocus();
                    break;
                case "textField4":
                    textField5.requestFocus();
                    // Last TextField, do nothing
                    break;
                case "textField5":
                    textField5.setEditable(false);
                        break;
            }
        }
    }


}








