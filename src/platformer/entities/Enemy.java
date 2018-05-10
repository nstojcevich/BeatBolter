package platformer.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static platformer.util.Constants.ENEMY_SPEED;
import static platformer.util.Constants.SHOW_HITBOXES;

public class Enemy extends Entity implements HasHitbox {
    private Rectangle hitbox;
    private int width, height;
    private Color color;

    Enemy(int x, int y, int width, int height, Color color) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        hitbox = new Rectangle(x, y, width, height);
        hitbox.setX(x);
        hitbox.setY(y - height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void moveLeft() {
        x -= ENEMY_SPEED;
        updateHitbox();
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
            gc.fillRect(x, y - height, width, height);
        }
        convertToNorm();
    }

    private void updateHitbox() {
        hitbox.setX(x);
        hitbox.setY(y - height);
    }

    @Override
    protected void convertToJFX() {
        super.convertToJFX();
        updateHitbox();
    }

    public void changeColor(Color color) {
        this.color = color;
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}
}
