package platformer.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static platformer.util.Constants.*;

public class Player extends Entity implements HasHitbox {
    private double moveSpeed = ((SCREEN_WIDTH/3)/60); // move one third of the screen in one second (60 frames)
    private boolean facingLeft;
    private boolean allowNewJump, jumping, crouching;
    private Rectangle standingHitbox = new Rectangle(PLAYER_WIDTH/2, PLAYER_HEIGHT);
    private Rectangle crouchingHitbox = new Rectangle(PLAYER_WIDTH/2, PLAYER_CROUCH_HEIGHT);
    private double gravity = .6;
    private double yVel = JUMP_VELOCITY;
    private boolean hit;

    public Player(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        facingLeft = false;
        allowNewJump = false;
        jumping = false;
        crouching = false;
    }

    public Rectangle getHitbox() {
        if (!crouching) {
            return standingHitbox;
        }
        else return crouchingHitbox;
    }

    public void moveLeft() {
        x -= moveSpeed;
        facingLeft = true;
    }

    public void moveRight() {
        x += moveSpeed;
        facingLeft = false;
    }

    public void jump() {
        if(!jumping) {
            jumpUpdate();
        }
    }

    /**
     * Move the player through the jump process, factors in gravity to create an arc
     */
    private void jumpUpdate() {
        if(!crouching && allowNewJump) {
            allowNewJump = false;
            jumping = true;
        }
        if(jumping) {
            y += yVel;
            yVel -= gravity;
        }
        if(onGround()) {
            y = PLAYER_HEIGHT + GROUND_HEIGHT;
            jumping = false;
            yVel = JUMP_VELOCITY;
        }
    }

    public boolean isJumping() {
        return jumping;
    }

    public void crouch() {
        crouching = true;
    }

    public void unCrouch() {
        crouching = false;
    }

    public boolean onGround() {
        return y <= GROUND_HEIGHT + PLAYER_HEIGHT;
    }

    /**
     * Update player sprite and hitbox position, and draw the player
     * @param gc
     */
    public void update(GraphicsContext gc) {
        if(jumping) jumpUpdate();
        if(onGround() && !jumping) allowNewJump = true;
        convertToJFX();

        getHitbox().setX(x + (PLAYER_WIDTH - getHitbox().getWidth())/2);

        if (crouching) {
            getHitbox().setY(y + (standingHitbox.getHeight() - crouchingHitbox.getHeight()));
        } else {
            getHitbox().setY(y);
        }

        drawPlayer(gc);
        convertToNorm();
    }

    /**
     * Draw player sprite and hitbox(if enabled)
     * @param gc
     */
    private void drawPlayer(GraphicsContext gc) {
        if (facingLeft) {
            if(crouching) gc.drawImage(PLAYER_LEFT_CROUCH_IMAGE, x, y);
            else gc.drawImage(PLAYER_LEFT_IMAGE, x, y);
        } else {
            if(crouching) gc.drawImage(PLAYER_RIGHT_CROUCH_IMAGE, x, y);
            else gc.drawImage(PLAYER_RIGHT_IMAGE, x, y);
        }
        if(SHOW_HITBOXES) {
            gc.setFill(Color.GREENYELLOW);
            gc.setGlobalAlpha(.5);
            gc.fillRect(getHitbox().getX(), getHitbox().getY(), getHitbox().getWidth(), getHitbox().getHeight());
            gc.setGlobalAlpha(1);
        }
    }

    /**
     * @param hit has the player been hit by an enemy that is still on screen
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    /**
     * @return has player been hit by an enemy that is still on screen
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * Handles movement of the player, used to make the player jump and crouch based on keyboard input
     * @param input input list passed in from main function
     */
    public void movePlayer(ArrayList<String> input) {
        if (input.contains("LEFT") && !input.contains("RIGHT")) {
            //moveLeft();
        }

        if (input.contains("RIGHT") && !input.contains("LEFT")) {
            //moveRight();
        }

        if(input.contains("UP") && !input.contains("DOWN")) {
            jump();
        }

        if(input.contains("DOWN") && !input.contains("UP")) {
            crouch();
        }

        if(!input.contains("DOWN")) {
            unCrouch();
        }
    }
}
