package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Amit Cohen, ID=208428714
 * The class PauseScreen represents the screen that show when the user stops the game.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * This is a constructor function.
     * @param k - the keyboard that recognize wish button pressed.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(175, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
