package animation;

import biuoop.DrawSurface;

/**
 * @author Amit Cohen, ID=208428714
 * The interface Animation represents an animation that runs on the GUI.
 */
public interface Animation {
    /**
     * the function is telling the animation what should she do in a frame.
     * @param d - the draw surface we will show to the user.
     */
    void doOneFrame(DrawSurface d);

    /**
     * this function tells when the animation should stop running.
     * @return - true if animation should stop run.
     */
    boolean shouldStop();
}
