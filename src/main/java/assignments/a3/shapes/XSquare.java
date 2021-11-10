package assignments.a3.shapes;


import javafx.scene.paint.Paint;

public class XSquare extends XShape {
    public XSquare(double newLeft, double newTop, double newWidth, double newHeight) {
        super(newLeft, newTop, newWidth, newHeight);
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public void move(double dX, double dY) {

    }
}
