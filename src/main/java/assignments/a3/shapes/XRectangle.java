package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public class XRectangle extends XShape {

    public XRectangle(double newleft, double newTop, double newWidth, double newHeight, Paint newColor) {
        super(newleft, newTop, newWidth, newHeight, newColor);
    }
    @Override
    public boolean contains(double x, double y) {
        return x >= this.left && x <= this.left + this.width && y >= this.top && y <=this.top + this.height;
    }

    @Override
    public void move(double dX, double dY) {
        this.left += dX;
        this.top += dY;
    }
}
