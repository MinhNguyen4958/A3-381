package assignments.a3.shapes;


import javafx.scene.paint.Paint;

public class XSquare extends XShape {
    public XSquare(double newLeft, double newTop, double newWidth, String shapeID, Paint color) {
        super(newLeft, newTop, newWidth, newWidth, shapeID, color);
    }

    @Override
    public boolean contains(double clickX, double clickY) {
        return clickX >= this.drawingX && clickX <= this.drawingX + this.width && clickY >= this.drawingY && clickY <= this.drawingY + this.height;
    }
}
