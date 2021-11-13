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
    }

    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    protected void draw() {
        double canvasWidth = myCanvas.getWidth();
        double canvasHeight = myCanvas.getHeight();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        ArrayList<XShape> shape_list = model.getShapes();
        for (XShape shape : shape_list) {
            double drawingX = shape.getDrawingX();
            double drawingY = shape.getDrawingY();
            double shapeWidth = shape.getWidth();
            double shapeHeight = shape.getHeight();
            String ID = shape.getID();
            switch (ID) {
                case "Rect", "Square" -> {
                    gc.setFill(shape.getColor());
                    gc.fillRect(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                    gc.setStroke(Color.BLACK);
                    gc.setLineDashes(null);
                    gc.strokeRect(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                }

                case "Oval", "Circle" -> {
                    gc.setFill(shape.getColor());
                    gc.fillOval(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                    gc.setStroke(Color.BLACK);
                    gc.setLineDashes(null);
                    gc.strokeOval(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                }

                case "Line" -> {
                    gc.setStroke(shape.getColor());
                    gc.setLineDashes(null);
                    gc.strokeLine(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                }
            }

            if (shape == iModel.getSelectedShape()) {
                double radius = iModel.getHandleRadius();
                switch (ID) {
                    case "Rect", "Square", "Oval", "Circle" -> {
                        double[] dashPattern = {5, 5};
                        gc.setFill(Color.TRANSPARENT);
                        gc.setLineWidth(2.5);
                        gc.setLineDashes(dashPattern);
                        gc.setStroke(Color.RED);
                        gc.fillRect(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                        gc.strokeRect(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);


                        iModel.setHandleX((drawingX + shapeWidth - radius));
                        iModel.setHandleY((drawingY + shapeHeight - radius));

                    }
                    case "Line" -> {
                        double[] dashPattern = {5, 5};
                        gc.setLineDashes(dashPattern);
                        gc.setStroke(Color.RED);
                        gc.setLineWidth(1.5);
                        gc.strokeLine(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);


                        iModel.setHandleX((shapeWidth - radius));
                        iModel.setHandleY((shapeHeight - radius));
                    }
                }
                gc.setFill(Color.YELLOW);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1);
                gc.setLineDashes(null);
                gc.fillOval(iModel.getHandleX() * canvasWidth, iModel.getHandleY() * canvasHeight, iModel.getHandleRadius() * 2 * canvasWidth, iModel.getHandleRadius() * 2 * canvasHeight);
                gc.strokeOval(iModel.getHandleX() * canvasWidth, iModel.getHandleY() * canvasHeight, iModel.getHandleRadius() * 2 * canvasWidth, iModel.getHandleRadius() * 2 * canvasHeight);
            }
        }
    }

    public void setController(DrawingController controller) {
        myCanvas.setOnMousePressed(e -> controller.handlePressed(e.getX() / width, e.getY() / height, e));
        myCanvas.setOnMouseDragged(e -> controller.handleDragged(e.getX() / width, e.getY() / height, e, iModel.getSelectedButton().getShapeID()));
        myCanvas.setOnMouseMoved(e -> controller.handleMoved(e.getX() / width, e.getY() / height, e));
        myCanvas.setOnMouseReleased(e -> controller.handleReleased(e.getX() / width, e.getY() / height, e));
//        myCanvas.setOnKeyPressed();
    }

    @Override
    public void modelChanged() {
        draw();
    }

    @Override
    public void iModelChanged() {
        draw();
    }
}
