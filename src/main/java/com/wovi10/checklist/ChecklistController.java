package com.wovi10.checklist;

import com.wovi10.checklist.utils.Checklist;
import com.wovi10.checklist.utils.ChecklistItem;
import com.wovi10.checklist.utils.ChecklistPopup;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * ChecklistController
 * This class shows what happens when interacting with the checklist form.
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class ChecklistController {
    private final Checklist group = create_group();
    @FXML
    public VBox vBox;
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
        ChecklistItem item = createItem(itemName);
        group.addItem(item);
        vBox.getChildren().add(group.getChecklist());
    }

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

    //region 1.1 requestItemName

    /**
     * 1.2
     * Create checklist item.
     *
     * @param itemName The name of the item to add.
     * @return The created checklist item.
     */
    private ChecklistItem createItem(String itemName) {
        return new ChecklistItem(itemName, group);
    }
    //endregion

    //region 1.2 createItem

    private Checklist create_group() {
        return new Checklist();
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
        group.clearAll();
    }
    //endregion

    //region 3 Clear button

    /**
     * 3.0
     * Delete on completed items on button press.
     */
    @FXML
    protected void onClearButtonClick() {
        group.clearCompleted();
    }
    //endregion
}
