package listener;

import collidable.Ball;
import collidable.Block;
import game.Counter;
import game.GameLevel;

/**
 * @author Amit Cohen, ID=208428714
 * The class BallRemover represents a listener that removes ball.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * This is a construction method.
     * @param game - the game that we want the ball remover to take part of.
     * @param cr - counts how many ball remained in the game.
     */
    public BallRemover(GameLevel game, Counter cr) {
        this.game = game;
        this.remainingBalls = cr;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeHitListener(this);
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}
