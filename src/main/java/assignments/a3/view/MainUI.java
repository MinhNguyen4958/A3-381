package assignments.a3.view;

import javafx.scene.layout.BorderPane;

public class MainUI extends BorderPane {
    private ShapeToolbar leftbar;
    private ColourToolbar rightbar;
    private DrawingView middleview;
    public MainUI() {
        leftbar = new ShapeToolbar();
        rightbar = new ColourToolbar();
        middleview = new DrawingView();
        this.setLeft(leftbar);
        this.setRight(rightbar);
        this.setCenter(middleview);

    }
}
