package geometry;

/**
 * @author Amit Cohen, ID=208428714
 * This class represents the geometry.Velocity of a ball.
 */
public class Velocity {
    // Fields
    private double dx;
    private double dy;

    /**
     * This is a constructor function for the velocity.
     *
     * @param dx - the difference in the X-axis.
     * @param dy - the difference in the Y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This function gets an angle and speed and convert them to dx,dy.
     *
     * @param angle - the angle that the object goes with.
     * @param speed - the speed of the object.
     * @return a new velocity by dx,dy.
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        //convert angle to radians
        angle = Math.toRadians(angle);
        return new Velocity(speed * Math.sin(angle), speed * -Math.cos(angle));
    }

    /**
     * this function returns the speed of a ball.
     * @return the speed of a ball.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow((this.dy), 2));
    }

    /**
     * this function sets dx for the velocity.
     * @param dx1 - the dx that we want to set.
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * this function sets dy for the velocity.
     * @param dy1 - the dy that we want to set.
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * This is an accessor function to get the dx of the velocity.
     *
     * @return the dx of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This is an accessor function to get the dy of the velocity.
     *
     * @return the dy of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * this function gets a point, and returns a new point with the position (x+dx,y+dy).
     *
     * @param p the point that we want to move.
     * @return a new point with the center (x+dx,y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

}