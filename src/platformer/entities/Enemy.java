package platformer.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static platformer.util.Constants.SHOW_HITBOXES;

public class Enemy extends Entity implements HasHitbox, DrawnAsShape {
    private Color color;

    Enemy(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void moveLeft(double speed) {
        x -= speed;
    }

    public void draw(GraphicsContext gc) {
        convertToJFX();
        if(SHOW_HITBOXES) {
            gc.setGlobalAlpha(.5);
            gc.setFill(color);
            gc.fillRect(getHitbox().getX(), getHitbox().getY(), getHitbox().getWidth(), getHitbox().getHeight());
            gc.setGlobalAlpha(1);
        }
        else {
            gc.setFill(color);
            gc.fillRect(x, y, width, height);
        }
        convertToNorm();
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
