package platformer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import platformer.entities.AirEnemy;
import platformer.entities.Enemy;
import platformer.entities.GroundEnemy;
import platformer.entities.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static platformer.util.Constants.*;

class EntityManager {
    private Player player;
    private List<Enemy> enemies = new ArrayList<>();
    private Random rand = new Random();
    private int score = 0;

    /**
     * Manages all enemies and players as well as score, handles movement, collision, adding, removing, and drawing of both.
     */
    EntityManager() {
        int playerStartX = 0;
        int playerStartY = GROUND_HEIGHT + PLAYER_HEIGHT;
        player = new Player(playerStartX, playerStartY);
    }

    /**
     * @param gc
     * @param input
     * Update players and enemies, and then check collision
     */
    void update(GraphicsContext gc, ArrayList<String> input) {
        player.movePlayer(input);
        player.update(gc);
        updateEnemies(gc);
        checkCollision();
    }

    private void updateEnemies(GraphicsContext gc) {
        moveEnemies();
        removeOutOfBounds();
        drawEnemies(gc);
    }

    private void drawEnemies(GraphicsContext gc) {
        for(Enemy e : enemies) {
            e.draw(gc);
        }
    }

    private boolean outOfBounds(Enemy e) {
        return e.getNormal_X() + e.getWidth() < 0 || e.getNormal_X() > SCREEN_WIDTH;
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
            score += removed;
        }
        if(removed >= 1) {
            player.setHit(false);
        }
    }

    private void moveEnemies() {
        moveEnemiesLeft();
    }

    private void moveEnemiesLeft() {
        for(Enemy e : enemies) {
            e.moveLeft();
        }
    }

    private void addGroundEnemy() {
        enemies.add(new GroundEnemy());
    }

    private void addAirEnemy() {
        enemies.add(new AirEnemy());
    }

    /**
     * add a new air or ground enemy based on a random number (50% chance for both)
     */
    void addRandomEnemy() {
        if(rand.nextInt(2) == 1) addGroundEnemy();
        else addAirEnemy();
    }

    /**
     * Method to update enemy color if the player collides with them, also runs isPlayerCollided to check if score
     * should be reset.
     */
    private void checkCollision() {
        isPlayerCollided();
        for(Enemy e : enemies) {
            if(player.getHitbox().localToScene(player.getHitbox().getLayoutBounds()).intersects(e.getHitbox().localToScene(e.getHitbox().getLayoutBounds()))) {
                e.setColor(Color.RED);
            } else {
                e.setColor(ENEMY_COLOR);
            }
        }
    }

    /**
     * Method to detect if player is currently collided with any entities currently on the screen
     * @return is player collided with any enemies
     */
    private boolean isPlayerCollided() {
        boolean collided = false;
        for(Enemy e : enemies) {
            if(!collided) {
               collided = player.getHitbox().localToScene(player.getHitbox().getLayoutBounds()).intersects(e.getHitbox().localToScene(e.getHitbox().getLayoutBounds()));
                if(collided) {
                    player.setHit(true);
                    score = 0;
                }
            }
        }
        return collided;
    }

    public int getScore() {
        return score;
    }
}
