package com.wovi10.checklist;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChecklistController {
    @FXML
    public VBox vBox;
    public VBox checklist_Group;
    private String itemToAdd;

    @FXML
    protected void onAddButtonClick() {
        String itemName = requestItemName();
        HBox checklistItem = createItem(itemName);
        checklist_Group.getChildren().add(checklistItem);
    }

    private String requestItemName() {
        Stage popup = new Stage();
        Label questionLabel = new Label("What item do you want to add?");
        Label answerLabel = new Label();
        Button answerButton = new Button("Add");
        VBox popupContent = new VBox();

        popupContent.getChildren().addAll(questionLabel, answerLabel, answerButton);
        answerButton.setOnAction(e -> giveAnswer(answerLabel, popup));
        Scene popupScene = new Scene(popupContent, 300, 200);
        popup.setScene(popupScene);
        popup.showAndWait();
        return itemToAdd;
    }

    private void giveAnswer(Label answerLabel, Stage popup) {
        itemToAdd = answerLabel.getText();
        popup.close();

    }

    private HBox createItem(String itemName) {
        HBox item = new HBox();
        Label nameLabel = createNameLabel(itemName);
        CheckBox checkBox = createCheckBox(nameLabel);
        item.getChildren().add(checkBox);
        item.getChildren().add(nameLabel);
        return item;
    }

    private Label createNameLabel(String itemName) {
        Label nameLabel = new Label();
        nameLabel.setText(itemName);
        return nameLabel;
    }

    private CheckBox createCheckBox(Label nameLabel) {
        CheckBox checkBox = new CheckBox();
        return checkBox;
    }
}
