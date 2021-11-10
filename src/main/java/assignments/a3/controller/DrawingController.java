package assignments.a3.controller;

import assignments.a3.model.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class DrawingController {
    InteractionModel iModel;
    DrawingModel model;
    double prevX, prevY;

    protected enum State { READY, PREPARE_CREATE, RESIZING }

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

    public void handlePressed(double normX, double normY, MouseEvent event) {
        prevX = normX;
        prevY = normY;

        switch (currentState) {
            case READY -> {
                    /**
                     * context: none
                     * side effect: none
                     */
                    // move to new state
                    currentState = State.PREPARE_CREATE;
                }
            }

        }

    public void handleReleased(double normX, double normY, MouseEvent event) {
        switch (currentState) {
            case PREPARE_CREATE -> {
                /**
                 * context: none
                 * side effects: none
                 *
                 */
                // move back to ready state
                currentState = State.READY;
            }
            case RESIZING -> {
                /**
                 * context none
                 * side effects: add the selected shape to model.shapeList, then unselect
                 */
                model.addShape(iModel.getSelectedShape());
                iModel.unselect();
                //move to new state
                currentState = State.READY;
            }
        }
    }

    public void handleDragged(double normX, double normY, MouseEvent event, String shapeID) {
        double initialX = prevX;
        double initialY = prevY;

        double dX = normX - prevX;
        double dY = normY - prevY;
        prevX = normX;
        prevY = normY;
        switch (currentState) {
            case RESIZING -> {
                //TODO: figuring out on how to resize a shape

//                String shapeID =
                model.resizeShape(iModel.getSelectedShape(), dX, dY);
                model.addShape(iModel.getSelectedShape());

                // keep the current state
            }

            case PREPARE_CREATE -> {
                /**
                 * context: none
                 * side effect:
                 */
                iModel.setShape(model.createShape(initialX, initialY, 0, 0, iModel.getSelectedColor(), iModel.getSelectedButton().getShapeID()));
                currentState = State.RESIZING;
            }

        }
    }

    public void handleMoved(double normX, double normY, MouseEvent event) {}
}
