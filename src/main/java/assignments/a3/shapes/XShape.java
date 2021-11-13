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
        // initial click for Cartesian Plane reference
        initialX = newLeft;
        initialY = newTop;

        // current mouseX/Y
        currentX = newLeft;
        currentY = newTop;

        // the drawing coordinates for GraphicsContext
        drawingX = newLeft;
        drawingY = newTop;

        width = newWidth;
        height = newHeight;

        ID = ShapeID;
        shapeColor = newColor;

        // Z-ordering ID
        z = 0;
    }

    public abstract boolean contains(double clickX, double clickY);

    public void move(double mouseX, double mouseY) {
        initialX = drawingX += mouseX - currentX;
        initialY = drawingY += mouseY - currentY;
        setCurrentX(mouseX);
        setCurrentY(mouseY);
    }
}
