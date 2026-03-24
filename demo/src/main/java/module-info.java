module com.trial {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens com.trial to javafx.fxml;
    exports com.trial;
}
