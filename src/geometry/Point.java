package geometry;

/**
 * @author Amit Cohen, ID=208428714
 * The class represent a point.
 */
public class Point {
    // Fields
    private double x;
    private double y;

    /**
     * This is a construction method for a point.
     *
     * @param x - the 'x' value in X-axis.
     * @param y - the 'y' value in Y-axis.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function returns the distance from current point to another.
     *
     * @param other - the other point we want to know the distance to.
     * @return - the distance between those 2 points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
    }

    /**
     * this function set a x value of a point.
     * @param x1 - the value that we want to put.
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * this function set a y value of a point.
     * @param y1 - the value that we want to put in.
     */
    public void setY(double y1) {
        this.y = y1;
    }

    /**
     * This function returns true if the points are identical.
     *
     * @param other - the points that we want to check if they are identical.
     * @return true if the points identical, otherwise false.
     */
    public boolean equals(Point other) {
        double eps = Math.pow(10, -2);
        if (Math.abs(other.getX() - this.getX()) < eps) {
            if (Math.abs(other.getY() - this.getY()) < eps) {
                return true;
            }
        }
        return this.x == other.x && this.y == other.y;
    }

    /**
     * This is an access method for the 'x' value of the point.
     *
     * @return the 'x' value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * this is an access method for the 'y' value of the point.
     *
     * @return the 'y' value of the point.
     */
    public double getY() {
        return this.y;
    }

}
