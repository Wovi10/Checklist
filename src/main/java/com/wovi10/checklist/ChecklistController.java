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

    //region 1 Add button
    /**
     * 1
     * When clicking the add button:
     * Show popup to request the name of the checklist item.
     * Create checklist item
     * the item gets added to the form.
     */
    @FXML
    protected void onAddButtonClick() {
        String itemName = requestItemName();
        HBox item = createItem(itemName);
        checklist_Group.getChildren().add(item);
    }

    //region 1.1 requestItemName
    /**
     * 1.1
     * Show popup to request the name of the checklist item.
     *
     * @return Name of the item to add in String format
     */
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

    /**
     * 1.1.1
     * Read the name of the item to add and set it for later use.
     *
     * @param answerLabel TextField in which the answer is put.
     * @param popup       The popup so it can be closed after setting the answer.
     */
    private void giveAnswer(TextField answerLabel, Stage popup) {
        itemToAdd = answerLabel.getText();
        popup.close();

    }
    //endregion

    /**
     * 1.2
     * Create checklist item.
     *
     * @param itemName The name of the item to add.
     * @return The created checklist item.
     */
    private HBox createItem(String itemName) {
        ChecklistItem item = new ChecklistItem(itemName, checklist_Group);
        return item.getItem();
    }
    //endregion

    //region 2 Clear all button
    /**
     * 2.0
     * Delete all checklist items on button press.
     */
    @FXML
    protected void onClearAllButtonClick() {
        checklist_Group.getChildren().clear();
    }
    //endregion

    //region 3 Clear button
    /**
     * 3.0
     * Delete on completed items on button press.
     */
    @FXML
    protected void onClearButtonClick() {
        checklist_Group.getChildren().removeIf(checklist_item -> checklist_item.getAccessibleText().equals(CHECKED));
    }
    //endregion

    /**
     * util
     * Change the state of an item.
     * @param item The item which has to be changed.
     */
    private void changeItemState(HBox item) {
        boolean itemIsChecked = item.getAccessibleText().equals(CHECKED);
        if (!itemIsChecked) {
            item.setAccessibleText(CHECKED);
        } else {
            item.setAccessibleText(UNCHECKED);
        }
    }
}
