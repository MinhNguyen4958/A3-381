package assignments.a3.controller;

import assignments.a3.model.*;
import assignments.a3.shapes.XShape;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class DrawingController {
    InteractionModel iModel;
    DrawingModel model;
    double initialX, initialY;

    protected enum State { READY, PREPARE_CREATE, RESIZING, SELECTED, MOVING, INITIAL_RESIZE }

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
        initialX = normX;
        initialY = normY;

        switch (currentState) {
            case READY -> {
                /**
                 * context: on a shape
                 * side effect: set selection
                 */
                boolean hit = model.contains(normX, normY);
                if (hit) {
                    iModel.setselectedShape(model.whichShape(normX, normY));
                    // move to next state
                    currentState = State.SELECTED;
                } else {
                    /**
                     * context: none
                     * side effect: none
                     */

                    // move to new state
                    currentState = State.PREPARE_CREATE;
                }
            }

            case SELECTED -> {
                boolean anotherHit = model.contains(normX, normY);
                if (anotherHit) {
                    /**
                     * context: on another shape
                     * side effect: set new shape selection
                     */
                    iModel.setselectedShape(model.whichShape(normX, normY));
                    //stay on this state
                } else {
                    /**
                     * context: none (on background)
                     * side effect; none
                     */
                    //move to prepare create
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
                 * side effects: unselect shape
                 */
                iModel.unselect();
                // move back to ready state
                currentState = State.READY;
            }

            case RESIZING, MOVING, INITIAL_RESIZE -> {
                /**
                 * context: none
                 * side effects: none
                 */
                //move to new state
                currentState = State.SELECTED;
            }
        }
    }

    public void handleDragged(double normX, double normY, MouseEvent event, String shapeID) {
        switch (currentState) {
            case INITIAL_RESIZE -> {
                model.resizeShape(iModel.getSelectedShape(), normX, normY);
            }

            case RESIZING -> {
                /**
                 * context: on selected shape's handle
                 * side effect: resize the shape
                 */
                boolean handleHit = iModel.handleHit(normX, normY);
                if (handleHit) {
                    model.resizeShape(iModel.getSelectedShape(), normX, normY);
                } else {
                    currentState = State.MOVING;
                }
                // keep the current state
            }

            case MOVING -> {
                model.moveShape(iModel.getSelectedShape(), normX, normY);
            }

            case PREPARE_CREATE -> {
                /**
                 * context: none
                 * side effect:
                 */
                XShape newShape = model.createShape(initialX, initialY, 0, 0, iModel.getSelectedButton().getShapeID(), iModel.getSelectedColor());
                iModel.setselectedShape(newShape);
                model.addShape(newShape);
                currentState = State.INITIAL_RESIZE;
            }

        }
    }

    public void handleMoved(double normX, double normY, MouseEvent event) {}
}
