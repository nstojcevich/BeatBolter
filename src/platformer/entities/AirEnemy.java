package platformer.entities;

import static platformer.util.Constants.*;

public class AirEnemy extends Enemy {
    public AirEnemy() {
        super(SCREEN_WIDTH, (GROUND_HEIGHT + PLAYER_HEIGHT - 10), 60, 20, AIR_ENEMY_IMGAGE);

    }
}
