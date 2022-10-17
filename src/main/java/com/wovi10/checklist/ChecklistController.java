package com.wovi10.checklist;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChecklistController {
    @FXML
    public VBox vBox;
    public VBox checklist_Group;
    public HBox group_Item;
    public CheckBox item_CB;
    public TextField item_Name;
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
        TextField answerField = new TextField();
        Button answerButton = new Button("Add");
        VBox popupContent = new VBox();

        popupContent.getChildren().addAll(questionLabel, answerField, answerButton);
        answerButton.setOnAction(e -> giveAnswer(answerField, popup));
        Scene popupScene = new Scene(popupContent, 300, 200);
        popup.setScene(popupScene);
        popup.showAndWait();
        return itemToAdd;
    }

    private void giveAnswer(TextField answerField, Stage popup) {
        itemToAdd = answerField.getText();
        popup.close();

    }

    private HBox createItem(String itemName) {
        HBox item = new HBox();
        CheckBox checkBox = createCheckBox();
        TextField textField = createTextField(itemName);
        item.getChildren().add(checkBox);
        item.getChildren().add(textField);
        return item;
    }

    private TextField createTextField(String itemName) {
        TextField textField = new TextField();
        textField.setText(itemName);
        textField.setEditable(false);
        return textField;
    }

    private CheckBox createCheckBox() {
        return new CheckBox();
    }
}
