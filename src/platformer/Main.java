package platformer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import static platformer.util.Constants.SCREEN_HEIGHT;
import static platformer.util.Constants.SCREEN_WIDTH;

public class Main extends Application {
    private Canvas gameCanvas;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage) {
        theStage.setTitle("Platformer Test");

        Group root = new Group();

        // Initialize Canvas
        gameCanvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(gameCanvas);

        SceneController controller = new SceneController(root, gameCanvas, theStage);

        // Finally show everything
        controller.start();
    }
}