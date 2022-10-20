package com.wovi10.checklist.utils;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.wovi10.checklist.Constants.PopupConstants.*;

/**
 * ChecklistPopup
 * This class provides all utils for the popup for my checklist.
 * The popup gets generated and everything gets paused until an answer has been given and processed.
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class ChecklistPopup {
    public Stage popupBox;
    private String itemToAdd;

    public ChecklistPopup() {
        popupBox = new Stage();
        VBox popupContent = create_popupContent();
        Scene scene = new Scene(popupContent, POPUP_HEIGHT, POPUP_WIDTH);
        popupBox.setScene(scene);
        popupBox.showAndWait();
    }

    //region Getters and Setters
    public String getItemToAdd() {
        return itemToAdd;
    }
    //endregion

    /**
     * Create the content to go on to the popup.
     * @return The content to go on to the popup.
     */
    private VBox create_popupContent() {
        Label questionLabel = new Label(POPUP_QUESTION);
        TextField answerField = new TextField();
        Button answerButton = create_answerButton(answerField);
        VBox popupContent = new VBox();
        popupContent.getChildren().addAll(questionLabel, answerField, answerButton);
        return popupContent;
    }

    /**
     * Creates the button for on the popup.
     * @param answerField The field in which the answer will be typed.
     * @return The answer button for on the popup.
     */
    private Button create_answerButton(TextField answerField) {
        Button answerButton = new Button(ADD_TEXT);
        answerButton.setOnAction(e -> giveAnswer(answerField));
        return answerButton;
    }

    /**
     * Reads input and sets the answer.
     * @param answerField The field in which the answer will be put.
     */
    private void giveAnswer(TextField answerField) {
        itemToAdd = answerField.getText();
        popupBox.close();
    }

}
