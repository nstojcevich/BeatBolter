package platformer.util;

public class SensibleUnits {
    public static int convertXToNorm(int x) {
        return x;
    }

    /*Convert from normal units ((0,0) is bottom left) to JFX units ((0,0) is top left)*/
    public static int convertYToNorm(int y) {
        return Constants.SCREEN_HEIGHT - y;
    }

    public static int convertXToJFX(int x) {
        return x;
    }

    /*Convert from normal units ((0,0) is bottom left) to JFX units ((0,0) is top left)*/
    public static int convertYToJFX(int y) {
        return Constants.SCREEN_HEIGHT - y;
    }
}
