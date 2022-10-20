package com.wovi10.checklist.utils;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.wovi10.checklist.Constants.ChecklistConstants.CHECKED;

/**
 * Checklist
 * This class provides all utils for a checklist.
 * You can add items, clear everything or only clear the completed items
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class Checklist {

    private List<ChecklistItem> checklistItems = new ArrayList<>();
    private final VBox visibleChecklist;

    public Checklist() {
        this.visibleChecklist = new VBox();
        this.checklistItems = new ArrayList<>();
    }

    //region Getters and Setters
    public VBox getVisibleChecklist() {
        return visibleChecklist;
    }

    public List<ChecklistItem> getChecklistItems() {
        return checklistItems;
    }
    //endregion

    /**
     * If item is valid, add it to checklist.
     * @param item Item to add.
     */
    public void addItem(ChecklistItem item) {
        if (item != null){
            visibleChecklist.getChildren().add(item.getItem());
            checklistItems.add(item);
        }
    }

    /**
     * Clear all items from checklist.
     */
    public void clearAll() {
        visibleChecklist.getChildren().clear();
    }

    /**
     * Clear only completed items from checklist.
     */
    public void clearCompleted() {
        ObservableList<Node> visibleChecklist = this.visibleChecklist.getChildren();
        visibleChecklist.clear();
        for (ChecklistItem checklistItem : checklistItems) {
            boolean isCompleted = checklistItem.getChecked();
            if (isCompleted){
                checklistItems.remove(checklistItem);
            }else {
                visibleChecklist.add(checklistItem.getItem());
            }
        }
    }

    public void saveItems() throws IOException {
        FileWriter writer = new FileWriter("output.txt");
        for (ChecklistItem checklistItem : checklistItems) {
            writer.write(checklistItem.getNameLabel() + System.lineSeparator());
        }
        writer.close();
    }
}
