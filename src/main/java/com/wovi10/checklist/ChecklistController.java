package com.wovi10.checklist;

import com.wovi10.checklist.utils.ChecklistItem;
import com.wovi10.checklist.utils.ChecklistPopup;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import static com.wovi10.checklist.Constants.ChecklistConstants.CHECKED;

/**
 * ChecklistController
 * This class shows what happens when interacting with the checklist form.
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class ChecklistController {
    @FXML
    public VBox vBox;
    @FXML
    public VBox checklist_Group;
    @FXML
    public Pane spacer;

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
        ChecklistPopup popup = new ChecklistPopup();
        return popup.getItemToAdd();
    }
    //endregion

    //region 1.2 createItem
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
}
