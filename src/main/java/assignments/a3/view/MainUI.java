package assignments.a3.view;

import assignments.a3.model.*;
import assignments.a3.controller.*;


import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class MainUI extends BorderPane implements InteractionModelSubscriber, DrawingModelSubscribers {
    private ShapeToolbar leftbar;
    private ColourToolbar rightbar;
    private DrawingView canvasView;

    InteractionModel iModel;
    DrawingModel model;

    public MainUI() {
        leftbar = new ShapeToolbar();
        rightbar = new ColourToolbar();
        canvasView = new DrawingView(500, 500);
        this.setBackground(new Background( new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.setLeft(leftbar);
        this.setRight(rightbar);
        this.setCenter(canvasView);

    }

    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    public void setiModel(InteractionModel newiModel) {
        iModel = newiModel;
        iModel.setColor(Color.valueOf("Aqua"));
//        iModel.setShape();
    }

    public void setController(DrawingController controller) {
        canvasView.myCanvas.setOnMousePressed(e -> controller.handlePressed(e.getX() / canvasView.width, e.getY() / canvasView.height, e));
        canvasView.myCanvas.setOnMouseDragged(e -> controller.handleDragged(e.getX() / canvasView.width, e.getY() / canvasView.height, e));
        canvasView.myCanvas.setOnMouseMoved(e -> controller.handleMoved(e.getX() / canvasView.width, e.getY() / canvasView.height, e));
        canvasView.myCanvas.setOnMouseReleased(e -> controller.handleReleased(e.getX() / canvasView.width, e.getY() / canvasView.height, e));

        rightbar.getColorButtons().forEach(button -> button.setOnMouseClicked(e -> controller.setColor(Color.valueOf(button.textProperty().get()))));
        leftbar.getShapeButtons().forEach(button -> button.setOnMouseClicked(e -> controller.setShape(button.getShapeID())));
    }

    @Override
    public void modelChanged() {
        draw();
    }

    private void draw() {

    }

    @Override
    public void iModelChanged() {

    }
}
