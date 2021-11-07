package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public abstract class XShape {
    public double left, top, width, height;
    public Paint color;

    public XShape(double newLeft, double newTop, double newWidth, double newHeight, Paint newColor) {
        left = newLeft;
        top = newTop;
        width = newWidth;
        height = newHeight;
        color = newColor;
    }

    public abstract boolean contains(double x, double y);

    public abstract void move(double dX, double dY);
}
