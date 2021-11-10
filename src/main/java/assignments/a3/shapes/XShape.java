package assignments.a3.shapes;


import javafx.scene.paint.Paint;

public abstract class XShape {
    protected double x, y, initialX, initialY, width, height;
    protected String ID;
    protected Paint shapeColor;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getID() { return ID; }

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

    public XShape(double newLeft, double newTop, double newWidth, double newHeight, String ShapeID, Paint newColor) {
        initialX = newLeft;
        initialY = newTop;
        x = newLeft;
        y = newTop;

        width = newWidth;
        height = newHeight;
        ID = ShapeID;
        shapeColor = newColor;
    }

    public void resize(double newX, double newY, double dX, double dY) {
        x = newX;
        y = newY;
        width = dX;
        height = dY;
    }

    public abstract boolean contains(double x, double y);

    public abstract void move(double dX, double dY);

    protected double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}
