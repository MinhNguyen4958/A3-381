package assignments.a3.model;

import assignments.a3.shapes.*;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Comparator;

public class DrawingModel {

    private ArrayList<XShape> shapes;
    private ArrayList<DrawingModelSubscribers> subs;
    private int nextZ;

    public DrawingModel() {
        shapes = new ArrayList<>();
        subs = new ArrayList<>();
        nextZ = 0;
    }
    // getter methods for shape lists
    public ArrayList<XShape> getShapes() {
        return shapes;
    }

    public void addSub(DrawingModelSubscribers newSub) {
        subs.add(newSub);
    }

    private void notifySubs() {
        subs.forEach(sub -> sub.modelChanged());
    }

    public XShape createShape(double x, double y, double newWidth, double newHeight, String shapeID, Paint newColor) {
        XShape newShape = null;
        // create a  temporary shape based on the shapeID taken from shape buttons
        switch (shapeID) {
            case "Rect" -> { newShape = new XRectangle(x, y, newWidth, newHeight, shapeID, newColor); }
            case "Square" -> { newShape =  new XSquare(x, y, newWidth, shapeID, newColor); }
            case "Oval" -> { newShape =  new XOval(x, y, newWidth, newHeight, shapeID, newColor); }
            case "Circle" -> { newShape =  new XCircle(x, y, newWidth, shapeID, newColor); }
            case "Line" ->  { newShape =  new XLine(x, y, x, y, shapeID, newColor); }
        }
        assert newShape != null;
        newShape.setZ(nextZ);

        nextZ++;
        return newShape;
    }

    public void addShape(XShape shape) {
        shapes.add(shape);
        shapes.sort(Comparator.comparingInt(XShape::getZ));
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

    public void resizeShape(XShape selectedShape, double mouseX, double mouseY) {
        double w = selectedShape.getWidth();
        double h = selectedShape.getHeight();

        double initialX = selectedShape.getInitialX();
        double initialY = selectedShape.getInitialY();

        if (mouseX - initialX >= 0) {
            if (mouseY - initialY >= 0) {
                // 1st quadrant
                double dX = mouseX - selectedShape.getCurrentX();
                double dY = mouseY - selectedShape.getCurrentY();

                if (!selectedShape.getID().equals("Line")) {
                    // we check the distance between currentX/Y and mouseX/Y
                    if (selectedShape.getID().equals("Rect") || selectedShape.getID().equals("Oval")) {
                        // if both x and y coordinates increase to the right, increment width and height
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
                    } else if (selectedShape.getID().equals("Square") || selectedShape.getID().equals("Circle")) {
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
                    selectedShape.setWidth(w);
                    selectedShape.setHeight(h);
                    selectedShape.setCurrentX(mouseX);
                    selectedShape.setCurrentY(mouseY);

                    // we selected the line button
                } else {
                    selectedShape.setWidth(mouseX);
                    selectedShape.setHeight(mouseY);
                }

                // the drawing coords should be the same as initials coords for this quadrant

            } else if (mouseY - initialY <= 0) {

                // 2nd quadrant
                double dX = mouseX - selectedShape.getCurrentX();
                double dY = mouseY - selectedShape.getCurrentY();

                if (!selectedShape.getID().equals("Line")) {
                    // calculate width, height and determine the drawing location of Y coord
                    if (selectedShape.getID().equals("Rect") || selectedShape.getID().equals("Oval")) {

                        if (dX <= 0) {
                            w -= Math.abs(dX);
                        } else if (dX >= 0) {
                            w += dX;
                        }
                        if (dY <= 0) {
                            h += Math.abs(dY);
                        } else if (dY >= 0) {
                            h -= dY;
                        }

                    } else if (selectedShape.getID().equals("Square") || selectedShape.getID().equals("Circle")) {
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

                    selectedShape.setDrawingY(drawingY);

                    selectedShape.setWidth(w);
                    selectedShape.setHeight(h);

                    selectedShape.setCurrentX(mouseX);
                    selectedShape.setCurrentY(mouseY);
                } else {
                    selectedShape.setWidth(mouseX);
                    selectedShape.setHeight(mouseY);
                }
            }
        } else if (mouseX - initialX <= 0) {
            if (mouseY - initialY <= 0) {
                // 3rd quadrant
                double dX = mouseX - selectedShape.getCurrentX();
                double dY = mouseY - selectedShape.getCurrentY();

                if (!selectedShape.getID().equals("Line")) {
                    // calculate width, height
                    if (selectedShape.getID().equals("Rect") || selectedShape.getID().equals("Oval")) {
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
                    } else if (selectedShape.getID().equals("Square") || selectedShape.getID().equals("Circle")) {
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

                    selectedShape.setWidth(w);
                    selectedShape.setHeight(h);
                    selectedShape.setDrawingX(drawingX);
                    selectedShape.setDrawingY(drawingY);

                    selectedShape.setCurrentX(mouseX);
                    selectedShape.setCurrentY(mouseY);
                } else {
                    selectedShape.setWidth(mouseX);
                    selectedShape.setHeight(mouseY);
                }

            } else if (mouseY - initialY >= 0) {
                // 4th quadrant
                double dX = mouseX - selectedShape.getCurrentX();
                double dY = mouseY - selectedShape.getCurrentY();

                // calculate width, height
                if (!selectedShape.getID().equals("Line")) {
                    if (selectedShape.getID().equals("Rect") || selectedShape.getID().equals("Oval")) {
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
                    } else if (selectedShape.getID().equals("Square") || selectedShape.getID().equals("Circle")) {
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
                    selectedShape.setDrawingX(drawingX);

                    selectedShape.setWidth(w);
                    selectedShape.setHeight(h);

                    selectedShape.setCurrentX(mouseX);
                    selectedShape.setCurrentY(mouseY);
                } else {
                    selectedShape.setWidth(mouseX);
                    selectedShape.setHeight(mouseY);
                }
            }
        }
        notifySubs();
    }
}
