package collidable;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Amit Cohen, ID=208428714
 * The class collidable.Paddle represents paddle
 */
public class Paddle implements Sprite, Collidable {
    private final geometry.Rectangle rec;
    private Color color;
    private final GUI gui;
    private final ArrayList<Ball> balls;
    private int velocity;

    /**
     * this is a constructor Method that creates a paddle.
     *
     * @param rec  - the rectangle of the paddle.
     * @param c    - the color of the paddle.
     * @param gui  - the gui that the paddle take part of.
     * @param velo - the velocity of the paddle.
     */
    public Paddle(geometry.Rectangle rec, Color c, GUI gui, int velo) {
        this.rec = rec;
        this.color = c;
        this.gui = gui;
        this.balls = new ArrayList<>();
        this.velocity = velo;
    }

    /**
     * this function adds a ball to the list of the balls that connected with paddle.
     *
     * @param b - the ball thag we want to add.
     */
    public void addBall(Ball b) {
        this.balls.add(b);
    }

    /**
     * this function checks if ball is in paddle.
     *
     * @param ball - the ball that we want to check.
     * @return - true if ball is in paddle, otherwise false.
     */
    public boolean isBallInPaddle(Ball ball) {
        //Ball is in paddle if he's values between the height and the weight of the paddle
        if (this.getCollisionRectangle().getDownLeft().getX() <= ball.getX()
                && ball.getX() <= this.getCollisionRectangle().getUpRight().getX()
                && this.getCollisionRectangle().getDownLeft().getY() >= ball.getY()
                && ball.getY() >= this.getCollisionRectangle().getUpRight().getY()) {
            return true;
        }
        return false;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    @Override
    public Velocity hit(Ball hitter, geometry.Point collisionPoint, Velocity currentVelocity) {
        //we will make a new "line" that is only a point, so we can use  isIntersecting method
        //the whole edges except top should react the same as block.
        Line temp = new Line(collisionPoint, collisionPoint);
        if (temp.isIntersecting(this.rec.getDown())) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (temp.isIntersecting(this.rec.getRight()) || temp.isIntersecting(this.rec.getLeft())) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }

        double width = this.rec.getWidth();
        geometry.Point p1 = this.rec.getUpLeft();
        geometry.Point p2 = new geometry.Point(this.rec.getUpLeft().getX() + width / 5, this.rec.getUpLeft().getY());
        geometry.Point p3 = new geometry.Point(
                this.rec.getUpLeft().getX() + 2 * width / 5, this.rec.getUpLeft().getY());
        Point p4 = new geometry.Point(this.rec.getUpLeft().getX() + 3 * width / 5, this.rec.getUpLeft().getY());
        geometry.Point p5 = new geometry.Point(
                this.rec.getUpLeft().getX() + 4 * width / 5, this.rec.getUpLeft().getY());
        geometry.Point p6 = new geometry.Point(this.rec.getUpLeft().getX() + width, this.rec.getUpLeft().getY());
        //if the ball hits top, there is 5 cases
        if (temp.isIntersecting(new

                Line(p1, p2))) {
            currentVelocity = currentVelocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (temp.isIntersecting(new

                Line(p2, p3))) {
            currentVelocity = currentVelocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (temp.isIntersecting(new

                Line(p3, p4))) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (temp.isIntersecting(new

                Line(p4, p5))) {
            currentVelocity = currentVelocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (temp.isIntersecting(new

                Line(p5, p6))) {
            currentVelocity = currentVelocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) (this.getCollisionRectangle().getUpLeft().getX()),
                (int) (this.getCollisionRectangle().getUpLeft().getY()),
                (int) (this.getCollisionRectangle().getWidth()),
                (int) this.getCollisionRectangle().getHeight());
    }

    @Override
    public void timePassed() {
        biuoop.KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
        //checking if ball in paddle
        for (Ball ball : balls) {
            if (isBallInPaddle(ball)) {
                ball.setVelocity(new Velocity(-ball.getVelocity().getDx(), ball.getVelocity().getDy()));
                if (ball.getX() <= this.getCollisionRectangle().getDownLeft().getX()
                        + this.getCollisionRectangle().getWidth() / 2) {
                    ball.setCenter(new Point(ball.getX(), this.getCollisionRectangle().getUpRight().getY() - 5));
                }
                ball.setCenter(new Point(ball.getX(), this.getCollisionRectangle().getUpRight().getY() - 5));
            }
        }
    }

    /**
     * this function take the paddle a step left.
     */
    public void moveLeft() {
        this.rec.setUpLeft(new geometry.Point(this.rec.getUpLeft().getX() - this.velocity,
                this.rec.getUpLeft().getY()));
        if (this.rec.getUpLeft().getX() < 22) {
            this.rec.setUpLeft(new geometry.Point(this.rec.getUpLeft().getX() + this.velocity,
                    this.rec.getUpLeft().getY()));
        }
    }

    /**
     * this function take the paddle a step right.
     */
    public void moveRight() {
        this.rec.setUpLeft(new geometry.Point(this.rec.getUpLeft().getX() + this.velocity,
                this.rec.getUpLeft().getY()));
        if (this.rec.getUpRight().getX() > 780) {
            this.rec.setUpLeft(new geometry.Point(this.rec.getUpLeft().getX() - this.velocity,
                    this.rec.getUpLeft().getY()));
        }
    }

    /**
     * this function adds the paddle to the lists of the sprites and the collidable.
     *
     * @param g - the game that we want to add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
