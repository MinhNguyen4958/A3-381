package assignments.a3.model;

import assignments.a3.shapes.*;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class DrawingModel {

    private ArrayList<XShape> shapes;
    private ArrayList<DrawingModelSubscribers> subs;

    public DrawingModel() {
        shapes = new ArrayList<>();
        subs = new ArrayList<>();
    }
    // getter methods for shape lists
    public ArrayList<XShape> getShapes() {
        return shapes;
    }

    public void addSub(DrawingModelSubscribers newSub) {
        subs.add(newSub);
    }

    public void notifySubs() {
        subs.forEach(sub -> sub.modelChanged());
    }

    public XShape createShape(double x, double y, double newWidth, double newHeight, String shapeID, Paint newColor) {

        // create a  temporary shape based on the shapeID taken from shape buttons
        switch (shapeID) {
            case "Rect" -> { return new XRectangle(x, y, newWidth, newHeight, shapeID, newColor); }
            case "Square" -> { return new XSquare(x, y, newWidth, shapeID, newColor); }
            case "Oval" -> { return new XOval(x, y, newWidth, newHeight, shapeID, newColor); }
            case "Circle" -> { return new XCircle(x, y, newWidth, shapeID, newColor); }
            case "Line" ->  { return new XLine(x, y, x, y, shapeID, newColor); }
        }

        return null;
    }

    public void addShape(XShape shape) {
        shapes.add(shape);
        notifySubs();
    }

    public void resizeShape(XShape shape, double mouseX, double mouseY) {
        double w = shape.getWidth();
        double h = shape.getHeight();

        double initialX = shape.getInitialX();
        double initialY = shape.getInitialY();

        if (mouseX - initialX >= 0) {
            if (mouseY - initialY >= 0) {
                // 1st quadrant
                shape.setWidth(0);
                shape.setHeight(0);
                double dX = mouseX - shape.getCurrentX();
                double dY = mouseY - shape.getCurrentY();

                if (shape.getID().equals("Rect") || shape.getID().equals("Oval")) {
                    if (dX >= 0) {
                        w += dX;
                    } else if (dX <= 0) {
                        w -= Math.abs(dX);
                    }

                    if (dY >= 0) {
                        h += dY;
                    } else if (dY <= 0) {
                        h -= Math.abs(dY);
                    }
                } else if (shape.getID().equals("Square") || shape.getID().equals("Circle")) {
                    if (dX >= 0) {
                        w += dX;
                        h += dX;
                    } else if (dX <= 0) {
                        w -= Math.abs(dX);
                        h -= Math.abs(dX);
                    }

                    if (dY >= 0) {
                        h += dY;
                        w += dY;
                    } else if (dY <= 0) {
                        h -= Math.abs(dY);
                        w -= Math.abs(dY);
                    }
                }
                shape.setWidth(w);
                shape.setHeight(h);
                shape.setCurrentX(mouseX);
                shape.setCurrentY(mouseY);

                // the drawing coords should be the same as initials coords for this quadrant

            } else if (mouseY - initialY <= 0) {

                // 2nd quadrant
                shape.setWidth(0);
                shape.setHeight(0);

                double dX = mouseX - shape.getCurrentX();
                double dY = mouseY - shape.getCurrentY();

                // calculate width, height and determine the drawing location of Y coord
                if (shape.getID().equals("Rect") || shape.getID().equals("Oval")) {
                    if (dX <= 0) {
                        w -= Math.abs(dX);
                    } else if (dX >= 0) {
                        w += dX;
                    }
                    if (dY <= 0) {
                        h += Math.abs(dY);
//                        drawingY -= Math.abs(dY);
                    } else if (dY >= 0) {
                        h -= dY;
//                        drawingY += dY;
                    }

                } else if (shape.getID().equals("Square") || shape.getID().equals("Circle")) {
                    if (dX <= 0) {
                        w -= Math.abs(dX);
                        h -= Math.abs(dX);
                    } else if (dX >= 0) {
                        w += dX;
                        h += dX;
                    }

                    if (dY <= 0) {
                        h += Math.abs(dY);
                        w += Math.abs(dY);
                    } else if (dY >= 0) {
                        h -= dY;
                        w -= dY;
                    }
                }
                // reposition the drawing coords
                double drawingY = initialY - h;

                shape.setDrawingY(drawingY);

                shape.setWidth(w);
                shape.setHeight(h);

                shape.setCurrentX(mouseX);
                shape.setCurrentY(mouseY);
            }
        } else if (mouseX - initialX <= 0) {
            if (mouseY - initialY <= 0) {
                // 3rd quadrant
                shape.setWidth(0);
                shape.setHeight(0);

                double dX = mouseX - shape.getCurrentX();
                double dY = mouseY - shape.getCurrentY();

                // calculate width, height
                if (shape.getID().equals("Rect") || shape.getID().equals("Oval")) {
                    if (dX <= 0) {
                        w += Math.abs(dX);
                    } else if (dX >= 0) {
                        w -= dX;
                    }

                    if (dY <= 0) {
                        h += Math.abs(dY);
                    } else if (dY >= 0) {
                        h -= dY;
                    }
                } else if (shape.getID().equals("Square") || shape.getID().equals("Circle")) {
                    if (dX <= 0) {
                        w += Math.abs(dX);
                        h += Math.abs(dX);
                    } else if (dX >= 0) {
                        w -= dX;
                        h -= dX;
                    }

                    if (dY <= 0) {
                        h += Math.abs(dY);
                        w += Math.abs(dY);
                    } else if (dY >= 0) {
                        h -= dY;
                        w -= dY;
                    }
                }
                // reposition the drawing coords
                double drawingX = initialX - w;
                double drawingY = initialY - h;

                shape.setWidth(w);
                shape.setHeight(h);
                shape.setDrawingX(drawingX);
                shape.setDrawingY(drawingY);

                shape.setCurrentX(mouseX);
                shape.setCurrentY(mouseY);

            } else if (mouseY - initialY >= 0) {
                // 4th quadrant
                shape.setWidth(0);
                shape.setHeight(0);

                double dX = mouseX - shape.getCurrentX();
                double dY = mouseY - shape.getCurrentY();

                // calculate width, height
//                if (dX >= 0) {
//                    w -= dX;
//                } else if (dX <= 0) {
//                    w += Math.abs(dX);
//
//                }
//
//                if (dY <= 0) {
//                    h -= Math.abs(dY);
//                } else if (dY >= 0) {
//                    h += dY;
//                }

                if (shape.getID().equals("Rect") || shape.getID().equals("Oval")) {
                    if (dX <= 0) {
                        w += Math.abs(dX);
                    } else if (dX >= 0) {
                        w -= dX;
                    }

                    if (dY <= 0) {
                        h -= Math.abs(dY);
                    } else if (dY >= 0) {
                        h += dY;
                    }
                } else if (shape.getID().equals("Square") || shape.getID().equals("Circle")) {
                    if (dX <= 0) {
                        w += Math.abs(dX);
                        h += Math.abs(dX);
                    } else if (dX >= 0) {
                        w -= dX;
                        h -= dX;
                    }

                    if (dY <= 0) {
                        h -= Math.abs(dY);
                        w -= Math.abs(dY);
                    } else if (dY >= 0) {
                        h += dY;
                        w += dY;
                    }
                }
                double drawingX = initialX - w;
                shape.setDrawingX(drawingX);

                shape.setWidth(w);
                shape.setHeight(h);

                shape.setCurrentX(mouseX);
                shape.setCurrentY(mouseY);
            }
        }
        notifySubs();
    }

    public boolean contains(double x, double y) {
        return shapes.stream().anyMatch(shape -> shape.contains(x, y));
    }

    public XShape whichShape(double x, double y) {
        return shapes.stream().filter(shape -> shape.contains(x, y)).findFirst().orElse(null);
    }

    public void moveShape(XShape shape, double dX, double dY) {
        shape.move(dX, dY);
        notifySubs();
    }

    public boolean hitEdge(double normX, double normY) {
        return false;
    }
}
