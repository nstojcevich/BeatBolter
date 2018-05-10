package platformer.entities;

import javafx.scene.shape.Rectangle;
import platformer.util.SensibleUnits;

class Entity implements HasHitbox{
    protected int x, y;
    private boolean isNormalUnits;
    protected Rectangle hitbox;
    protected int width, height;

    Entity(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        isNormalUnits = true;
        hitbox = new Rectangle(x, y);
        hitbox.setX(x);
        hitbox.setY(y);
        hitbox.setWidth(width);
        hitbox.setHeight(height);
    }

    // Make y origin at top right instead of bottom left
    // **only used when passing points to JFX methods, everything written by us uses "normal" units**
    protected void convertToJFX() {
        isNormalUnits = false;
        x = SensibleUnits.convertXToJFX(x);
        y = SensibleUnits.convertYToJFX(y);
        updateHitbox();
    }

    // Make y origin at bottom left instead of top right
    protected void convertToNorm() {
        isNormalUnits = true;
        x = SensibleUnits.convertXToNorm(x);
        y = SensibleUnits.convertYToNorm(y);
    }

    protected void updateHitbox() {
        getHitbox().setX(x);
        getHitbox().setY(y);
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public int getNormal_X() {return isNormalUnits ? x : SensibleUnits.convertXToNorm(x);}

    public int getNormal_Y() {return isNormalUnits ? y : SensibleUnits.convertYToNorm(y);}

    public int getJFX_X() {return !isNormalUnits ? x : SensibleUnits.convertXToJFX(x);}

    public int getJFX_Y() {return !isNormalUnits ? y : SensibleUnits.convertYToJFX(y);}

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }
}
