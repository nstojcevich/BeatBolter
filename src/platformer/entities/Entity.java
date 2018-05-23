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

    /**
     *
     * @param x starting x position of the entity
     * @param y starting y position of the entity
     * @param hitboxWidth width of the entities hitbox
     * @param hitBoxHeight height of the entities hitbox
     * @param texture texture of the entity, if it is null then nothing is drawn
     */
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

    /**
     * update the hitbox to the entities current position
     */
    protected void updateHitbox() {
        getHitbox().setX(x);
        getHitbox().setY(SCREEN_HEIGHT - y);
    }

    /**
     * if the entity has a texture then draw the entities texture at it's current position
     * @param gc Graphics Context to draw the texture in
     */
    protected void draw(GraphicsContext gc) {
        if(texture != null) {
            gc.drawImage(texture, x, gc.getCanvas().getHeight() - y);
        }
    }

    /**
     * movement update to be called periodically, must be based on time as it will run independently
     * from the drawing of each frame
     */
    public abstract void updateMovement();

    public int getHitboxWidth() {return hitboxWidth;}

    public int getHitboxHeight() {return hitboxHeight;}

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }
}
