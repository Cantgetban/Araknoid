package sprite;

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * @author Amit Cohen, ID=208428714
 * The class sprite.SpriteCollection represents a Collection of Sprites.
 */
public class SpriteCollection {
    //Fields
    private ArrayList<Sprite> list;

    /**
     * this function initialize the list of the sprites.
     */
    public SpriteCollection() {
        this.list = new ArrayList<Sprite>();
    }

    /**
     * This is an accessor method.
     * @return - the sprite list.
     */
    public ArrayList<Sprite> getList() {
        return this.list;
    }

    /**
     * this function add a sprite to the list.
     * @param s - the sprite that we want to add.
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }

    /**
     * this function calls the "time passed" function of the sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.list.size(); i++) {
            this.list.get(i).timePassed();
        }
    }

    /**
     * this function calls the "draw on" function of the sprites.
     * @param d - the draw surface that we want to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.list.size(); i++) {
            this.list.get(i).drawOn(d);
        }
    }
}
