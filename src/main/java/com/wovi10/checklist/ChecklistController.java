package com.wovi10.checklist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChecklistController {
    @FXML
    public VBox vBox;
    public Group checklist_Group;
    public Group group_Item;
    public CheckBox item_CB;
    public TextField item_Name;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onAddButtonClick() {
        createNewItem();
    }

    private void createNewItem() {
        checklist_Group.getChildren().add(group_Item);
    }
}
