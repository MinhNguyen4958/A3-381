package assignments.a3;

import assignments.a3.controller.*;
import assignments.a3.model.*;
import assignments.a3.view.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DrawingApp extends Application {
    @Override
    public void start(Stage stage) {
        DrawingController controller = new DrawingController();
        MainUI mainView = new MainUI();
        InteractionModel iModel = new InteractionModel();
        DrawingModel model = new DrawingModel();
        DrawingView canvas = mainView.getCanvas();
        MiniDrawingView miniCanvas = mainView.getMiniDrawingView();

        mainView.setController(controller);
        mainView.setiModel(iModel);

        canvas.setController(controller);
        canvas.setiModel(iModel);
        canvas.setModel(model);

        miniCanvas.setController(controller);
        miniCanvas.setiModel(iModel);
        miniCanvas.setModel(model);

        iModel.addiSub(canvas);
        iModel.addiSub(miniCanvas);

        model.addSub(canvas);
        model.addSub(miniCanvas);

        controller.setInteractionModel(iModel);
        controller.setModel(model);

        Scene scene = new Scene(mainView, 625, 500);
//        scene.setOnKeyPressed();
        stage.setTitle("Drawing App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}