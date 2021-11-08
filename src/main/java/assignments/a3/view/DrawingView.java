package assignments.a3.view;

import assignments.a3.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class DrawingView extends Pane  {
    protected Canvas myCanvas;
    protected GraphicsContext gc;
    protected double width, height;

    public DrawingView(double w, double h) {
        width = w;
        height = h;
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        this.getChildren().add(myCanvas);
    }
}
