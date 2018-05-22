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
        launch(Main.class, args);
    }

    public void start(Stage theStage) {
        theStage.setTitle("Platformer Test");
        theStage.setResizable(false);
        Group root = new Group();

        // Initialize Canvas
        gameCanvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(gameCanvas);

        SceneManager sceneManager = new SceneManager(root, gameCanvas, theStage);

        // Finally show everything
        sceneManager.start();
    }
}