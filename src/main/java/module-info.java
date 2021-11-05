module assignments.a3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens assignments.a3 to javafx.fxml;
    exports assignments.a3;
    exports assignments.a3.shapes;
    opens assignments.a3.shapes to javafx.fxml;
    exports assignments.a3.view;
    opens assignments.a3.view to javafx.fxml;
    exports assignments.a3.model;
    opens assignments.a3.model to javafx.fxml;
    exports assignments.a3.controller;
    opens assignments.a3.controller to javafx.fxml;
}