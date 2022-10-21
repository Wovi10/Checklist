package com.wovi10.checklist.utils;

import com.wovi10.checklist.ChecklistController;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import static com.wovi10.checklist.Constants.ChecklistConstants.DELETE_TEXT;
import static com.wovi10.checklist.Constants.ChecklistConstants.STRIKETHROUGH_FILE;

/**
 * ChecklistItem
 * This class adds all utils for a checklist item.
 * You can create an item, delete it or mark it as completed.
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class ChecklistItem {
    private final VBox parent;
    private final String strikethroughStyle = String.valueOf(ChecklistController.class.getResource(STRIKETHROUGH_FILE));
    private final HBox item;
    private final Label nameLabel;
    private boolean isChecked;

    public ChecklistItem(String name, Checklist parent) {
        this.item = new HBox();
        this.nameLabel = new Label(name);
        CheckBox checkBox = create_CheckBox(nameLabel);
        this.parent = parent.getVisibleChecklist();
        Button deleteButton = create_deleteButton(item);
        isChecked = false;

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        item.getChildren().addAll(checkBox, nameLabel, spacer, deleteButton);
    }

    /**
     * Create delete button for specific checklist item.
     *
     * @param item The item to be deleted on button press.
     * @return The created button.
     */
    private Button create_deleteButton(HBox item) {
        Button button = new Button(DELETE_TEXT);
        EventHandler<MouseEvent> eventHandler = mouseEvent -> {
            changeState();
            parent.getChildren().remove(item);
        };
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        button.setAlignment(Pos.CENTER_RIGHT);
        return button;
    }

    /**
     * Create checkBox for completion.
     *
     * @param nameLabel The name to strikethrough.
     * @return The created checkBox.
     */
    private CheckBox create_CheckBox(Label nameLabel) {
        CheckBox checkBox = new CheckBox();

        checkBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            ObservableList<String> nameLabel_Stylesheets = nameLabel.getStylesheets();
            if (newValue) {
                nameLabel_Stylesheets.add(strikethroughStyle);
            } else {
                nameLabel_Stylesheets.remove(strikethroughStyle);
            }
            changeState();
        });
        return checkBox;
    }

    /**
     * Change the state of an item.
     */
    private void changeState() {
        isChecked = !isChecked;
    }
    //endregion

    //region Getters and Setters
    public HBox getItem() {
        return item;
    }

    public String getNameLabel() {
        return nameLabel.getText();
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public String toString() {
        return "nameLabel=" + nameLabel.getText() +
                ", isChecked=" + isChecked;
    }
}
