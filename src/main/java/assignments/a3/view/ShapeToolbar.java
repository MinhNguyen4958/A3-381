package assignments.a3.view;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ShapeToolbar extends VBox {

    private Button rect_btn;
    private Button sqr_btn;
    private Button circl_btn;
    private Button oval_btn;
    private Button line_btn;





    public ShapeToolbar() {
        Rectangle rect_icon = new Rectangle(30, 20);
        VBox rect_box = new VBox(rect_icon, new Label("Rect"));
        rect_box.setAlignment(Pos.CENTER);
        rect_btn = new Button( "", rect_box);
        rect_btn.setPrefHeight(100);
        rect_btn.setPrefWidth(60);

        Rectangle sqr_icon = new Rectangle(25, 25);
        VBox sqr_box = new VBox(sqr_icon, new Label("Square"));
        sqr_box.setAlignment(Pos.CENTER);
        sqr_btn = new Button("", sqr_box);
        sqr_btn.setPrefHeight(100);
        sqr_btn.setPrefWidth(60);

        Circle circl_icon = new Circle(15.0);
        VBox circl_box = new VBox(circl_icon, new Label("Circle"));
        circl_box.setAlignment(Pos.CENTER);
        circl_btn = new Button("", circl_box);
        circl_btn.setPrefHeight(100);
        circl_btn.setPrefWidth(60);

        Ellipse oval_icon = new Ellipse(15, 10);
        VBox oval_box = new VBox(oval_icon, new Label("Oval"));
        oval_box.setAlignment(Pos.CENTER);
        oval_btn = new Button("",oval_box);
        oval_btn.setPrefHeight(100);
        oval_btn.setPrefWidth(60);

        Line line_icon = new Line(10, 0, 0, 30);
        VBox line_box = new VBox(line_icon, new Label("Line"));
        line_box.setAlignment(Pos.CENTER);
        line_btn = new Button("", line_box);
        line_btn.setPrefHeight(100);
        line_btn.setPrefWidth(60);
        this.getChildren().addAll(rect_btn, sqr_btn, circl_btn, oval_btn, line_btn);
    }
}
