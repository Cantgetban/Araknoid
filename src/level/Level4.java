package level;

import biuoop.DrawSurface;
import biuoop.GUI;
import collidable.Block;
import collidable.Paddle;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Amit Cohen, ID=208428714
 * The class is the 4th level.
 */
public class Level4 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> tmp = new ArrayList<>();
        //create new velocity, that we will change after from angle to speed
        Velocity v = new Velocity(0, 0);
        tmp.add(v.fromAngleAndSpeed(0, 10));
        tmp.add(v.fromAngleAndSpeed(20, 10));
        tmp.add(v.fromAngleAndSpeed(-20, 10));
        return tmp;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public void drawBackground(DrawSurface ds) {
        Color color = new Color(40, 95, 206);
        ds.setColor(color);
        ds.fillRectangle(0, 25, ds.getWidth(), ds.getHeight());
        ds.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            ds.drawLine(140 + i * 10, 400, 80 + i * 10, 600);
        }
        color = new Color(212, 205, 205);
        ds.setColor(color);
        ds.fillCircle(135, 400, 23);
        ds.fillCircle(155, 420, 27);
        color = new Color(199, 193, 193);
        ds.setColor(color);
        ds.fillCircle(175, 390, 29);
        color = new Color(167, 158, 158);
        ds.setColor(color);
        ds.fillCircle(195, 420, 22);
        ds.fillCircle(215, 400, 32);
        ds.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            ds.drawLine(605 + i * 10, 450, 580 + i * 10, 600);
        }
        color = new Color(212, 205, 205);
        ds.setColor(color);
        ds.fillCircle(600, 430, 23);
        ds.fillCircle(620, 470, 27);
        color = new Color(199, 193, 193);
        ds.setColor(color);
        ds.fillCircle(640, 440, 29);
        color = new Color(167, 158, 158);
        ds.setColor(color);
        ds.fillCircle(660, 460, 22);
        ds.fillCircle(680, 450, 32);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> tmp = new ArrayList<>();
        Color[] arr = {Color.CYAN, Color.PINK, Color.WHITE, Color.GREEN, Color.YELLOW, Color.RED, Color.DARK_GRAY};
        for (int i = 0; i < 7; i++) {
            Color c = arr[i];
            for (int j = 0; j < 18; j++) {
                geometry.Rectangle rec = new geometry.Rectangle(new Point(735 - j * 42,
                        250 - i * 30), 41.5, 29.5);
                Block b = new Block(rec, c);
                tmp.add(b);
            }
        }
        return tmp;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 126;
    }

    @Override
    public Paddle createPaddle(GUI gui) {
        return new Paddle(new Rectangle(new Point(350, 565), 100, 10),
                Color.DARK_GRAY, gui, 12);
    }
}
