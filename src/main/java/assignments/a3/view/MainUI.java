package assignments.a3.view;

import assignments.a3.model.*;
import assignments.a3.controller.*;


import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class MainUI extends BorderPane {
    private ShapeToolbar leftbar;
    private ColourToolbar rightbar;
    private DrawingView middleview;

    InteractionModel iModel;
    DrawingModel model;

    public MainUI() {
        leftbar = new ShapeToolbar();
        rightbar = new ColourToolbar();
        middleview = new DrawingView();
        this.setBackground(new Background( new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.setLeft(leftbar);
        this.setRight(rightbar);
        this.setCenter(middleview);

    }

    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    public void setiModel(InteractionModel newiModel) {
        iModel = newiModel;
    }

    public void setController(DrawingController controller) {
        rightbar.getColorButtons().forEach(button -> button.setOnMouseClicked(e -> controller.setColor(Color.valueOf(button.textProperty().get()))));
//        leftbar.getShapeButtons().forEach(button -> button.setOnMouseClicked(e -> controller.setShape(button.getText())))
    }
}
