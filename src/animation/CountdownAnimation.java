package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import level.LevelInformation;
import sprite.SpriteCollection;

/**
 * @author Amit Cohen, ID=208428714
 * The class CountdownAnimation - creates an animation that counts to 3 before the game begins.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countForm;
    private SpriteCollection gameScreen;
    private LevelInformation lvl;
    private boolean stop;

    /**
     * this is a Constructor function that creates an object.
     * @param numOfSeconds - the seconds that the animation will run.
     * @param countForm - the number that we start to count from.
     * @param gameScreen - all the sprites that will be in the background while we count.
     * @param lv - the level of the game that gonna start after the count.
     */
    public CountdownAnimation(double numOfSeconds, int countForm, SpriteCollection gameScreen, LevelInformation lv) {
        this.numOfSeconds = numOfSeconds;
        this.countForm = countForm;
        this.gameScreen = gameScreen;
        this.lvl = lv;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.lvl.drawBackground(d);
        this.gameScreen.drawAllOn(d);
        biuoop.Sleeper sleeper = new Sleeper();
        int sleepTime = (int) (this.numOfSeconds / 3 * 1000);
        //draws the number 3,2,1
        d.drawText(400, d.getHeight() / 2, Integer.toString(countForm), 32);
        //sleeper should be activate except the first run
        if (countForm != 3) {
            sleeper.sleepFor(sleepTime);
        }
        countForm -= 1;
        if (countForm == -1) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

