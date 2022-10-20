package com.wovi10.checklist.utils;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.wovi10.checklist.Constants.ChecklistConstants.ADD_TEXT;
import static com.wovi10.checklist.Constants.PopupConstants.*;

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

    private VBox create_popupContent() {
        Label questionLabel = new Label(POPUP_QUESTION);
        TextField answerField = new TextField();
        Button answerButton = create_answerButton(answerField);
        VBox popupContent = new VBox();
        popupContent.getChildren().addAll(questionLabel, answerField, answerButton);
        return popupContent;
    }

    private Button create_answerButton(TextField answerField) {
        Button answerButton = new Button(ADD_TEXT);
        answerButton.setOnAction(e -> giveAnswer(answerField));
        return answerButton;
    }

    private void giveAnswer(TextField answerField) {
        itemToAdd = answerField.getText();
        popupBox.close();
    }
}
