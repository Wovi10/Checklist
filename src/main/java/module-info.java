module com.wovi10.checklist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wovi10.checklist to javafx.fxml;
    exports com.wovi10.checklist;
}