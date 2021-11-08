package assignments.a3.model;
import assignments.a3.shapes.XShape;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class InteractionModel {
    private XShape selectedShape;
    private Paint selectedColor;
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

    public void setShape(XShape newShape) {
        selectedShape = newShape;
        notifyiSubs();
    }

    public void setColor(Paint newColor) {
        selectedColor = newColor;
        notifyiSubs();
    }

    public XShape getSelectedShape() {
        return selectedShape;
    }

    public Paint getSelectedColor() {
        return selectedColor;
    }

    public void unselect() {
    }
}
