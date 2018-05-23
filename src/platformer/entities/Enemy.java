package platformer.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static platformer.util.Constants.SECONDS_PER_FRAME;
import static platformer.util.Constants.SHOW_HITBOXES;

public class Enemy extends Entity implements HasHitbox, DrawnAsShape {
    private Color color;
    private long lastMove = -1;
    private Image texture;

    /**
     * Enemy that is drawn as a rectangle the size of it's hitbox
     * @param x starting x position
     * @param y starting y position
     * @param width width of hitbox/enemy
     * @param height height of hitbox/enemy
     * @param color color for the rectangle to be drawn as
     */
    Enemy(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, null);
        this.x = x;
        this.y = y;
        this.color = color;
        this.texture = null;
    }

    /**
     * Enemy that is drawn as a texture
     * @param x starting x position
     * @param y starting y position
     * @param width width of hitbox
     * @param height height of hitbox
     * @param texture texture to draw the enemy as
     */
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

    /**
     * move the enemy along the x axis a certain amount
     * @param speed amount to move the enemy per call
     */
    private void move(double speed) {
        x += speed;
    }

    /**
     * draw the hitbox if the constant is set to true,
     * and if there is no texture draw a rectangle instead of the texture
     * @param gc Graphics Context to draw the texture in
     */
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

    /**
     * Set the color to draw the enemy as if there is no texture
     * @param color color to draw the enemy as
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the color that the enemy is currently being drawn as if it does not have a texture
     */
    @Override
    public Color getColor() {
        return color;
    }

}
