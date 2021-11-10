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
    double viewWidth, viewHeight;

    public InteractionModel() {
        subs = new ArrayList<>();
    }

    public void setViewSize(double w, double h) {
        viewWidth = w;
        viewHeight = h;
    }

    public void addiSub(InteractionModelSubscriber newSub) {
        subs.add(newSub);
    }

    private void notifyiSubs() {
        subs.forEach(sub -> sub.iModelChanged());
    }

    public void setShape(XShape newShape) {
        selectedShape = newShape;
        notifyiSubs();
    }

    public void setColor(Paint newColor) {
        selectedColor = newColor;
        notifyiSubs();
    }

    public void setButton(ShapeButton newButton) {
        selectedButton = newButton;
        notifyiSubs();
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
    }
}
