package listener;

import collidable.Ball;
import collidable.Block;
import game.Counter;
import game.GameLevel;

/**
 * @author Amit Cohen, ID=208428714
 * The class BlockRemover represents a listener that removes block.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * This is a construction method.
     * @param game - the game that we want the block remover to take part of.
     * @param removedBlocks - counts how many block remained in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
