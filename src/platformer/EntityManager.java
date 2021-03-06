package platformer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import platformer.entities.AirEnemy;
import platformer.entities.Enemy;
import platformer.entities.GroundEnemy;
import platformer.entities.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static platformer.util.Constants.*;

public class EntityManager {
    private Player player;
    private Set<Enemy> enemies = new HashSet<>();
    private Random rand = new Random();
    private ScoreManager scoreManager;

    /**
     * Manages all enemies and players as well as score, handles movement, collision, adding, removing, and drawing of both.
     */
    public EntityManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        player = new Player(PLAYER_START_X, PLAYER_START_Y);
    }

    /**
     * @param input
     * Update players and enemies, and then check collision
     */
    public void updateMovement(ArrayList<String> input) {
        for(Enemy e : enemies) {
            e.updateMovement();
        }
        removeOutOfBounds();
        player.movePlayer(input);
        player.updateMovement();
        checkCollision();
    }

    public void drawEntities(GraphicsContext gc) {
        player.draw(gc);
        for(Enemy e : enemies) {
            e.draw(gc);
        }
    }

    private boolean outOfBounds(Enemy e) {
        return e.getHitbox().getX() + e.getHitbox().getWidth() < 0 || e.getHitbox().getX() > SCREEN_WIDTH;
    }

    /**
     * Loops through all enemies and removes them if they are out of bounds on either the left or right of the screen,
     * also adds 1 to score if an enemy is removed and the player did not hit an enemy before that.
     */
    private void removeOutOfBounds() {
        int before = enemies.size();
        enemies.removeIf(this::outOfBounds);
        int removed = before - enemies.size();
        if(!player.isHit()) {
            scoreManager.addToScore(removed);
        }
        if(removed >= 1) {
            player.setHit(false);
        }
    }

    /**
     * add a new air or ground enemy based on a random number (50% chance for both)
     */
    public void addRandomEnemy() {
        if(rand.nextBoolean())
            enemies.add(new GroundEnemy());
        else
            enemies.add(new AirEnemy());
    }

    public void reset() {
        enemies.clear();
        player.reset();
    }

    /**
     * Method to drawEntities enemy color if the player collides with them and reset the score
     */
    private void checkCollision() {
        for(Enemy e : enemies) {
            if(player.getHitbox().getBoundsInLocal().intersects(e.getHitbox().getBoundsInLocal())) {
                player.setHit(true);
                e.setColor(Color.RED);
            } else {
                e.setColor(ENEMY_COLOR);
            }
        }
    }

    public boolean isPlayerHit() {
        return player.isHit();
    }
}
