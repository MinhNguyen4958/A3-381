package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public class XRectangle extends XShape {

    public XRectangle(double newX, double newY, double newWidth, double newHeight, String shapeID, Paint color) {
        super(newX, newY, newWidth, newHeight, shapeID, color);
    }
    @Override
    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <=this.y + this.height;
    }

    @Override
    public void move(double dX, double dY) {
        this.x += dX;
        this.y += dY;
    }
}
