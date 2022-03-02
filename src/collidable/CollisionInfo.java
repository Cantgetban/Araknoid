package collidable;

import geometry.Point;

/**
 * @author Amit Cohen, ID=208428714
 * The class CollisonInfo represents a collision.
 */
public class CollisionInfo {
    private Point p;
    private Collidable obj;

    /**
     * this is a constructor method that creates a Collision info object.
     * @param p1 - the point of the collision.
     * @param object - the object that the ball collided with
     */
    public CollisionInfo(Point p1, Collidable object) {
        this.p = p1;
        this.obj = object;
    }

    /**
     * this is an accessor method.
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.p;
    }

    /**
     * this is an accessor method.
     * @return the object of the collision.
     */
    public Collidable collisionObject() {
        return this.obj;
    }
}
