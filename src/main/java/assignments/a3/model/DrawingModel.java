package assignments.a3.model;

import assignments.a3.shapes.*;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class DrawingModel {

    private ArrayList<XShape> shapes;
    private ArrayList<DrawingModelSubscribers> subs;

    public DrawingModel() {
        shapes = new ArrayList<>();
        subs = new ArrayList<>();
    }
    // getter methods for shape lists
    public ArrayList<XShape> getShapes() {
        return shapes;
    }

    public void addSub(DrawingModelSubscribers newSub) {
        subs.add(newSub);
    }

    public void notifySubs() {
        subs.forEach(sub -> sub.modelChanged());
    }

    public XShape createShape(double x, double y, double newWidth, double newHeight, Paint newColor, String shapeID) {

        // create a  temporary shape based on the shapeID taken from shape buttons
        switch (shapeID) {
            case "Rect" -> { return new XRectangle(x, y, newWidth, newHeight); }
            case "Square" -> { return new XSquare(x, y, newWidth, newWidth); }
            case "Oval" -> { return new XOval(x, y, newWidth, newHeight); }
            case "Circle" -> { return new XCircle(x, y, newWidth, newWidth); }
            case "Line" ->  { return new XLine(x, y, x, y); }
        }

        return null;
    }

    public void addShape(XShape shape) {
        shapes.add(shape);
        notifySubs();
    }

    public void resizeShape(XShape shape, double newWidth, double newHeight) {
        double w = shape.getWidth();
        double h = shape.getHeight();
        w += newWidth;
        h += newHeight;
        shape.setHeight(h);
        shape.setWidth(w);
        notifySubs();
    }

    public boolean contains(double x, double y) {
        return shapes.stream().anyMatch(shape -> shape.contains(x, y));
    }

    public XShape whichShape(double x, double y) {
        return shapes.stream().filter(shape -> shape.contains(x, y)).findFirst().orElse(null);
    }

    public void moveShape(XShape shape, double dX, double dY) {
        shape.move(dX, dY);
        notifySubs();
    }

    public boolean hitEdge(double normX, double normY) {
        return false;
    }
}
