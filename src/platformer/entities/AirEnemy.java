package platformer.entities;

import static platformer.util.Constants.*;

public class AirEnemy extends Enemy {
    public AirEnemy() {
        super(SCREEN_WIDTH, (GROUND_HEIGHT + PLAYER_HEIGHT - 10), 60, 10, ENEMY_COLOR);

    }
}
