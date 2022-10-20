package com.wovi10.checklist.utils;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.wovi10.checklist.Constants.ChecklistConstants.SAVING_FILE_NAME;

/**
 * Checklist
 * This class provides all utils for a checklist.
 * You can add items, clear everything or only clear the completed items
 *
 * @author - Wout Vinckevleugel (Wovi10)
 */
public class Checklist {

    private final VBox visibleChecklist;
    private List<ChecklistItem> checklistItems = new ArrayList<>();

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
     *
     * @param item Item to add.
     */
    public void addItem(ChecklistItem item) {
        if (item != null) {
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
            if (isCompleted) {
                checklistItems.remove(checklistItem);
            } else {
                visibleChecklist.add(checklistItem.getItem());
            }
        }
    }

    public void saveItems() throws IOException {
        String fileLocation = SAVING_FILE_NAME;
        File yourFile = new File(fileLocation);
        boolean fileNotExisted = yourFile.createNewFile();
        if (fileNotExisted) {
            System.out.printf("File created at: %s", fileLocation);
        } else {
            System.out.printf("Writing to: %s", fileLocation);
        }

        FileWriter writer = new FileWriter(fileLocation);
        for (ChecklistItem checklistItem : checklistItems) {
            writer.write(checklistItem.getNameLabel() + System.lineSeparator());
        }
        writer.close();
    }
}
