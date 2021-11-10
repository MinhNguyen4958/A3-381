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

    public XShape createShape(double x, double y, double newWidth, double newHeight, String shapeID, Paint newColor) {

        // create a  temporary shape based on the shapeID taken from shape buttons
        switch (shapeID) {
            case "Rect" -> { return new XRectangle(x, y, newWidth, newHeight, shapeID, newColor); }
            case "Square" -> { return new XSquare(x, y, newWidth, newWidth, shapeID, newColor); }
            case "Oval" -> { return new XOval(x, y, newWidth, newHeight, shapeID, newColor); }
            case "Circle" -> { return new XCircle(x, y, newWidth, newWidth, shapeID, newColor); }
            case "Line" ->  { return new XLine(x, y, x, y, shapeID, newColor); }
        }

        return null;
    }

    public void addShape(XShape shape) {
        shapes.add(shape);
        notifySubs();
    }

    public void resizeShape(XShape shape, double mouseX, double mouseY) {
        double w = shape.getWidth();
        double h = shape.getHeight();
        double currentX = shape.getX();
        double currentY = shape.getY();
        double dX = mouseX - currentX;
        double dY = mouseY - currentY;

        if
        System.out.println("this is mouseX: " + mouseX + " ,this is currentX: " + currentX + ", and this is initialX: " +shape.getInitialX());
        System.out.println("this is mouseY: " + mouseY+ " ,this is currentY: " + currentY + ", and this is initialY: " +shape.getInitialY());
        System.out.println();
        w += Math.abs(mouseX - currentX);
        h += Math.abs(mouseY - currentY);

        shape.setX(mouseX);
        shape.setY(mouseY);
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
