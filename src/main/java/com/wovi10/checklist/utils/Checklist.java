package com.wovi10.checklist.utils;

import com.wovi10.checklist.ChecklistController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

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
        visibleChecklist.getChildren().clear();
        for (ChecklistItem checklistItem : checklistItems) {
            boolean isCompleted = checklistItem.isChecked();
            if (isCompleted) {
                checklistItems.remove(checklistItem);
//                visibleChecklist.getChildren().remove(checklistItem.getItem());
            }else{
                visibleChecklist.getChildren().add(checklistItem.getItem());
            }
        }
    }

    public void saveItems() throws IOException {
        String fileLocation = String.valueOf(Objects.requireNonNull(ChecklistController.class.getResource("savedChecklist.txt")).getPath());
        File yourFile = new File(fileLocation);
        yourFile.createNewFile();
        System.out.printf("Writing to: %s \n", fileLocation);

        FileWriter writer = new FileWriter(fileLocation);
        for (ChecklistItem checklistItem : checklistItems) {
            if (!checklistItem.isChecked()){
                writer.write(checklistItem.getNameLabel() + System.lineSeparator());
            }
        }
        writer.close();
    }

    public void loadItems() throws IOException {
        String fileLocation = String.valueOf(Objects.requireNonNull(ChecklistController.class.getResource("savedChecklist.txt")).getPath());
        File yourFile = new File(fileLocation);
        boolean fileNotExisted = yourFile.createNewFile();
        if (!fileNotExisted) {
            try {
                File myObj = new File(fileLocation);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    ChecklistItem item = new ChecklistItem(data, this);
                    addItem(item);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
