package assignments.a3.view;

import assignments.a3.model.*;
import assignments.a3.controller.*;


import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainUI extends BorderPane implements InteractionModelSubscriber, DrawingModelSubscribers {
    private ShapeToolbar leftbar;
    private ColourToolbar rightbar;
    private DrawingView canvasView;
    InteractionModel iModel;
    DrawingModel model;

    public MainUI() {
        leftbar = new ShapeToolbar();
        rightbar = new ColourToolbar();
        canvasView = new DrawingView(500, 500);
        this.setBackground(new Background( new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.setLeft(leftbar);
        this.setRight(rightbar);
        this.setCenter(canvasView);
        canvasView.myCanvas.widthProperty().addListener((observable, oldVal, newVal) -> {
            canvasView.myCanvas.setWidth(newVal.doubleValue());
            draw();
        });

        canvasView.myCanvas.heightProperty().addListener((observable, oldVal, newVal) -> {
            canvasView.myCanvas.setHeight(newVal.doubleValue());
            draw();
        });

    }

    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    public void setiModel(InteractionModel newiModel) {
        iModel = newiModel;
        iModel.setColor(Color.valueOf("Aqua"));
        leftbar.getShapeButtons().forEach(button -> {
            // the first button will be highlighted in Aqua
            if (button.isSelected()) {
                button.setBorder(new Border(new BorderStroke(iModel.getSelectedColor(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
                button.getShapeIcon().setFill(iModel.getSelectedColor());
            }

        iModel.setButton(leftbar.getShapeButtons().get(0));
        });
    }

    public void setController(DrawingController controller) {
        rightbar.getColorButtons().forEach(button -> button.setOnMouseClicked(e -> controller.setColor(Color.valueOf(button.textProperty().get()))));
        leftbar.getShapeButtons().forEach(button -> button.setOnMouseClicked(e -> {
            if (button.isSelected()) {
                iModel.setButton(button);
                button.setBorder(new Border(new BorderStroke(iModel.getSelectedColor(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
                button.getShapeIcon().setFill(iModel.getSelectedColor());
                button.getShapeIcon().setStroke(iModel.getSelectedColor()); // this line is for filling the line icon for the left toolbar
            }
        }));

        canvasView.myCanvas.setOnMousePressed(e -> controller.handlePressed(e.getX() / canvasView.width, e.getY() / canvasView.height, e));
        canvasView.myCanvas.setOnMouseDragged(e -> controller.handleDragged(e.getX() / canvasView.width, e.getY() / canvasView.height, e, iModel.getSelectedButton().getShapeID()));
        canvasView.myCanvas.setOnMouseMoved(e -> controller.handleMoved(e.getX() / canvasView.width, e.getY() / canvasView.height, e));
        canvasView.myCanvas.setOnMouseReleased(e -> controller.handleReleased(e.getX() / canvasView.width, e.getY() / canvasView.height, e));

    }

    @Override
    public void modelChanged() {
        draw();
    }

    @Override
    public void iModelChanged() {
        // update the button border whenever the selected is changed
        leftbar.getShapeButtons().forEach(button -> {
            if (button.isSelected()) {
                button.setBorder(new Border(new BorderStroke(iModel.getSelectedColor(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
                button.getShapeIcon().setFill(iModel.getSelectedColor());
                button.getShapeIcon().setStroke(iModel.getSelectedColor());
            }
        });
    }

    private void draw() {

    }

}
