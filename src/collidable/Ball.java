package collidable;

import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import listener.HitListener;
import listener.HitNotifier;
import sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Cohen, ID=208428714
 * The class collidable.Ball represents a circle.
 */
public class Ball implements Sprite, HitNotifier {
    // Fields
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity;
    private final GameEnvironment game;
    private final List<HitListener> hitListeners;

    /**
     * This is a Constructor function for a collidable.Ball.
     *
     * @param center - geometry.Point center of the circle.
     * @param r      - the radius of the circle.
     * @param color  - the color of the circle.
     * @param game   - the game environment of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.game = game;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * this is an accessor function for the x value of the center of the circle.
     *
     * @return - return the x value of the center of the circle.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * this is an accessor function for the y value of the center of the circle.
     *
     * @return - return the y value of the center of the circle.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * this function set the center of the ball.
     * @param p - the point that we want to be the center of the ball.
     */
    public void setCenter(Point p) {
        this.center = p;
    }

    /**
     * this is an accessor function to the radius of the circle.
     *
     * @return - return the radius of the circle.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * this is an accessor function to the color of the circle.
     *
     * @return - return the color of the circle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The function returns the trajectory of the ball.
     *
     * @return the trajectory of the ball.
     */
    public Line getTrajectory() {
        Velocity v = this.velocity;
        Point p = v.applyToPoint(new Point(this.center.getX() * 2, this.center.getY() * 2));
        if (this.getVelocity().getDy() < 0) {
            p.setY(p.getY() - 1);
        }
        if (this.getVelocity().getDy() > 0) {
            p.setY(p.getY() + 1);
        }
        if (this.getVelocity().getDx() < 0) {
            p.setX(p.getX() - 1);
        }
        if (this.getVelocity().getDx() > 0) {
            p.setX(p.getX() + 1);
        }
        return new Line(this.center, v.applyToPoint(this.center));
    }

    /**
     * this function draws a circle.
     *
     * @param surface - the surface that we want to print on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * this function set a velocity for a ball.
     *
     * @param v - the velocity that we want to put in the object.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * this function set a velocity for a ball.
     *
     * @param dx - the change in the X-axis.
     * @param dy - the change in the Y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this is an accessor function to get the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This function moves the ball a single step.
     */
    public void moveOneStep() {
        Line trajectory = this.getTrajectory();
        CollisionInfo boom = this.game.getClosestCollision(trajectory);
        //if there aren't hit
        if (boom == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        //there is a hit
        Collidable clash = boom.collisionObject();
        //set the new velocity after the hit
        this.velocity = clash.hit(this, boom.collisionPoint(), this.velocity);
    }

    /**
     * this function adds the ball to the sprite list.
     *
     * @param g the game environment that we want to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * this function remove the ball from the game.
     * @param g - the game that we'll remove the ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}