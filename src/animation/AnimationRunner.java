package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import biuoop.GUI;

/**
 * @author Amit Cohen, ID=208428714
 * The class AnimationRuuner is running an animation.
 */
public class AnimationRunner {
    public static final int MILES_PER_SECOND = 1000;
    private GUI gui;
    private final int framesPerSecond = 60;

    /**
     * this is a constructor function.
     * @param gui - the GUI that animation will run on.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
    }

    /**
     * this is an accessor method.
     * @return - the GUI.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * this function running an animation.
     * @param animation - the animation that will run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MILES_PER_SECOND / this.framesPerSecond;
        biuoop.Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
