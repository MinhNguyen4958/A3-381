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

    public void addSub(DrawingModelSubscribers newSub) {
        subs.add(newSub);
    }

    public void notifySubs() {
        subs.forEach(sub -> sub.modelChanged());
    }

//    public ArrayList
}
