package assignments.a3;

import assignments.a3.controller.DrawingController;
import assignments.a3.model.InteractionModel;
import assignments.a3.view.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DrawingApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DrawingController controller = new DrawingController();
        MainUI mainView = new MainUI();
        InteractionModel iModel = new InteractionModel();
        mainView.setController(controller);
        mainView.setiModel(iModel);
        iModel.addiSub(mainView);
        controller.setInteractionModel(iModel);



        Scene scene = new Scene(mainView, 800, 500);
        stage.setTitle("Drawing App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}