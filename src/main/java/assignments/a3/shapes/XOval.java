package assignments.a3.shapes;

import javafx.scene.paint.Paint;

public class XOval extends XShape {
    public XOval(double newLeft, double newTop, double newWidth, double newHeight, String shapeID, Paint color) {
        super(newLeft, newTop, newWidth, newHeight, shapeID, color);
    }

    @Override
    public boolean contains(double clickX, double clickY) {
        //TODO

        // oval = circle's width is scaled by X dimension
        if (width > height) {
            // get the invese of the scale factor
            double inverse = height / width;
            // temporarily conver the oval back to a circle
            double temp_width = width * inverse;
            // move the clickX to the  ellipse's temp circle
            double scaledX = clickX * inverse;
            System.out.println(scaledX);
            double radius = temp_width/ 2;
            return dist(scaledX, clickY, drawingX + radius, drawingY + radius) <= radius;
        }
        else if (height > width) { // oval = circle's height is scaled by Y dimension
            double inverse = width / height;
            double temp_height = height * inverse;
            double scaledY = clickY * inverse;
            double radius = temp_height / 2;
            return dist(clickX, scaledY,drawingX + radius, drawingY + radius) <= radius;
        }
        else { // the oval is the circle
            double radius = height / 2;
            return dist(clickX, clickY, drawingX + radius, drawingY + radius) <= radius;
        }
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
