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
 * The class is the 3rd level of the game.
 */
public class Level3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> tmp = new ArrayList<>();
        //create new velocity, that we will change after from angle to speed
        Velocity v = new Velocity(0, 0);
        tmp.add(v.fromAngleAndSpeed(10, 5));
        tmp.add(v.fromAngleAndSpeed(-10, 5));
        return tmp;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public void drawBackground(DrawSurface ds) {
        Color color = new Color(2, 113, 2);
        ds.setColor(color);
        ds.fillRectangle(0, 25, ds.getWidth(), ds.getHeight());
        color = new Color(71, 66, 66);
        ds.setColor(color);
        ds.fillRectangle(200, 200, 20, 200);
        color = new Color(239, 239, 140);
        ds.setColor(color);
        ds.fillCircle(210, 200, 12);
        color = new Color(245, 147, 74);
        ds.setColor(color);
        ds.fillCircle(210, 200, 8);
        color = new Color(255, 255, 255);
        ds.setColor(color);
        ds.fillCircle(210, 200, 3);
        color = new Color(56, 52, 52);
        ds.setColor(color);
        ds.fillRectangle(190, 400, 40, 200);
        color = new Color(0, 0, 0);
        ds.setColor(color);
        ds.fillRectangle(155, 450, 110, 200);
        ds.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ds.fillRectangle(165 + j * 20, 460 + i * 35, 10, 25);
            }
        }
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> tmp = new ArrayList<>();
        Color[] arr = {Color.WHITE, Color.PINK, Color.BLUE, Color.YELLOW, Color.RED, Color.DARK_GRAY};
        for (int i = 0; i < 5; i++) {
            Color c = arr[i];
            for (int j = 0; j < i + 3; j++) {
                geometry.Rectangle rec = new geometry.Rectangle(new Point(736 - j * 40,
                        300 - i * 30), 39.5, 29.5);
                Block b = new Block(rec, c);
                tmp.add(b);
            }
        }
        return tmp;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 25;
    }

    @Override
    public Paddle createPaddle(GUI gui) {
        return new Paddle(new Rectangle(new Point(350, 565), 100, 10),
                Color.DARK_GRAY, gui, 10);
    }
}
