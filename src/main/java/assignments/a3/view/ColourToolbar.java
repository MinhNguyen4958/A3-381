package assignments.a3.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ColourToolbar extends VBox {
//    private VBox toolbar;
    ArrayList<Button> colorBtns;
    public ColourToolbar() {
        colorBtns = new ArrayList<>();
        String[] colourNames = {"Aqua", "Violet", "Green", "Gold", "Orange", "Coral", "Fuchsia", "Peru"};
        for (String name : colourNames) {
//            Rectangle r = new Rectangle(59,59, Color.valueOf(name));
//            r.setStroke(Color.WHITE);
//            r.setStrokeWidth(3.0);
//            StackPane colorbox = new StackPane(r, new Label(name));
//            this.getChildren().add(colorbox);
            Button color_btn = new Button(name);
            color_btn.setPrefHeight(59);
            color_btn.setPrefWidth(65);
            color_btn.setBackground(new Background(new BackgroundFill(Color.valueOf(name), null, null)));
            color_btn.setBorder(new Border(new BorderStroke(Color.WHITE, null, null, new BorderWidths(3.0))));
//            color_btn.setPadding(new Insets(3.0));
            color_btn.setOnMouseClicked(e -> System.out.println(color_btn.getBorder()));
            colorBtns.add(color_btn);
            this.getChildren().add(color_btn);
        }

        this.heightProperty().addListener((observable, oldVal, newVal) -> {
            for (Button button : colorBtns) {
                button.setPrefHeight(this.getHeight() / 8);
            }
        });
    }
}
