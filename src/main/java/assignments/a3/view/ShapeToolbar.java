package assignments.a3.view;


import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ShapeToolbar extends VBox {

    private ShapeButton rect_btn;
    private ShapeButton sqr_btn;
    private ShapeButton circl_btn;
    private ShapeButton oval_btn;
    private ShapeButton line_btn;

    private ArrayList<ShapeButton> shape_btns;


    public ArrayList<ShapeButton> getShapeButtons() {
        return shape_btns;
    }

    public ShapeToolbar() {

        ToggleGroup shape_group = new ToggleGroup();
        shape_btns = new ArrayList<>();

        // set up the rect button
        rect_btn = new ShapeButton("Rect", shape_group);
        shape_btns.add(rect_btn);


        // set up the square button
        sqr_btn = new ShapeButton("Square", shape_group);
        shape_btns.add(sqr_btn);

        // set up the circle button
        circl_btn = new ShapeButton("Circle", shape_group);
        shape_btns.add(circl_btn);

//        // set up the oval button
        oval_btn = new ShapeButton("Oval", shape_group);
        shape_btns.add(oval_btn);

        // set up the line button
        line_btn = new ShapeButton("Line", shape_group);

        shape_btns.add(line_btn);

        // enables resizing for the buttons
        this.heightProperty().addListener((observable, oldValue, NewValue) -> {
            shape_btns.forEach(button -> button.setPrefHeight(this.getHeight() / 5));
        });

        for (ShapeButton shape_btn : shape_btns) {
            this.getChildren().add(shape_btn);
            shape_btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.0))));
            shape_btn.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (!shape_btn.isSelected()) {
                    shape_btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
                    shape_btn.getShapeIcon().setFill(Color.BLACK);
                    shape_btn.getShapeIcon().setStroke(Color.BLACK);
                }
            });
        }
        // on startup the rectangle button is selected
        shape_btns.get(0).setSelected(true);



    }
}
