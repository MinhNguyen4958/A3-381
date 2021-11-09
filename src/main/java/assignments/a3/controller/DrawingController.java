package assignments.a3.controller;

import assignments.a3.model.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class DrawingController {
    InteractionModel iModel;
    DrawingModel model;
    double prevX, prevY;

    protected enum State { READY, MOVING, PREPARE_CREATE, RESIZING }

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

    public void setShape(String shapeID) {

    }

    public void handlePressed(double normX, double normY, MouseEvent event) {
        prevX = normX;
        prevY = normY;

        switch (currentState) {
            case READY -> {
                /**
                 * context: on a shape
                 * side effects: set selection
                 */
                boolean hitShape = model.contains(normX, normY);
                boolean hitEdge = model.hitEdge(normX, normY);
                if (hitShape) {
                    //side effect: set selection
                    iModel.setShape(model.whichShape(normX, normY));
                    // move to new state
                    currentState = State.MOVING;
                } else if (hitEdge) {
                    /**
                     * context: on a shape's bottom right edge
                     * side effect: set selection
                     */
                    iModel.setShape(model.whichShape(normX, normY));
                    // move to next state
                    currentState = State.RESIZING;
                } else {
                    /**
                     * context: none
                     * side effect: none
                     */
                    // move to new state
                    currentState = State.PREPARE_CREATE;
                }
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
            case MOVING, RESIZING -> {
                /**
                 * context none
                 * side effects: unselected
                 */
                iModel.unselect();
                //move to new state
                currentState = State.READY;
            }
        }
    }

    public void handleDragged(double normX, double normY, MouseEvent event, String shapeID) {
        double dX = normX - prevX;
        double dY = normY - prevY;
        prevX = normX;
        prevY = normY;

        switch (currentState) {
            case MOVING -> {
                /**
                 * context: on a shape
                 * set effect: move the shape
                 */
                // stay in this state
                model.moveShape(iModel.getSelectedShape(), dX, dY);
            }

            case PREPARE_CREATE -> {
                model.createShape(normX, normY, dX, dY, iModel.getSelectedColor(), iModel.getSelectedButton().getShapeID());
                currentState = State.RESIZING;
            }

            case RESIZING -> {
                //TODO: figuring out on how to resize a shape

            }
        }
    }

    public void handleMoved(double normX, double normY, MouseEvent event) {}
}
