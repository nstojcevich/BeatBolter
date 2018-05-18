package platformer.scenes;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import platformer.EntityManager;
import platformer.GameManager;
import platformer.SceneManager;

import java.util.ArrayList;

import static platformer.util.Constants.*;

public class GameScene extends Scene{
    private GameManager gameManager;
    private EntityManager entityManager;
    private GraphicsContext game_gc;
    private boolean paused = false;
    private Text statsText = new Text();
    private PauseMenu pauseMenu;
    private SceneManager sceneManager;
    private ArrayList<String> input;


    public GameScene(Group root, Canvas gameCanvas, SceneManager sceneManager) {
        super(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        game_gc = gameCanvas.getGraphicsContext2D();
        gameManager = new GameManager();
        entityManager = new EntityManager(gameManager);
        pauseMenu = new PauseMenu(sceneManager, this, gameCanvas);
        pauseMenu.setVisible(false);
        root.getChildren().add(stats());
        root.getChildren().add(pauseMenu);
        this.sceneManager = sceneManager;

        // ArrayList to hold all of the currently pressed keys on the keyboard
        input = new ArrayList<>();

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
    }

    public void update(int framesPassed) {
        if (!sceneManager.getCurrentScene().equals(this)) {
            paused = true;
        } else if (!pauseMenu.isVisible()) {
            paused = false;
        }
        sceneManager.setPaused(paused);
        if (!paused) {
            if (entityManager.isPlayerHit()) {
                resetGame();
            }

            if (framesPassed % 60 * 2 == 0) { // Run every 2 seconds
                entityManager.addRandomEnemy();
            }

            if (input.contains("P")) {
                pauseGame();
                input.remove("P");
            }

            drawStage(); // Clear screen/draw stage
            entityManager.update(game_gc, input); // Update and draw player/enemies
        }
    }

    public void pauseGame() {
        openPauseMenu();
        paused = true;
    }

    public void unpauseGame() {
        closePauseMenu();
        paused = false;
    }

    private void openPauseMenu() {
        game_gc.setEffect(new GaussianBlur());
        pauseMenu.setVisible(true);
    }

    private void closePauseMenu() {
        pauseMenu.setVisible(false);
        game_gc.setEffect(null);
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

        updateStats();
    }

    public void resetGame() {
        gameManager.reset();
        entityManager.reset();
        drawStage();
    }

    private Node stats() {
        statsText.setEffect(null);
        statsText.setFill(Color.BLACK);
        statsText.setFont(Font.font("Arial", 20));
        statsText.setText("Score: " + gameManager.getScore() + "\nHighscore: " + gameManager.getHighScore());
        statsText.setX(10);
        statsText.setY(20);
        return statsText;
    }

    private void updateStats() {
        statsText.setText("Score: " + gameManager.getScore() + "\nHighscore: " + gameManager.getHighScore());
    }
}
