package assignments.a3.shapes;


import javafx.scene.paint.Paint;

public abstract class XShape {
    // the drawing coords for gc
    protected double drawingX;
    protected double drawingY;

    // the initial click coords
    protected double initialX;
    protected double initialY;

    // the current mouse coords
    protected double currentX;
    protected double currentY;

    //z iD
    int z;

    protected double width;
    protected double height;
    protected String ID;
    protected Paint shapeColor;

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }

    public double getCurrentX() {
        return currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public double getDrawingX() {
        return drawingX;
    }

    public double getDrawingY() {
        return drawingY;
    }


    public void setDrawingX(double drawingX) {
        this.drawingX = drawingX;
    }

    public void setDrawingY(double drawingY) {
        this.drawingY = drawingY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getID() {
        return ID;
    }

    public Paint getColor() {
        return shapeColor;
    }

    public void setInitialX(double initialX) {
        this.initialX = initialX;
    }

    public void setInitialY(double initialY) {
        this.initialY = initialY;
    }

    public double getInitialX() {
        return initialX;
    }

    public double getInitialY() {
        return initialY;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public XShape(double newLeft, double newTop, double newWidth, double newHeight, String ShapeID, Paint newColor) {
        initialX = newLeft;
        initialY = newTop;

        currentX = newLeft;
        currentY = newTop;

        drawingX = newLeft;
        drawingY = newTop;

        width = newWidth;
        height = newHeight;
        ID = ShapeID;
        shapeColor = newColor;
        z = 0;
    }

    public void resize(double newX, double newY, double dX, double dY) {
        drawingX = newX;
        drawingY = newY;
        width = dX;
        height = dY;
    }

    public abstract boolean contains(double x, double y);

    public abstract void move(double dX, double dY);

    protected double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
