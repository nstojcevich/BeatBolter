package platformer.entities;

import static platformer.util.Constants.*;

public class AirEnemy extends Enemy {
    public AirEnemy() {
        super(SCREEN_WIDTH, (GROUND_HEIGHT + PLAYER_HEIGHT + 35), 90, 40, AIR_ENEMY_IMGAGE);

    }
}
