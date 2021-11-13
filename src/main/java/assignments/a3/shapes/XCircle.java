package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public class XCircle extends XShape {
    private double radius;
    public XCircle(double newLeft, double newTop, double newWidth, String shapeID, Paint color) {
        super(newLeft, newTop, newWidth, newWidth, shapeID, color);
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public boolean contains(double clickX, double clickY) {
        radius = width / 2;
        return dist(clickX, clickY, drawingX + radius, drawingY + radius) <= radius;
    }
}
