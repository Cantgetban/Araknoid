package sprite;

import biuoop.DrawSurface;
/**
 * @author Amit Cohen, ID=208428714
 * The interface sprite.Sprite represents a object in animation.
 */
public interface Sprite {
    /**
     * this function draws the sprite.
     * @param d - the draw surface that we want to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * this function tells what the sprite should do while the time passing.
     */
    void timePassed();
}
