package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public class XLine extends XShape {
    private double ratioA, ratioB, ratioC;
    private double length;
    private double tolerance;

    public XLine(double x1, double y1, double x2, double y2) {
        // for lines left = x1, top = y1, width = x2, height = y2
        super(x1, y1, x2, y2);
        tolerance = 10;
        length = dist(x1, y1, x2, y2);
        ratioA = (y1 -y2) / length;
        ratioB = (x2 -x1) / length;
        ratioC = -1 * ((y1 - y2) * x1 + (x2 - x1) * y1) / length;
    }

    @Override
    public boolean contains(double x, double y) {
        return Math.abs(distanceFromLine(x, y)) < tolerance && dist(x, y, this.x, this.y) < length && dist(x, y, width, height) < length;
    }

    private double distanceFromLine(double x, double y) {
        return ratioA * x + ratioB * y + ratioC;
    }

    @Override
    public void move(double dX, double dY) {
    }
}
