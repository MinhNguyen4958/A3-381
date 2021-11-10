package assignments.a3.view;

import assignments.a3.controller.DrawingController;
import assignments.a3.model.*;
import assignments.a3.shapes.XShape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

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

        myCanvas.widthProperty().addListener((observable, oldVal, newVal) -> {
            myCanvas.setWidth(newVal.doubleValue());
            draw();
        });

        myCanvas.heightProperty().addListener((observable, oldVal, newVal) -> {
            myCanvas.setHeight(newVal.doubleValue());
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
        gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        ArrayList<XShape> shape_list = model.getShapes();
        double canvasWidth = myCanvas.getWidth();
        double canvasHeight = myCanvas.getHeight();
        for (XShape shape : shape_list) {
            switch(shape.getID()) {
                case "Rect" -> {
                    gc.setFill(shape.getColor());
                    gc.strokeRect(shape.getX() * canvasWidth , shape.getY() * canvasHeight, shape.getWidth() * canvasWidth, shape.getHeight() * canvasHeight);
                    gc.fillRect(shape.getX() * canvasWidth , shape.getY() * canvasHeight, shape.getWidth() * canvasWidth, shape.getHeight() * canvasHeight);
                }

                case "Square" -> {
                    gc.setFill(shape.getColor());
                    gc.strokeRect(shape.getX() * canvasWidth , shape.getY() * canvasHeight, shape.getWidth() * canvasWidth, shape.getWidth() * canvasHeight);
                    gc.fillRect(shape.getX() * canvasWidth , shape.getY() * canvasHeight, shape.getWidth() * canvasWidth, shape.getWidth() * canvasHeight);

                }

                case "Oval" -> {
                    gc.setFill(shape.getColor());
                    gc.strokeOval(shape.getX() * canvasWidth, shape.getY() * canvasHeight, shape.getWidth() * canvasWidth, shape.getHeight() * canvasHeight);
                    gc.fillOval(shape.getX() * canvasWidth, shape.getY() * canvasHeight, shape.getWidth() * canvasWidth, shape.getHeight() * canvasHeight);
                }
                case "Circle" -> {
                    gc.setFill(shape.getColor());
                    gc.strokeOval(shape.getX() * canvasWidth, shape.getY() * canvasHeight, shape.getWidth() * canvasWidth, shape.getWidth() * canvasHeight);
                    gc.fillOval(shape.getX() * canvasWidth, shape.getY() * canvasHeight, shape.getWidth() * canvasWidth, shape.getWidth() * canvasHeight);
                }
                case "Line" -> {
                    gc.setStroke(shape.getColor());
                    gc.strokeLine(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
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
