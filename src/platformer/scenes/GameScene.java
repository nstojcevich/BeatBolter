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
import platformer.ScoreManager;
import platformer.SceneManager;

import java.util.ArrayList;

import static platformer.util.Constants.*;

public class GameScene extends Scene{
    private ScoreManager scoreManager;
    private EntityManager entityManager;
    private GraphicsContext game_gc;
    private boolean paused = false;
    private Text scoreText = new Text();
    private PauseMenu pauseMenu;
    private EndGame endGame;
    private SceneManager sceneManager;
    private ArrayList<String> input;
    long lastEnemyAdd = -1;
    long pausedTime = -1;


    /**
     * Scene that handles the drawing and updating of the game and the pause screen
     * @param root root group of the main application
     * @param gameCanvas canvas to be drawn on
     * @param sceneManager sceneManager of the main application used to pause and unpause
     */
    public GameScene(Group root, Canvas gameCanvas, SceneManager sceneManager) {
        super(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        game_gc = gameCanvas.getGraphicsContext2D();
        scoreManager = new ScoreManager();
        entityManager = new EntityManager(scoreManager);
        pauseMenu = new PauseMenu(sceneManager, this, gameCanvas);
        endGame = new EndGame(sceneManager, this, gameCanvas);
        endGame.setVisible(false);
        pauseMenu.setVisible(false);
        root.getChildren().add(score());
        root.getChildren().add(pauseMenu);
        root.getChildren().add(endGame);
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

    /**
     * add in random enemies every x seconds and check for pausing/unpausing of the game, update entity movement then
     * draw the stage and all entities
     */
    public void update() {
        if (!sceneManager.getCurrentScene().equals(this)) {
            paused = true;
        } else if (!pauseMenu.isVisible() && !endGame.isVisible()) {
            paused = false;
        }
        if (!paused) {
            if (entityManager.isPlayerHit()) {
                endGame();
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

    /**
     * pause the game update loop and open up a pause screen
     */
    public void pauseGame() {
        game_gc.setEffect(new GaussianBlur());
        pauseMenu.setVisible(true);
        paused = true;
        pausedTime = System.currentTimeMillis();
    }

    /**
     * close the pause screen and resume gameplay
     */
    public void unpauseGame() {
        pauseMenu.setVisible(false);
        game_gc.setEffect(null);
        paused = false;
        pausedTime -= System.currentTimeMillis();
        lastEnemyAdd -= pausedTime;
    }

    /**
     * draw the stage and all entities, update score
     */
    private void draw() {
        drawStage();
        entityManager.drawEntities(game_gc);
        updateScore();

    }

    private void drawStage() {
        // clear any effects when unpaused
        if(!paused)
            game_gc.setEffect(null);

        // Make sure everything is opaque
        game_gc.setGlobalAlpha(1);

        // Sky
        game_gc.drawImage(SKY_IMAGE, 0, 0);
    }

    /**
     * reset score and enemies to start a new game
     */
    public void resetGame() {
        endGame.setVisible(false);
        paused = false;
        scoreManager.reset();
        entityManager.reset();
        drawStage();
    }

    /**
     * score and high score text box
     * @return node containing the score text
     */
    private Node score() {
        scoreText.setEffect(null);
        scoreText.setFill(Color.BLACK);
        scoreText.setFont(Font.font("Arial", 20));
        scoreText.setText("Score: " + scoreManager.getScore() + "\nHighscore: " + scoreManager.getHighScore());
        scoreText.setX(10);
        scoreText.setY(20);
        return scoreText;
    }

    /**
     * read updated scores from the scoreManager
     */
    private void updateScore() {
        scoreText.setText("Score: " + scoreManager.getScore() + "\nHighscore: " + scoreManager.getHighScore());
    }

    private void endGame() {
        game_gc.setEffect(new GaussianBlur());
        endGame.setVisible(true);
        paused = true;
    }
}
