package com.wovi10.checklist;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

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
        HBox checklistItem = createItem();
        checklist_Group.getChildren().add(checklistItem);
    }

    private String requestItemName() {
        Popup popup = new Popup();
        Label questionLabel = new Label("What item do you want to add?");
        TextField answerField = new TextField();
        Button answerButton = new Button();
        popup.getContent().addAll(questionLabel, answerField, answerButton);
        answerButton.setOnAction(e -> giveAnswer(answerField));
        return itemToAdd;
    }

    private void giveAnswer(TextField answerField) {
        itemToAdd = answerField.getText();
    }

    private HBox createItem() {
        HBox item = new HBox();
        CheckBox checkBox = createCheckBox();
        TextField textField = createTextField();
        item.getChildren().add(checkBox);
        item.getChildren().add(textField);
        return item;
    }

    private TextField createTextField() {
        return new TextField();
    }

    private CheckBox createCheckBox() {
        return new CheckBox();
    }
}
