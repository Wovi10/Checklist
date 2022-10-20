package com.wovi10.checklist.utils;

import javafx.scene.layout.VBox;

import static com.wovi10.checklist.Constants.ChecklistConstants.CHECKED;

/**
 * Checklist
 * This class provides all utils for a checklist.
 * You can add items, clear everything or only clear the completed items
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class Checklist {

    private final VBox checklist;

    public Checklist() {
        this.checklist = new VBox();
    }

    //region Getters and Setters
    public VBox getChecklist() {
        return checklist;
    }
    //endregion

    /**
     * If item is valid, add it to checklist.
     * @param item Item to add.
     */
    public void addItem(ChecklistItem item) {
        if (item != null){
            checklist.getChildren().add(item.getItem());
        }
    }

    /**
     * Clear all items from checklist.
     */
    public void clearAll() {
        checklist.getChildren().clear();
    }

    /**
     * Clear only completed items from checklist.
     */
    public void clearCompleted() {
        checklist.getChildren().removeIf(item -> item.getAccessibleText().equals(CHECKED));
    }
}
