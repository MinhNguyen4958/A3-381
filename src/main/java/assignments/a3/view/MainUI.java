package assignments.a3.view;

import assignments.a3.model.*;
import assignments.a3.controller.*;


import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainUI extends BorderPane implements InteractionModelSubscriber {
    private ShapeToolbar leftbar;
    private ColourToolbar rightbar;
    private DrawingView canvasView;

    InteractionModel iModel;

    public MainUI() {
        leftbar = new ShapeToolbar();
        rightbar = new ColourToolbar();
        canvasView = new DrawingView(500, 500);
        this.setBackground(new Background( new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.setLeft(leftbar);
        this.setRight(rightbar);
        this.setCenter(canvasView);
    }

    public void setiModel(InteractionModel newiModel) {
        iModel = newiModel;
        iModel.setColor(Color.valueOf("Aqua"));

        // set the default button's icon and border color to Aqua
        iModel.setButton(leftbar.getShapeButtons().get(0));
        leftbar.getShapeButtons().get(0).setBorder(new Border(new BorderStroke(iModel.getSelectedColor(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
        leftbar.getShapeButtons().get(0).getShapeIcon().setFill(iModel.getSelectedColor());
    }

    public void setController(DrawingController controller) {
        rightbar.getColorButtons().forEach(button -> button.setOnMouseClicked(e ->  {
            controller.setColor(Color.valueOf(button.textProperty().get()));
        }));
        leftbar.getShapeButtons().forEach(button -> button.setOnMouseClicked(e -> {
            if (button.isSelected()) {
                iModel.setButton(button);
            }
        }));
    }

    public DrawingView getCanvas() { return canvasView; }

    /** change the color of shapeButton's icon and border */
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
}
