package com.wovi10.checklist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.wovi10.checklist.Constants.ApplicationConstants.*;

public class ChecklistApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ChecklistApplication.class.getResource(FXML_FILE));
        Scene scene = new Scene(fxmlLoader.load(), PROGRAM_HEIGHT, PROGRAM_WIDTH);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
