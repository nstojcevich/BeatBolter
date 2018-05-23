package platformer.entities;

import static platformer.util.Constants.*;

public class AirEnemy extends Enemy {
    /**
     * Enemy that is high enough so that the player cannot jump over them but low enough so that the player must
     * crouch to pass without collision
     */
    public AirEnemy() {
        super(SCREEN_WIDTH, (GROUND_HEIGHT + PLAYER_HEIGHT + 35), 90, 40, AIR_ENEMY_IMGAGE);

    }
}
