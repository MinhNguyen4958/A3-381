package assignments.a3.view;

import assignments.a3.controller.DrawingController;
import assignments.a3.model.*;
import assignments.a3.shapes.XShape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DrawingView extends Pane implements DrawingModelSubscribers, InteractionModelSubscriber {
    protected Canvas myCanvas;
    protected GraphicsContext gc;
    protected double width, height;

    InteractionModel iModel;
    DrawingModel model;

    public DrawingView(double w, double h) {
        width = w;
        height = h;
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        this.getChildren().add(myCanvas);
        this.setBackground(new Background( new BackgroundFill(Color.LIGHTGRAY, null, null)));

        this.widthProperty().addListener((observable, oldVal, newVal) -> {
            myCanvas.setWidth(this.getWidth());
            draw();
        });

        this.heightProperty().addListener((observable, oldVal, newVal) -> {
            myCanvas.setHeight(this.getHeight());
            draw();
        });
    }

    public void setiModel(InteractionModel newiModel) {
        iModel = newiModel;
        iModel.setViewSize(myCanvas.getWidth(), myCanvas.getHeight());
    }

    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    private void draw() {
        double canvasWidth = myCanvas.getWidth();
        double canvasHeight = myCanvas.getHeight();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        ArrayList<XShape> shape_list = model.getShapes();
        for (XShape shape : shape_list) {
            double drawingX = shape.getDrawingX();
            double drawingY = shape.getDrawingY();
            double shapeWidth = shape.getWidth();
            double shapeHeight = shape.getHeight();
            switch(shape.getID()) {
                case "Rect", "Square" -> {
                    gc.setFill(shape.getColor());
                    gc.fillRect(drawingX * canvasWidth , drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(drawingX * canvasWidth , drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                }

                case "Oval", "Circle" -> {
                    gc.setFill(shape.getColor());
                    gc.fillOval(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                    gc.setStroke(Color.BLACK);
                    gc.strokeOval(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                }

                case "Line" -> {
                    gc.setStroke(shape.getColor());
                    gc.strokeLine(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                }
            }
        }
    }

    public void setController(DrawingController controller) {
        myCanvas.setOnMousePressed(e -> controller.handlePressed(e.getX() / width, e.getY() / height, e));
        myCanvas.setOnMouseDragged(e -> controller.handleDragged(e.getX() / width, e.getY() / height, e, iModel.getSelectedButton().getShapeID()));
        myCanvas.setOnMouseMoved(e -> controller.handleMoved(e.getX() / width, e.getY() / height, e));
        myCanvas.setOnMouseReleased(e -> controller.handleReleased(e.getX() / width, e.getY() / height, e));
    }

    @Override
    public void modelChanged() {
        draw();
    }

    @Override
    public void iModelChanged() {

    }
}
