package assignments.a3.controller;

import assignments.a3.model.*;
import javafx.scene.paint.Paint;

public class DrawingController {
    InteractionModel iModel;
    DrawingModel model;
    double prevX, prevY;



    protected enum State {READY, DRAGGING}

    private State currentState;

    public DrawingController() {
        currentState = State.READY;
    }

    public void setInteractionModel(InteractionModel newiModel) {
        iModel = newiModel;
    }

    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    public void setColor(Paint newColor) {
        iModel.setColor(newColor);
    }

    public void setShape(String shape) {
    }
}
