package assignments.a3.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ColourToolbar extends VBox {
//    private VBox toolbar;
    ArrayList<Rectangle> colourRects;
    public ColourToolbar() {
//        this.setSpacing(5.0);

        colourRects = new ArrayList<>();
        String[] colourNames = {"Aqua", "Violet", "Green", "Gold", "Orange", "Coral", "Fuchsia", "Peru"};
        for (String name : colourNames) {
            Rectangle r = new Rectangle(59,59, Color.valueOf(name));
            r.setStroke(Color.WHITE);
            r.setStrokeWidth(3.0);
            StackPane colorbox = new StackPane(r, new Label(name));
            this.getChildren().add(colorbox);
            colourRects.add(r);
        }
    }
}
