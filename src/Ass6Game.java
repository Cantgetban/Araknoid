import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import level.LevelInformation;
import level.Level1;
import level.Level2;
import level.Level3;
import level.Level4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Cohen, ID=208428714
 * The class Ass3Game represents a game.Game.
 */
public class Ass6Game {
    /**
     * this is the main function thats the program starts with.
     *
     * @param args - Main gets a empty args.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Araknoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        GameFlow game = new GameFlow(ar, gui.getKeyboardSensor(), gui);
        List<LevelInformation> levels = new ArrayList<>();
        //if args is empty, do a full game
        if (args.length == 0 || (args.length == 1 && (args[0].equals("${args}") || args[0].equals("args")))) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
        //if args aren't empty, make the levels in order to args.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                levels.add(new Level1());
            }
            if (args[i].equals("2")) {
                levels.add(new Level2());
            }
            if (args[i].equals("3")) {
                levels.add(new Level3());
            }
            if (args[i].equals("4")) {
                levels.add(new Level4());
            }
        }
        game.runLevels(levels);
        System.exit(0);
    }
}
