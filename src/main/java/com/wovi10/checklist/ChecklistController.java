package com.wovi10.checklist;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChecklistController {
    private final String strikethroughStyle = String.valueOf(
            ChecklistController.class.getResource("css/strikethrough.css"));
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

    private void giveAnswer(TextField answerLabel, Stage popup) {
        itemToAdd = answerLabel.getText();
        popup.close();

    }

    private HBox createItem(String itemName) {
        HBox item = new HBox();
        Label nameLabel = createNameLabel(itemName);
        CheckBox checkBox = create_CheckBox(nameLabel);
        Button deleteButton = create_DeleteButton(item);
        deleteButton.setAlignment(Pos.CENTER_RIGHT);
        item.getChildren().add(checkBox);
        item.getChildren().add(nameLabel);
        item.getChildren().add(deleteButton);
        return item;
    }

    private Button create_DeleteButton(HBox item) {
        Button button = new Button();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                checklist_Group.getChildren().remove(item);
            }
        };
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        return button;
    }

    @FXML
    private void onDeleteButtonClick() {
        String itemName = requestItemName();
        HBox checklistItem = createItem(itemName);
        checklist_Group.getChildren().add(checklistItem);
    }

    private Label createNameLabel(String itemName) {
        Label nameLabel = new Label();
        nameLabel.setText(itemName);
        return nameLabel;
    }

    private CheckBox create_CheckBox(Label nameLabel) {
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue) {
                nameLabel.getStylesheets().add(strikethroughStyle);
            }else{
                nameLabel.getStylesheets().remove(strikethroughStyle);
            }
        });
        return checkBox;
    }
}
