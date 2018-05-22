package platformer.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static platformer.util.Constants.SECONDS_PER_FRAME;
import static platformer.util.Constants.SHOW_HITBOXES;

public class Enemy extends Entity implements HasHitbox, DrawnAsShape {
    private Color color;
    private long lastMove = -1;
    private Image texture;

    Enemy(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, null);
        this.x = x;
        this.y = y;
        this.color = color;
        this.texture = null;
    }

    Enemy(int x, int y, int width, int height, Image texture)  {
        super(x, y, width, height, texture);
        this.x = x;
        this.y = y;
        this.color = null;
        this.texture = texture;
    }

    @Override
    public void updateMovement() {
        if(lastMove < 0)
            lastMove = System.currentTimeMillis();
        if(System.currentTimeMillis() - lastMove > 100) {
            lastMove = System.currentTimeMillis();
        }
        if(System.currentTimeMillis() - lastMove > 5) { //make sure we're not paused and not running twice in a single frame
            move(((System.currentTimeMillis() - lastMove) / SECONDS_PER_FRAME) * -10); // move 10 pixels per 60th of a second
            lastMove = System.currentTimeMillis();
            updateHitbox();
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    private void move(double speed) {
        x += speed;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (SHOW_HITBOXES) {
            gc.setGlobalAlpha(.5);
            gc.setFill(color);
            gc.fillRect(getHitbox().getX(), getHitbox().getY(), getHitbox().getWidth(), getHitbox().getHeight());
            gc.setGlobalAlpha(1);
        }
        if (texture == null) {
            gc.setFill(color);
            gc.fillRect(x, gc.getCanvas().getHeight() - y, hitboxWidth, hitboxHeight);
        }
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

}
