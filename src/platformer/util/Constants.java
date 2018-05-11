package platformer.util;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class Constants {

    private Constants() {}
    public static final boolean SHOW_HITBOXES = false;

    // screen/platform dimensions
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 540;
    public static final int GROUND_HEIGHT = 20;

    // enemy
    public static final int ENEMY_SPEED = 10;
    public static Color ENEMY_COLOR = Color.DARKSLATEGRAY;

    // images
    public static final Image PLAYER_RIGHT_IMAGE = new Image( "Assets/Platformer/playerRight.png", 100, 100, false, false);
    public static final Image PLAYER_LEFT_IMAGE = new Image( "Assets/Platformer/playerLeft.png", 100, 100, false, false);
    public static final Image PLAYER_LEFT_CROUCH_IMAGE = new Image( "Assets/Platformer/playerCrouchLeft.png", 68, 68, false, false);
    public static final Image PLAYER_RIGHT_CROUCH_IMAGE = new Image( "Assets/Platformer/playerCrouchRight.png", 68, 68, false, false);

    // player dimensions
    public static final int PLAYER_WIDTH = (int)PLAYER_LEFT_IMAGE.getWidth();
    public static final int PLAYER_HEIGHT = (int)PLAYER_LEFT_IMAGE.getHeight();
    public static final int PLAYER_CROUCH_HEIGHT = (int)PLAYER_LEFT_CROUCH_IMAGE.getHeight();
    public static final int PLAYER_CROUCH_WIDTH = (int)PLAYER_LEFT_CROUCH_IMAGE.getWidth();
    public static final int PLAYER_START_X = 15;
    public static final int PLAYER_START_Y = GROUND_HEIGHT + PLAYER_HEIGHT;

    //player movement
    public static final double JUMP_VELOCITY = 10;
}
