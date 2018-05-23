package platformer.entities;

import static platformer.util.Constants.*;

public class GroundEnemy extends Enemy {
    /**
     * Enemy that the player must jump over in order to not collide with
     */
    public GroundEnemy() {
        super(SCREEN_WIDTH, GROUND_HEIGHT + 45, 90, 45, GROUND_ENEMY_IMAGE);
    }
}
