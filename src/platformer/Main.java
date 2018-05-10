package platformer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static platformer.util.Constants.*;

public class Main extends Application {
    private EntityManager entityManager;
    private int framesPassed = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage) {
        theStage.setTitle("Platformer Test");

        Group root = new Group();

        // Initialize scene
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        // Initialize Canvas
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(canvas);

        // Initialize graphics context
        GraphicsContext gc = canvas.getGraphicsContext2D();


        // Initialize Entity Manager
        entityManager = new EntityManager();

        // ArrayList to hold all of the currently pressed keys on the keyboard
        ArrayList<String> input = new ArrayList<>();

        // Add key to list
        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code) )
                        input.add( code );
                });

        // Remove key from list
        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });

        // Draw stage to make sure screen is clear
        drawStage(gc);

        // Loop to run once every 60 seconds (60 frames per second)
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), //60 frames per second
                actionEvent -> {
                    framesPassed++;

                    if(framesPassed % 60 * 2 == 0) { // Run every 2 seconds
                        entityManager.addRandomEnemy();
                    }

                    drawStage(gc); // Clear screen/draw stage
                    entityManager.update(gc, input); // Update and draw player/enemies
                }
        );

        // Add frames to gameLoop
        gameLoop.getKeyFrames().add(kf);

        // Display current frame
        gameLoop.play();

        // Finally show everything
        theStage.show();
    }

    private void drawStage(GraphicsContext gc) {
        // Make sure everything is opaque
        gc.setGlobalAlpha(1);

        // Sky
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Ground
        gc.setFill(Color.GREEN);
        gc.fillRect(0, SCREEN_HEIGHT - GROUND_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT - GROUND_HEIGHT);

        // Score Counter
        gc.setFill(Color.BLACK);
        gc.fillText("Score: " + entityManager.getScore(), 10, 10);
    }
}