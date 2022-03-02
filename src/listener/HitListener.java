package listener;

import collidable.Ball;
import collidable.Block;

/**
 * @author Amit Cohen, ID=208428714
 * The class HitListener represents a listener that does something when block being hit.
 */
public interface HitListener {
    /**
     * This method calls everytime a block is being hit.
     * @param beingHit - the block that being hit.
     * @param hitter - the ball that hitted the ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
