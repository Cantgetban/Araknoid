package sprite;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * @author Amit Cohen, ID=208428714
 * The class ScoreIndicator represents a block that the score is presented in.
 */
public class ScoreIndicator implements Sprite {
    private geometry.Rectangle rec;
    private Color color;
    private GameLevel game;

    /**
     * This is a construction method that creates an object.
     * @param rec - the rectangle of the object.
     * @param color - the color of the object.
     * @param game - the game that the object will take part of.
     */
    public ScoreIndicator(geometry.Rectangle rec, Color color, GameLevel game) {
        this.rec = rec;
        this.color = color;
        this.game = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) (this.rec.getUpLeft().getX()),
                (int) (this.rec.getUpLeft().getY()),
                (int) (this.rec.getWidth()),
                (int) this.rec.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(350, 20, "Score :" + Integer.toString(this.game.getScore().getValue()), 20);
        d.drawText(650, 20, this.game.getLevel().levelName(), 20);
    }

    @Override
    public void timePassed() {
    }
}
