package assignments.a3.view;


import assignments.a3.shapes.XShape;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MiniDrawingView extends DrawingView  {

    public MiniDrawingView(double w, double h) {
        super(w, h);
        this.setBackground(new Background( new BackgroundFill(Color.DARKGRAY, null, null)));
        this.setMaxHeight(100);
        this.setMaxWidth(100);
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
                    gc.strokeRect(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
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
            if (shape == iModel.getSelectedShape()) {
                gc.setStroke(Color.RED);
                gc.setLineWidth(2.0);
                switch (ID) {
                    case "Rect", "Square", "Oval", "Circle" -> {
                        gc.setFill(Color.TRANSPARENT);
                        gc.fillRect(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                        gc.strokeRect(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);

                    }
                    case "Line" -> {
                        gc.strokeLine(drawingX * canvasWidth, drawingY * canvasHeight, shapeWidth * canvasWidth, shapeHeight * canvasHeight);
                    }
                }
            }
        }
    }

    @Override
    public void iModelChanged() {
        this.draw();
    }
}
