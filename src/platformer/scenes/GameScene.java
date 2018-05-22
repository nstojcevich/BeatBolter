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
    long lastEnemyAdd = -1;
    long pausedTime = -1;


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

    public void update() {
        if (!sceneManager.getCurrentScene().equals(this)) {
            paused = true;
        } else if (!pauseMenu.isVisible()) {
            paused = false;
        }
        if (!paused) {
            if (entityManager.isPlayerHit()) {
                resetGame();
            }
            if((System.currentTimeMillis() - lastEnemyAdd) >= 1000 && !paused) { // 2 per second
                entityManager.addRandomEnemy();
                lastEnemyAdd = System.currentTimeMillis();
            }

            if (input.contains("SPACE")) {
                pauseGame();
                input.remove("SPACE");
            }
            entityManager.updateMovement(input); // Update and drawEntities player/enemies
            draw();
        }
    }

    public void pauseGame() {
        game_gc.setEffect(new GaussianBlur());
        pauseMenu.setVisible(true);
        paused = true;
        pausedTime = System.currentTimeMillis();
    }

    public void unpauseGame() {
        pauseMenu.setVisible(false);
        game_gc.setEffect(null);
        paused = false;
        pausedTime -= System.currentTimeMillis();
        lastEnemyAdd -= pausedTime;
    }

    private void draw() {
        drawStage();
        entityManager.drawEntities(game_gc);
        updateStats();

    }

    private void drawStage() {
        // Make sure everything is opaque
        game_gc.setGlobalAlpha(1);

        // Sky
        game_gc.drawImage(SKY_IMAGE, 0, 0);

        // Ground
        //game_gc.setFill(Color.GREEN);
        //game_gc.fillRect(0, SCREEN_HEIGHT - GROUND_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT - GROUND_HEIGHT);
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
