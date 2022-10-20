package com.wovi10.checklist;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.wovi10.checklist.ChecklistConstants.*;

/**
 * ChecklistController
 * This class shows what happens when interacting with the checklist form.
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class ChecklistController {
    private final String strikethroughStyle = String.valueOf(ChecklistController.class.getResource(STRIKETHROUGH_FILE));
    @FXML
    public VBox vBox;
    @FXML
    public VBox checklist_Group;
    @FXML
    public Pane spacer;
    private String itemToAdd;

    /**
     * When clicking the add button:
     *         a popup shows to request the name of the checklist item.
     *         a checklist item gets made.
     *         the item gets added to the form.
     */
    @FXML
    protected void onAddButtonClick() {
        String itemName = requestItemName();
        HBox checklistItem = createItem(itemName);
        checklist_Group.getChildren().add(checklistItem);
    }

    private String requestItemName() {
        Stage popup = new Stage();
        Label questionLabel = new Label(POPUP_QUESTION);
        TextField answerField = new TextField();
        Button answerButton = new Button(ADD_TEXT);
        VBox popupContent = new VBox();

        popupContent.getChildren().addAll(questionLabel, answerField, answerButton);
        answerButton.setOnAction(e -> giveAnswer(answerField, popup));
        Scene popupScene = new Scene(popupContent, POPUP_HEIGHT, POPUP_WIDTH);
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

        Pane item_spacer = new Pane();
        HBox.setHgrow(item_spacer, Priority.ALWAYS);
        Label nameLabel = new Label(itemName);
        CheckBox checkBox = create_CheckBox(nameLabel, item);
        Button deleteButton = create_DeleteButton(item);
        item.getChildren().addAll(checkBox, nameLabel, item_spacer, deleteButton);
        item.setAccessibleText(UNCHECKED);
        return item;
    }

    private Button create_DeleteButton(HBox item) {
        Button button = new Button(DELETE_TEXT);
        EventHandler<MouseEvent> eventHandler = mouseEvent -> checklist_Group.getChildren().remove(item);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        button.setAlignment(Pos.CENTER_RIGHT);
        return button;
    }

    private CheckBox create_CheckBox(Label nameLabel, HBox item) {
        CheckBox checkBox = new CheckBox();

        checkBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            ObservableList<String> nameLabel_Stylesheets = nameLabel.getStylesheets();
            if (newValue) {
                nameLabel_Stylesheets.add(strikethroughStyle);
                item.setAccessibleText(CHECKED);
            } else {
                nameLabel_Stylesheets.remove(strikethroughStyle);
                item.setAccessibleText(UNCHECKED);
            }
        });
        return checkBox;
    }

    @FXML
    protected void onClearAllButtonClick() {
        checklist_Group.getChildren().clear();
    }

    @FXML
    protected void onClearButtonClick() {
        checklist_Group.getChildren().removeIf(checklist_item -> checklist_item.getAccessibleText().equals(CHECKED));
    }
}
