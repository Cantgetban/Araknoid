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
 * The class is the 1st level of the game.
 */
public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> tmp = new ArrayList<>();
        tmp.add(new Velocity(0, 5));
        return tmp;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> tmp = new ArrayList<>();
        tmp.add(new Block(new Rectangle(new Point(385, 145), 30, 30), Color.RED));
        return tmp;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public Paddle createPaddle(GUI gui) {
        return new Paddle(new Rectangle(new Point(350, 565), 100, 10),
                Color.DARK_GRAY, gui, 8);
    }

    @Override
    public void drawBackground(DrawSurface ds) {
        ds.setColor(Color.lightGray);
        ds.fillRectangle(0, 25, ds.getWidth(), ds.getHeight());
        ds.setColor(Color.BLACK);
        ds.drawLine(400, 180, 400, 300);
        ds.drawLine(420, 160, 540, 160);
        ds.drawLine(380, 160, 260, 160);
        ds.drawLine(400, 140, 400, 20);
        ds.drawCircle(400, 160, 60);
        ds.drawCircle(400, 160, 90);
        ds.drawCircle(400, 160, 120);

    }
}
