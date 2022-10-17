package com.wovi10.checklist;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChecklistController {
    @FXML
    public VBox vBox;
    public VBox checklist_Group;
    public HBox group_Item;
    public CheckBox item_CB;
    public TextField item_Name;

    @FXML
    protected void onAddButtonClick() {
        HBox checklistItem = createItem();
        checklist_Group.getChildren().add(checklistItem);
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
