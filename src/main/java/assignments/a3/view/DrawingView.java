package assignments.a3.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class DrawingView extends Pane {
    private Canvas canvasView;
    private GraphicsContext gc;
    public DrawingView() {
        canvasView = new Canvas(500, 500);
        gc = canvasView.getGraphicsContext2D();
        this.getChildren().add(canvasView);
    }
}
