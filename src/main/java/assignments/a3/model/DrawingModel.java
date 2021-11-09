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

    public void createShape(double x, double y, double newWidth, double newHeight, Paint newColor, String shapeID) {
        // create a shape based on the shapeID taken from shape buttons
        switch (shapeID) {
            case "Rect":
                shapes.add(new XRectangle(x, y, newWidth, newHeight, newColor));
                break;
            case "Square":
                shapes.add(new XSquare(x, y, newWidth, newWidth, newColor));
                break;
            case "Oval":
                shapes.add(new XOval(x, y, newWidth, newHeight, newColor));
                break;
            case "Circle":
                shapes.add(new XCircle(x, y, newWidth, newWidth, newColor));
                break;
            case "Line":
                shapes.add(new XLine(x, y, newWidth, newHeight, newColor));
                break;
        }
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

    public void resizeShape(double newWidth, double newHeight) {

    }
}
