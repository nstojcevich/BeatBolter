package platformer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

import static platformer.util.Constants.*;

public class GameScene extends Scene{
    private Timeline gameLoop;
    private int framesPassed = 0;
    private double runDurationSeconds = 0;
    private GameManager gameManager;
    private EntityManager entityManager;
    private GraphicsContext game_gc;
    private boolean paused = false;
    private Text scoreText = new Text();
    private PauseMenu pauseMenu;


    public GameScene(Group root, Canvas gameCanvas, SceneController controller) {
        super(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        // Initialize graphics context
        game_gc = gameCanvas.getGraphicsContext2D();

        gameManager = new GameManager();
        entityManager = new EntityManager(gameManager);
        pauseMenu = new PauseMenu(controller, this, gameCanvas);
        pauseMenu.setVisible(false);
        root.getChildren().add(scoreText());
        root.getChildren().add(pauseMenu);

        // ArrayList to hold all of the currently pressed keys on the keyboard
        ArrayList<String> input = new ArrayList<>();

        // Add key to list
        setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code) )
                        input.add( code );
                });

        // Remove key from list
        setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });

        // Loop to run once every 60 seconds (60 frames per second)
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), //60 frames per second
                actionEvent -> {
                    if (!paused) {
                        framesPassed++;
                        runDurationSeconds = framesPassed / 60;
                        entityManager.setEnemySpeed(10 + runDurationSeconds / 10);
                        if (entityManager.isPlayerHit()) {
                            framesPassed = 0;
                            resetGame();
                        }

                        if (framesPassed % 60 * 2 == 0) { // Run every 2 seconds
                            entityManager.addRandomEnemy();
                        }

                        if (input.contains("SPACE")) {
                            pauseGame();
                            input.remove("SPACE");
                        }

                        drawStage(); // Clear screen/draw stage
                        entityManager.update(game_gc, input); // Update and draw player/enemies
                    }
                }
        );

        // Add frames to gameLoop
        gameLoop.getKeyFrames().add(kf);

        // Display current frame
        gameLoop.play();
    }

    public void freezeLoop() {
        gameLoop.pause();
        paused = true;
    }

    public void pauseGame() {
        game_gc.setEffect(new GaussianBlur());
        paused = true;
        pauseMenu.setVisible(true);
    }

    private void closePauseMenu() {
        pauseMenu.setVisible(false);
        game_gc.setEffect(null);
    }

    public void unpauseGame() {
        closePauseMenu();
        paused = false;
    }

    public void unFreezeLoop() {
        gameLoop.play();
        paused = false;
    }

    private void drawStage() {
        // Make sure everything is opaque
        game_gc.setGlobalAlpha(1);

        // Sky
        game_gc.setFill(Color.SKYBLUE);
        game_gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Ground
        game_gc.setFill(Color.GREEN);
        game_gc.fillRect(0, SCREEN_HEIGHT - GROUND_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT - GROUND_HEIGHT);

        updateScore();
    }

    private void resetGame() {
        gameManager.reset();
        entityManager.reset();
        drawStage();
    }

    private Node scoreText() {
        scoreText.setEffect(null);
        scoreText.setFill(Color.BLACK);
        scoreText.setFont(Font.font("Arial", 20));
        scoreText.setText("Score: " + gameManager.getScore() + "\nHighscore: " + gameManager.getHighScore());
        scoreText.setX(10);
        scoreText.setY(20);
        return scoreText;
    }

    private void updateScore() {
        scoreText.setText("Score: " + gameManager.getScore() + "\nHighscore: " + gameManager.getHighScore());
    }
}
