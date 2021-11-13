package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public class XRectangle extends XShape {

    public XRectangle(double newX, double newY, double newWidth, double newHeight, String shapeID, Paint color) {
        super(newX, newY, newWidth, newHeight, shapeID, color);
    }
    @Override
    public boolean contains(double clickX, double clickY) {
        return clickX >= this.drawingX && clickX <= this.drawingX + this.width && clickY >= this.drawingY && clickY <=this.drawingY + this.height;
    }
}
