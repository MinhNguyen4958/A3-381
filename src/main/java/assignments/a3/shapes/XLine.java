package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public class XLine extends XShape {
    private double ratioA, ratioB, ratioC;
    private double length;
    private double tolerance;

    public XLine(double x1, double y1, double x2, double y2, String shapeID, Paint color) {
        // for lines drawingX = x1, drawingY = y1, width = x2, height = y2
        super(x1, y1, x2, y2, shapeID, color);

    }

    @Override
    public boolean contains(double clickX, double clickY) {
        // for lines drawingX = x1, drawingY = y1, width = x2, height = y2
        length = dist(drawingX, drawingY, width, height);
        ratioA = (drawingY - height) / length;
        ratioB = (width - drawingX) / length;
        ratioC = -1 * ((drawingY - height) * drawingX + (width - drawingX) * drawingY) / length;
        return Math.abs(distanceFromLine(clickX, clickY)) < 0.03;
    }

    private double distanceFromLine(double x, double y) {
        return ratioA * x + ratioB * y + ratioC;
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    @Override
    public void move(double mouseX, double mouseY) {
    }
}
