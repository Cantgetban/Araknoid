package collidable;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listener.HitListener;
import listener.HitNotifier;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Cohen, ID=208428714
 * The class represent a collidable.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //Fields
    private final geometry.Rectangle rec;
    private final Color color;
    private final List<HitListener> hitListeners;

    /**
     * This is a constructor method that creates a block.
     *
     * @param rect  - the rectangle that the block is made of.
     * @param color - the color of the block.
     */
    public Block(geometry.Rectangle rect, Color color) {
        this.rec = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        //we will make a new "line" that is only a point, so we can use  isIntersecting method
        Line temp = new Line(collisionPoint, collisionPoint);
        if (temp.isIntersecting(this.rec.getUp()) || temp.isIntersecting(this.rec.getDown())) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (temp.isIntersecting(this.rec.getRight()) || temp.isIntersecting(this.rec.getLeft())) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) (this.getCollisionRectangle().getUpLeft().getX()),
                (int) (this.getCollisionRectangle().getUpLeft().getY()),
                (int) (this.getCollisionRectangle().getWidth()),
                (int) this.getCollisionRectangle().getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * this function adding the block to the sprite and collidable lists.
     *
     * @param g - the game environment that we want to add the block to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * this function remove block from the game.
     * @param game - the game that we want to remove the ball from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * this function notify all the listeners of the blocks that he have been hitted.
     * @param hitter - the ball that hitted the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
