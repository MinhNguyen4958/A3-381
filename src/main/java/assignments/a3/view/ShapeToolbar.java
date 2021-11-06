package assignments.a3.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ShapeToolbar extends VBox {

    private ToggleButton rect_btn;
    private ToggleButton sqr_btn;
    private ToggleButton circl_btn;
    private ToggleButton oval_btn;
    private ToggleButton line_btn;

    private ArrayList<ToggleButton> shape_btns;


    public ArrayList<ToggleButton> getShapeButtons() {
        return shape_btns;
    }

    public ShapeToolbar() {

        ToggleGroup shape_group = new ToggleGroup();
        shape_btns = new ArrayList<>();

        // set up the rect button
        Rectangle rect_icon = new Rectangle(30, 20);
        VBox rect_box = new VBox(rect_icon, new Label("Rect"));
        rect_box.setAlignment(Pos.CENTER);
        rect_btn = new ToggleButton( "", rect_box);
        rect_btn.setPrefHeight(100);
        rect_btn.setPrefWidth(60);
        rect_btn.setToggleGroup(shape_group);
        shape_btns.add(rect_btn);


        // set up the square button
        Rectangle sqr_icon = new Rectangle(25, 25);
        VBox sqr_box = new VBox(sqr_icon, new Label("Square"));
        sqr_box.setAlignment(Pos.CENTER);
        sqr_btn = new ToggleButton("", sqr_box);
        sqr_btn.setPrefHeight(100);
        sqr_btn.setPrefWidth(60);
        sqr_btn.setToggleGroup(shape_group);
        shape_btns.add(sqr_btn);

        // set up the circle button
        Circle circl_icon = new Circle(15.0);
        VBox circl_box = new VBox(circl_icon, new Label("Circle"));
        circl_box.setAlignment(Pos.CENTER);
        circl_btn = new ToggleButton("", circl_box);
        circl_btn.setPrefHeight(100);
        circl_btn.setPrefWidth(60);
        circl_btn.setToggleGroup(shape_group);
        shape_btns.add(circl_btn);


        // set up the oval button
        Ellipse oval_icon = new Ellipse(15, 10);
        VBox oval_box = new VBox(oval_icon, new Label("Oval"));
        oval_box.setAlignment(Pos.CENTER);
        oval_btn = new ToggleButton("",oval_box);
        oval_btn.setPrefHeight(100);
        oval_btn.setPrefWidth(60);
        oval_btn.setToggleGroup(shape_group);
        shape_btns.add(oval_btn);

        // set up the line button
        Line line_icon = new Line(10, 0, 0, 30);
        VBox line_box = new VBox(line_icon, new Label("Line"));
        line_box.setAlignment(Pos.CENTER);
        line_btn = new ToggleButton("", line_box);
        line_btn.setPrefHeight(100);
        line_btn.setPrefWidth(60);
        line_btn.setToggleGroup(shape_group);
        shape_btns.add(line_btn);

        // enables resizing for the buttons
        this.heightProperty().addListener((observable, oldValue, NewValue) -> {
            shape_btns.forEach(button -> button.setPrefHeight(this.getHeight() / 5));
        });

        for (ToggleButton shape_btn : shape_btns) {
            shape_btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.0))));
            shape_btn.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (shape_btn.isSelected()) {
                    shape_btn.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
                } else {
                    shape_btn.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.0))));
                }
            });
        }
        // on startup the rectangle button is selected
        shape_btns.get(0).setSelected(true);

        this.getChildren().addAll(rect_btn, sqr_btn, circl_btn, oval_btn, line_btn);
    }
}
