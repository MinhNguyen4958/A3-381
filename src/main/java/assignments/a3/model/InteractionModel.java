package assignments.a3.model;
import assignments.a3.shapes.XShape;
import assignments.a3.view.ShapeButton;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class InteractionModel {
    private XShape selectedShape;
    private Paint selectedColor;
    private ShapeButton selectedButton;
    ArrayList<InteractionModelSubscriber> subs;
    double handleX, handleY, handleRadius;

    public void setHandleX(double handleX) {
        this.handleX = handleX;
    }

    public void setHandleY(double handleY) {
        this.handleY = handleY;
    }


    public double getHandleX() {
        return handleX;
    }

    public double getHandleY() {
        return handleY;
    }

    public double getHandleRadius() {
        return handleRadius;
    }


    public InteractionModel() {
        subs = new ArrayList<>();
        handleX = handleY = 0;
        handleRadius  = 0.0125;
    }

    public void addiSub(InteractionModelSubscriber newSub) {
        subs.add(newSub);
    }

    private void notifyiSubs() {
        subs.forEach(sub -> sub.iModelChanged());
    }

    public void setselectedShape(XShape newShape, DrawingModel model) {
        selectedShape = newShape;
        model.setNextZ(newShape);
        notifyiSubs();
    }

    public void setColor(Paint newColor) {
        selectedColor = newColor;
    }

    public void setButton(ShapeButton newButton) {
        selectedButton = newButton;
    }

    public XShape getSelectedShape() {
        return selectedShape;
    }

    public Paint getSelectedColor() {
        return selectedColor;
    }

    public ShapeButton getSelectedButton() { return selectedButton; }

    public void unselect() {
        selectedShape = null;
        notifyiSubs();
    }
    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public boolean handleHit(double normX, double normY) {
        boolean hit = dist(normX, normY, handleX + handleRadius, handleY + handleRadius) <= handleRadius;
        if (hit) {
            this.getSelectedShape().setCurrentX(normX);
            this.getSelectedShape().setCurrentY(normY);
            return true;
        }
        return false;
    }
}

