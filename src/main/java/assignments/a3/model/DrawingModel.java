package assignments.a3.model;

import assignments.a3.shapes.XShape;

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

    public boolean contains(double x, double y) {
        return shapes.stream().anyMatch(shape -> shape.contains(x, y));
    }

    public XShape whichShape(double x, double y) {
        XShape foundShape = null;

        for (XShape shape : shapes) {
            if (shape.contains(x, y)) {
                foundShape = shape;
            }
        }
        return foundShape;
    }

    public void moveShape(XShape shape, double dX, double dY) {
        shape.move(dX, dY);
        notifySubs();
    }

    public boolean hitEdge(double normX, double normY) {
        return false;
    }

    public void createShape(double normX, double normY) {
    }
}
