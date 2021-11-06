package assignments.a3.model;
import assignments.a3.shapes.XShape;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class InteractionModel {
    XShape selectedShape;
    Paint selectedColor;
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
}
