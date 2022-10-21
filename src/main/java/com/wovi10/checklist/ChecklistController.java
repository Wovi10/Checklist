package com.wovi10.checklist;

import com.wovi10.checklist.utils.Checklist;
import com.wovi10.checklist.utils.ChecklistItem;
import com.wovi10.checklist.utils.ChecklistPopup;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * ChecklistController
 * This class shows what happens when interacting with the checklist form.
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class ChecklistController {
    private final Checklist checklist = create_group();
    @FXML
    public VBox program;
    @FXML
    public Pane spacer;

    public void initialize() throws IOException {
        checklist.loadItems();
        program.getChildren().add(checklist.getVisibleChecklist());
    }

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
        if (!itemName.isEmpty()) {
            ChecklistItem item = createItem(itemName);
            checklist.addItem(item);
            boolean checklistIsInitialised = program.getChildren().contains(checklist.getVisibleChecklist());
            if (!checklistIsInitialised) {
                program.getChildren().add(checklist.getVisibleChecklist());
            }
        }
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
        return new ChecklistItem(itemName, checklist);
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
        checklist.clearAll();
    }
    //endregion

    //region 3 Clear button

    /**
     * 3.0
     * Delete on completed items on button press.
     */
    @FXML
    protected void onClearButtonClick() {
        checklist.clearCompleted();
    }

    public void onSaveButtonClick() throws IOException {
        checklist.saveItems();
    }
    //endregion
}
