package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import animation.EndScreen;
import level.LevelInformation;

import java.util.List;
/**
 * @author Amit Cohen, ID=208428714
 * The class GameFlow is the class that responsible of the levels to flow.
 */
public class GameFlow {

    private final AnimationRunner ar;
    private final KeyboardSensor ks;
    private Counter score;
    private final GUI gui;

    /**
     * this is a constructor function.
     * @param runner - the object that will run the animation.
     * @param ks - the keyboard which tells us which button is the player pressing.
     * @param gui - the GUI that the game runs on.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor ks, GUI gui) {
        this.ar = runner;
        this.ks = ks;
        this.score = new Counter();
        this.gui = gui;
    }

    /**
     * the function that runs the levels one by one.
     * @param levels - the levels that we want to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean flag = true;
        for (LevelInformation levelinfo : levels) {
            GameLevel level = new GameLevel(levelinfo, this.score, this.gui);
            level.initialize();
            while (level.getBlocksCounter().getValue() > 0 && level.getBallsCounter().getValue() > 0) {
                level.run();
            }
            if (level.getBallsCounter().getValue() == 0) {
                flag = false;
                break;
            }
        }
        EndScreen es = new EndScreen(flag, this.score, this.ks);
        Animation kes = new KeyPressStoppableAnimation(this.ks, "space", es);
        this.ar.run(kes);
    }
}
