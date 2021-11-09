package assignments.a3.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;

public class ShapeButton extends ToggleButton {

    private String shapeID;
    private Shape shape_icon;

    public ShapeButton(String shape_txt, ToggleGroup toggleGroup) {
        shapeID = shape_txt;
        shape_icon = null;
        switch (shape_txt) {
            case "Rect": {
                shape_icon = new Rectangle(30, 20);
                break;
            }
            case "Square": {
                shape_icon = new Rectangle(25, 25);
                break;
            }
            case "Circle": {
                shape_icon = new Circle(15.0);
                break;
            }
            case "Oval": {
                shape_icon = new Ellipse(15, 10);
                break;
            }
            case "Line": {
                shape_icon = new Line(10, 0, 0, 30);
                break;
            }
        }

        VBox btn_box = new VBox(shape_icon, new Label(shape_txt));
        btn_box.setAlignment(Pos.CENTER);

        this.setText("");
        this.setGraphic(btn_box);

        this.setPrefHeight(100);
        this.setPrefWidth(60);
        this.setToggleGroup(toggleGroup);
    }

    public String getShapeID() { return shapeID; }
    public Shape getShapeIcon() { return shape_icon; }

}
