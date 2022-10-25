package com.wovi10.checklist.utils;

import com.wovi10.checklist.ChecklistController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private final List<ChecklistItem> checklistItems;

    public Checklist() {
        this.visibleChecklist = new VBox();
        this.checklistItems = new ArrayList<>();
    }

    //region Getters and Setters
    public VBox getVisibleChecklist() {
        return visibleChecklist;
    }

    //endregion

    /**
     * Clear all items from checklist.
     */
    public void clearAll() {
        visibleChecklist.getChildren().clear();
        checklistItems.clear();
    }

    /**
     * Clear only completed items from checklist.
     */
    public void clearCompleted() {
        List<ChecklistItem> itemsToRemove = new ArrayList<>();
        for (ChecklistItem checklistItem : checklistItems) {
            boolean isCompleted = checklistItem.isChecked();
            if (isCompleted) {
                itemsToRemove.add(checklistItem);
                visibleChecklist.getChildren().remove(checklistItem.getItem());
            }
        }
        checklistItems.removeAll(itemsToRemove);
    }

    public void saveItems() throws IOException {
        String fileLocation = String.valueOf(Objects.requireNonNull(ChecklistController.class.getResource("savedChecklist.txt")).getPath());
        File yourFile = new File(fileLocation);
        yourFile.createNewFile();
        Debug.fileDebug_Print(DebugHelp.To, fileLocation);

        FileWriter writer = new FileWriter(fileLocation);
        for (ChecklistItem checklistItem : checklistItems) {
            if (!checklistItem.isChecked()) {
                writer.write(checklistItem.getNameLabel() + System.lineSeparator());
            }
        }
        writer.close();
        showSavedPopup();
    }

    /**
     * Shows popup to mention to the user save has happened
     */
    private static void showSavedPopup() {
        Stage popupBox = new Stage();
        Label message = new Label("Document saved");
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> popupBox.close());
        VBox popupContent = new VBox();
        popupContent.getChildren().addAll(message, okButton);
        Scene scene = new Scene(popupContent, 100, 100);
        popupBox.setScene(scene);
        popupBox.showAndWait();
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
                Debug.fileNotFound_error_Print(e);
            }
        }
    }

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
}
