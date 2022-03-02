package level;

import biuoop.DrawSurface;
import biuoop.GUI;
import collidable.Block;
import collidable.Paddle;
import geometry.Velocity;
import java.util.List;
/**
 * @author Amit Cohen, ID=208428714
 * The interface represting a single level of the game.
 */
public interface LevelInformation {
    /**
     * this function returns the number of the ball in the level.
     * @return - the number of the balls.
     */
    int numberOfBalls();
    /**
     * this function returns the initial velocity of each ball.
     * @return - the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();
    /**
     *
     * @return - the name of the level.
     */
    String levelName();
    /**
     * this function draws the background of the level.
     * @param ds - the drawsurface we will draw on.
     */
    void drawBackground(DrawSurface ds);
    /**
     *
     * @return - the list of the blocks
     */
    List<Block> blocks();
    /**
     *
     * @return - the number of the blocks in the level.
     */
    int numberOfBlocksToRemove();

    /**
     * this function creates the paddle of the level.
     * @param gui - the gui of the game.
     * @return - the paddle of the level.
     */
    Paddle createPaddle(GUI gui);
}
