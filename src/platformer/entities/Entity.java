package platformer.entities;

import javafx.scene.shape.Rectangle;

import static platformer.util.Constants.SCREEN_HEIGHT;

abstract class Entity implements HasHitbox{
    protected int x, y;
    protected Rectangle hitbox;
    protected int width, height;

    protected Entity(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        hitbox = new Rectangle(x, y);
        hitbox.setX(x);
        hitbox.setY(y);
        hitbox.setWidth(width);
        hitbox.setHeight(height);
    }

    protected void updateHitbox() {
        getHitbox().setX(x);
        getHitbox().setY(SCREEN_HEIGHT - y);
    }

    public abstract void updateMovement();

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }
}
