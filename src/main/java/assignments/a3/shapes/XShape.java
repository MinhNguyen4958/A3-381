package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public abstract class XShape {
    public double x, y, width, height;
    public Paint color;

    public XShape(double newLeft, double newTop, double newWidth, double newHeight, Paint newColor) {
        x = newLeft;
        y = newTop;
        width = newWidth;
        height = newHeight;
        color = newColor;
    }

    public abstract boolean contains(double x, double y);

    public abstract void move(double dX, double dY);

    protected double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
