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
 * The class is the 2nd level of the game.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> tmp = new ArrayList<>();
        //create new velocity, that we will change after from angle to speed
        Velocity v = new Velocity(0, 0);
        tmp.add(v.fromAngleAndSpeed(10, 5));
        tmp.add(v.fromAngleAndSpeed(20, 5));
        tmp.add(v.fromAngleAndSpeed(30, 5));
        tmp.add(v.fromAngleAndSpeed(40, 5));
        tmp.add(v.fromAngleAndSpeed(50, 5));
        tmp.add(v.fromAngleAndSpeed(-10, 5));
        tmp.add(v.fromAngleAndSpeed(-20, 5));
        tmp.add(v.fromAngleAndSpeed(-30, 5));
        tmp.add(v.fromAngleAndSpeed(-40, 5));
        tmp.add(v.fromAngleAndSpeed(-50, 5));
        return tmp;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public void drawBackground(DrawSurface ds) {
        ds.setColor(Color.WHITE);
        ds.fillRectangle(0, 25, ds.getWidth(), ds.getHeight());
        Color color = new Color(241, 229, 87);
        ds.setColor(color);
        ds.fillCircle(100, 150, 60);
        int numLines = 150;
        for (int i = 1; i <= numLines; i++) {
            ds.drawLine(100, 150, 900 / numLines * i, 250);
        }
        color = new Color(246, 214, 3);
        ds.setColor(color);
        ds.fillCircle(100, 150, 50);
        color = new Color(246, 177, 3);
        ds.setColor(color);
        ds.fillCircle(100, 150, 40);
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> tmp = new ArrayList<>();
        tmp.add(new Block(new geometry.Rectangle(new Point(25, 250), 50, 30), Color.RED));
        tmp.add(new Block(new geometry.Rectangle(new Point(76, 250), 50, 30), Color.RED));
        tmp.add(new Block(new geometry.Rectangle(new Point(127, 250), 50, 30), Color.ORANGE));
        tmp.add(new Block(new geometry.Rectangle(new Point(178, 250), 50, 30), Color.ORANGE));
        tmp.add(new Block(new geometry.Rectangle(new Point(229, 250), 50, 30), Color.YELLOW));
        tmp.add(new Block(new geometry.Rectangle(new Point(280, 250), 50, 30), Color.YELLOW));
        tmp.add(new Block(new geometry.Rectangle(new Point(331, 250), 50, 30), Color.GREEN));
        tmp.add(new Block(new geometry.Rectangle(new Point(382, 250), 50, 30), Color.GREEN));
        tmp.add(new Block(new geometry.Rectangle(new Point(433, 250), 50, 30), Color.BLUE));
        tmp.add(new Block(new geometry.Rectangle(new Point(484, 250), 50, 30), Color.BLUE));
        tmp.add(new Block(new geometry.Rectangle(new Point(535, 250), 50, 30), Color.PINK));
        tmp.add(new Block(new geometry.Rectangle(new Point(586, 250), 50, 30), Color.PINK));
        tmp.add(new Block(new geometry.Rectangle(new Point(637, 250), 50, 30), Color.CYAN));
        tmp.add(new Block(new geometry.Rectangle(new Point(688, 250), 50, 30), Color.CYAN));
        tmp.add(new Block(new geometry.Rectangle(new Point(739, 250), 50, 30), Color.DARK_GRAY));
        return tmp;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 12;
    }

    @Override
    public Paddle createPaddle(GUI gui) {
        return new Paddle(new Rectangle(new Point(100, 565), 600, 10),
                Color.DARK_GRAY, gui, 10);
    }
}
