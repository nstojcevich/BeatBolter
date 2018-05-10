package platformer.entities;

import static platformer.util.Constants.*;

public class GroundEnemy extends Enemy {
    public GroundEnemy() {
        super(SCREEN_WIDTH, GROUND_HEIGHT + 30, 60, 30, ENEMY_COLOR);
    }
}
