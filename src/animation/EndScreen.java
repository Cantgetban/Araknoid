package animation;


import biuoop.DrawSurface;

import biuoop.KeyboardSensor;
import game.Counter;

import java.awt.Color;
/**
 * @author Amit Cohen, ID=208428714
 * The class EndScreen represents the screen that will be after the game ends.
 */
public class EndScreen implements Animation {
    private boolean isWon;
    private Counter score;
    private KeyboardSensor ks;
    private boolean stop;

    /**
     * this is a Constructor function that creates an object.
     * @param win - if the user won the game.
     * @param score - the score of the user.
     * @param ks - the keyboard that recognize if the user want to end the screen.
     */
    public EndScreen(boolean win, Counter score, KeyboardSensor ks) {
        this.isWon = win;
        this.score = score;
        this.ks = ks;
    }

    @Override
    public void doOneFrame(DrawSurface ds) {
        ds.setColor(Color.BLACK);
        ds.fillRectangle(0, 0, ds.getWidth(), ds.getHeight());
        String title;
        if (isWon) {
            title = "You Win! Your score is: " + Integer.toString(score.getValue());
        } else {
            title = "Game Over. Your score is: " + Integer.toString(score.getValue());
        }
        ds.setColor(Color.WHITE);
        ds.drawText(200, 300, title, 30);
        ds.drawText(300, 450, "~Press space to continue~", 15);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
