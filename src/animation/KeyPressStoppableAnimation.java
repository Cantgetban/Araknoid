package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Amit Cohen, ID=208428714
 * The class KeyPressStoppableAnimation is a class that decorate animation that a keypress stops them.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * this is a constructor function that creates an object.
     * @param sensor - the keyboard that we will recognize it what the user written.
     * @param key - the button that the user press.
     * @param animation - the animation that runs on the GUI.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!this.ks.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (this.ks.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
