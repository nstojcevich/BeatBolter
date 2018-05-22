package platformer.entities;

import com.sun.istack.internal.Nullable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import static platformer.util.Constants.SCREEN_HEIGHT;

abstract class Entity implements HasHitbox{
    protected int x, y;
    protected Rectangle hitbox;
    protected int hitboxWidth, hitboxHeight;
    protected Image texture;

    protected Entity(int x, int y, int hitboxWidth, int hitBoxHeight, @Nullable Image texture) {
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitBoxHeight;
        this.x = x;
        this.y = y;
        this.texture = texture;
        hitbox = new Rectangle(x, y);
        hitbox.setX(x);
        hitbox.setY(y);
        hitbox.setWidth(hitboxWidth);
        hitbox.setHeight(hitBoxHeight);
    }

    protected void updateHitbox() {
        getHitbox().setX(x);
        getHitbox().setY(SCREEN_HEIGHT - y);
    }

    protected void draw(GraphicsContext gc) {
        if(texture != null) {
            gc.drawImage(texture, x, gc.getCanvas().getHeight() - y);
        }
    }

    public abstract void updateMovement();

    public int getHitboxWidth() {return hitboxWidth;}

    public int getHitboxHeight() {return hitboxHeight;}

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }
}
