package com.wovi10.checklist;

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

import static com.wovi10.checklist.ChecklistConstants.*;

public class ChecklistItem {
    public HBox item;
    private final VBox parent;
    private final String strikethroughStyle = String.valueOf(ChecklistController.class.getResource(STRIKETHROUGH_FILE));

    protected ChecklistItem(String name, VBox parent) {
        item = new HBox();
        Label nameLabel = new Label(name);
        CheckBox checkBox = create_CheckBox(nameLabel);
        this.parent = parent;
        Button deleteButton = create_deleteButton(item);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        item.getChildren().addAll(checkBox, nameLabel, spacer, deleteButton);
        item.setAccessibleText(UNCHECKED);
    }

    //region Getters and Setters
    protected HBox getItem() {
        return item;
    }
    //endregion

    /**
     * Create delete button for specific checklist item.
     *
     * @param item The item to be deleted on button press.
     * @return The created button.
     */
    private Button create_deleteButton(HBox item) {
        Button button = new Button(DELETE_TEXT);
        EventHandler<MouseEvent> eventHandler = mouseEvent -> parent.getChildren().remove(item);
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
    protected void changeState() {
        boolean itemIsChecked = item.getAccessibleText().equals(CHECKED);
        if (!itemIsChecked) {
            item.setAccessibleText(CHECKED);
        } else {
            item.setAccessibleText(UNCHECKED);
        }
    }
}
