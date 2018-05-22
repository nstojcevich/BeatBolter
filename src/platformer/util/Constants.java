package platformer.util;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class Constants {

    public static final double SECONDS_PER_FRAME = 16.67;

    private Constants() {}
    public static final boolean SHOW_HITBOXES = false;

    // screen/platform dimensions
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 540;
    public static final int GROUND_HEIGHT = 20;

    // images
    //public static final Image PLAYER_STANDING_IMAGE = new Image("platformer/assets/player/standing_right.png", 100, 100, false, false);
    //public static final Image PLAYER_CROUCHING_IMAGE = new Image("platformer/assets/player/crouch_right.png", 68, 68, false, false);
    public static final Image PLAYER_STANDING_IMAGE = new Image("platformer/assets/player/giraf_stand.png", 100, 100, false, false);
    public static final Image PLAYER_CROUCHING_IMAGE = new Image("platformer/assets/player/giraf_crouch.png", 100, 68, false, false);

    public static final Image MAIN_SCREEN_BACKGROUND = new Image("platformer/assets/menu/menu_background.png", SCREEN_WIDTH, SCREEN_HEIGHT, false, false);
    public static final Image AIR_ENEMY_IMGAGE = new Image("platformer/assets/enemies/AirEnemy.png", 120, 40, false, false);

    public static final Image GROUND_ENEMY_IMAGE = new Image("platformer/assets/enemies/GroundEnemy.png", 90, 45, false, false);
    public static final Image SKY_IMAGE = new Image("platformer/assets/Sky.png", 960, 540, false, false);

    // player dimensions
    public static final int PLAYER_WIDTH = (int) PLAYER_STANDING_IMAGE.getWidth();
    public static final int PLAYER_HEIGHT = (int) PLAYER_STANDING_IMAGE.getHeight();
    public static final int PLAYER_CROUCH_HEIGHT = (int) PLAYER_CROUCHING_IMAGE.getHeight();
    public static final int PLAYER_CROUCH_WIDTH = (int) PLAYER_CROUCHING_IMAGE.getWidth();
    public static final int PLAYER_START_X = 15;
    public static final int PLAYER_START_Y = GROUND_HEIGHT + PLAYER_HEIGHT;

    // enemy
    public static Color ENEMY_COLOR = Color.DARKSLATEGRAY;

    //player movement
    public static final double JUMP_VELOCITY = 10;
}
