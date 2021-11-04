module assignments.a3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens assignments.a3 to javafx.fxml;
    exports assignments.a3;
}