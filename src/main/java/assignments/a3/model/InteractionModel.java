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

    public InteractionModel() {
        subs = new ArrayList<>();
    }

    public void addiSub(InteractionModelSubscriber newSub) {
        subs.add(newSub);
    }

    private void notifyiSubs() {
        subs.forEach(sub -> sub.iModelChanged());
    }

    public void setselectedShape(XShape newShape) {
        selectedShape = newShape;
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
    }

    public boolean handleHit(double normX, double normY) {
        return false;
    }

    // wrapper method to draw the border and the handle around the selected shape
    public void shapeSizeChanged() {
        notifyiSubs();
    }
}
