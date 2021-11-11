package assignments.a3.view;

import assignments.a3.model.DrawingModelSubscribers;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class MiniDrawingView extends DrawingView implements DrawingModelSubscribers {

    public MiniDrawingView(double w, double h) {
        super(w, h);
        this.setBackground(new Background( new BackgroundFill(Color.DARKGRAY, null, null)));
        this.setMaxHeight(100);
        this.setMaxWidth(100);
    }
}
