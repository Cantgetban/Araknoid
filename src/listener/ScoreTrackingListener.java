package listener;

import collidable.Ball;
import collidable.Block;
import game.Counter;

/**
 * @author Amit Cohen, ID=208428714
 * The class ScoreTrackingListener represents a listener that counts the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * This is a construction method that creates an object.
     * @param scoreCounter - the counter of the object.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
