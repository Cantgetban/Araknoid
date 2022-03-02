package collidable;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * @author Amit Cohen, ID=208428714
 * The interface collidable represents a collidable elements.
 */
public interface Collidable {
    /**
     * this is an accessor method that returns the geometry.Rectangle of the collidable block.
     * @return the collidable block.
     */
    Rectangle getCollisionRectangle();

    /**
     * this function returns the velocity that the ball should have after the hit.
     * @param collisionPoint - the intersection point between the ball and the rectangle.
     * @param currentVelocity - the current velocity of the ball
     * @param hitter - the ball that hitted the collidable.
     * @return a new velocity after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
