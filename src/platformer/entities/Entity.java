package platformer.entities;

import platformer.util.SensibleUnits;

public class Entity {
    int x;
    int y;
    private boolean isNormalUnits;

    Entity(int x, int y) {
        isNormalUnits = true;
        this.x = x;
        this.y = y;
    }

    // Make y origin at top right instead of bottom left
    // **only used when passing points to JFX methods, everything written by us uses "normal" units**
    void convertToJFX() {
        isNormalUnits = false;
        x = SensibleUnits.convertXToJFX(x);
        y = SensibleUnits.convertYToJFX(y);
    }

    // Make y origin at bottom left instead of top right
    void convertToNorm() {
        isNormalUnits = true;
        x = SensibleUnits.convertXToNorm(x);
        y = SensibleUnits.convertYToNorm(y);
    }


    public int getNormal_X() {return isNormalUnits ? x : SensibleUnits.convertXToNorm(x);}

    public int getNormal_Y() {return isNormalUnits ? y : SensibleUnits.convertYToNorm(y);}

    public int getJFX_X() {return !isNormalUnits ? x : SensibleUnits.convertXToJFX(x);}

    public int getJFX_Y() {return !isNormalUnits ? y : SensibleUnits.convertYToJFX(y);}
}
