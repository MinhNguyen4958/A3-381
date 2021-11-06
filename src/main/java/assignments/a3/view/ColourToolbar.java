package assignments.a3.view;

import javafx.scene.control.Button;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ColourToolbar extends VBox {
//    private VBox toolbar;
    ArrayList<ToggleButton> colorBtns;

    public ArrayList<ToggleButton> getColorButtons() {
        return colorBtns;
    }

    public ColourToolbar() {
        colorBtns = new ArrayList<>();
        String[] colourNames = {"Aqua", "Violet", "Green", "Gold", "Orange", "Coral", "Fuchsia", "Peru"};
        ToggleGroup color_group = new ToggleGroup();
        for (String name : colourNames) {
            ToggleButton color_btn = new ToggleButton(name);

            color_btn.setPrefWidth(65);
            color_btn.setBackground(new Background(new BackgroundFill(Color.valueOf(name), null, null)));
            color_btn.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
            colorBtns.add(color_btn);

            color_btn.setToggleGroup(color_group);
            color_btn.selectedProperty().addListener((observable, oldVal, newVal) -> {
                if (color_btn.isSelected()) {
                    // if the button is selected then draw borders match button's color
                    color_btn.setBorder(new Border(new BorderStroke(Color.valueOf(color_btn.textProperty().get()), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
                } else {
                    // otherwise keep it white
                    color_btn.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3.0))));
                }
            });
            this.getChildren().add(color_btn);
        }


        // on startup the first color button will be selected
        colorBtns.get(0).setSelected(true);

        // allow button resizing
        this.heightProperty().addListener((observable, oldVal, newVal) -> {
            for (ToggleButton button : colorBtns) {
                button.setPrefHeight(this.getHeight() / 8);
            }
        });
    }
}
