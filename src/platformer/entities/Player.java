package platformer.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static platformer.util.Constants.*;

public class Player extends Entity implements HasHitbox {
    private boolean allowNewJump, jumping, crouching;
    private Rectangle crouchingHitbox = new Rectangle(PLAYER_CROUCH_WIDTH - PLAYER_CROUCH_WIDTH/3, PLAYER_CROUCH_HEIGHT);
    private final double gravity = .5;
    private double yVel = JUMP_VELOCITY;
    private boolean hit;
    private long lastMove = -1;
    private long lastJumpdate = -1;

    /**
     *
     * @param x starting x position of the player
     * @param y startying y position of the player
     */
    public Player(int x, int y) {
        super(x, y, PLAYER_WIDTH/2, PLAYER_HEIGHT, PLAYER_STANDING_IMAGE);
        this.x = x;
        this.y = y;
        allowNewJump = false;
        jumping = false;
        crouching = false;
    }

    /**
     *
     * @return the standing or crouching hitbox of the player
     */
    @Override
    public Rectangle getHitbox() {
        if (!crouching) {
            return super.getHitbox();
        }
        else return crouchingHitbox;
    }

    public void reset() {
        unCrouch();
        hit = false;
        x = PLAYER_START_X;
        y = PLAYER_START_Y;
    }

    /**
     * Start the jump process, the process is continued through the jumpdate method
     * */
    public void jump() {
        if(!jumping) {
            jumping = true;
            jumpdate();
        }
    }

    /**
     * Move the player through the jump process, factors in gravity to create an arc
     */
    private void jumpdate() {
        if(!crouching && allowNewJump) {
            allowNewJump = false;
            jumping = true;
        }
        if(jumping) {
            y += yVel;
            yVel -= gravity;
        }
        if(onGround()) {
            y = getHitboxHeight() + GROUND_HEIGHT;
            jumping = false;
            yVel = JUMP_VELOCITY;
        }
    }

    /**
     * Make the player crouch
     */
    public void crouch() {
        if(!crouching) {
            y -= PLAYER_HEIGHT - PLAYER_CROUCH_HEIGHT;
        }
        crouching = true;
    }

    /**
     * Make the player uncrouch
     */
    public void unCrouch() {
        if(crouching) {
            y += PLAYER_HEIGHT - PLAYER_CROUCH_HEIGHT;
        }
        crouching = false;
    }

    @Override
    public int getHitboxHeight() {
        if(crouching)
            return PLAYER_CROUCH_HEIGHT;
        else
            return PLAYER_HEIGHT;
    }

    @Override
    public int getHitboxWidth() {
        if(crouching)
            return PLAYER_CROUCH_WIDTH;
        else
            return PLAYER_WIDTH;
    }

    /**
     *
     * @return whether or not the player is touching the ground
     */
    public boolean onGround() {
        return y <= GROUND_HEIGHT + getHitboxHeight();
    }

    @Override
    protected void updateHitbox() {
        getHitbox().setX(x + (getHitboxWidth() - getHitbox().getWidth())/2);
        getHitbox().setY(SCREEN_HEIGHT - y);
    }

    /**
     * Updates the jump movement based on the time passed since the last update
     */
    @Override
    public void updateMovement() {
        if(System.currentTimeMillis() - lastJumpdate > 50) {
            lastJumpdate = System.currentTimeMillis();
        }
        if(onGround() && !jumping) allowNewJump = true;
        if(jumping) {
            while ((System.currentTimeMillis() - lastJumpdate) >= SECONDS_PER_FRAME) {
                jumpdate();
                lastJumpdate += SECONDS_PER_FRAME;
            }
        }
        if(lastMove < 0)
            lastMove = System.currentTimeMillis();
        lastMove = System.currentTimeMillis();
        updateHitbox();
    }

    /**
     * Draw player sprite and hitbox(if enabled)
     * @param gc the GraphicsContext that the player images are to be drawn on
     */
    @Override
    public void draw(GraphicsContext gc) {
        if(crouching)
            gc.drawImage(PLAYER_CROUCHING_IMAGE, x, gc.getCanvas().getHeight() - y);
        else
            gc.drawImage(PLAYER_STANDING_IMAGE, x, gc.getCanvas().getHeight() - y);
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
