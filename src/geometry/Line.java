package geometry;

/**
 * @author Amit Cohen, ID=208428714
 * The class geometry.Line represents a 2D geometry.Line.
 */
public class Line {
    // Fields
    private final Point p1;
    private final Point p2;

    /**
     * This is a constructor function for geometry.Line.
     *
     * @param start - the geometry.Point where the line starts.
     * @param end   - the geometry.Point where line ends.
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }

    /**
     * This is a constructor function for geometry.Line.
     *
     * @param x1 - the 'x' value of the 1st point.
     * @param y1 - the 'y' value of the 1st point.
     * @param x2 - the 'x' value of the 2nd point.
     * @param y2 - the 'y' value of the 2nd point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**
     * this is an accessor function to get p1 point.
     *
     * @return the point p1.
     */
    public Point getP1() {
        return this.p1;
    }

    /**
     * this is an accessor function to get p2 point.
     *
     * @return the point p2.
     */
    public Point getP2() {
        return this.p2;
    }

    /**
     * This function returns true if the line is vertical.
     *
     * @return true if line is vertical, otherwise false.
     */
    public boolean isVertical() {
        return this.p1.getX() == this.p2.getX();
    }

    /**
     * This function returns true if the line is a dot.
     *
     * @return true if the line is a dot.
     */
    public boolean isDot() {
        return this.p1.getX() == this.p2.getX() && this.p1.getY() == this.p2.getY();
    }

    /**
     * this is an accessor function for the incline of the line.
     *
     * @return the incline of the line.
     */
    public double getIncline() {
        //if the line is a dot, return 0
        if (this.isDot()) {
            return 0;
        }
        //return the absolute value of infinity instead of dividing by 0
        if ((this.p1.getX()) - this.p2.getX() == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return ((this.p1.getY() - this.p2.getY()) / ((this.p1.getX()) - this.p2.getX()));
    }

    /**
     * this function returns the constant for the line equation y=mx+a , a is the constant.
     *
     * @return the constant of the line equation.
     */
    public double getConstant() {
        return (this.p1.getY() - this.getIncline() * this.p1.getX());
    }

    /**
     * this function returns the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.p1.distance((p2));
    }

    /**
     * this function return the middle of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((this.p1.getX() + this.p2.getX()) / 2, (this.p1.getY() + this.p2.getY()) / 2);
    }

    /**
     * This function returns the start of the line.
     *
     * @return a point the start of the line.
     */
    public Point start() {
        return this.p1;
    }

    /**
     * this function returns the end point of the line.
     * @return the end point of the line.
     */
    public Point end() {
        return this.p2;
    }

    /**
     * this function returns true if the lines intersecting each other.
     *
     * @param other the other line that we want to check if they intersect each other.
     * @return true if lines intersecting.
     */
    public boolean isIntersecting(Line other) {
        //epsilon used for numbers that are really close to 0 (because the numbers are double)
        //every check for equality will contain epsilon because of the double numbers.
        double epsilon = Math.pow(10, -8);
        //checking if both lines are vertical
        if (this.isVertical() && other.isVertical() && !this.isDot() && !other.isDot()) {
            //if the x value is different, they won't intersect.
            if (this.p1.getX() != other.p1.getX()) {
                return false;
            }
            //if the y value between the y values of the line, they intersect
            return (other.p1.getY() <= this.p1.getY() && this.p2.getY() <= other.p1.getY())
                    || (this.p1.getY() <= other.p1.getY() && other.p2.getY() <= this.p2.getY());

        }
        //if one on the lines is a dot, checking if other line contain this dot
        if (this.isDot()) {
            double maxY2 = Math.max(other.p1.getY(), other.p2.getY());
            double minY2 = Math.min(other.p1.getY(), other.p2.getY());
            double maxX2 = Math.max(other.p1.getX(), other.p2.getX());
            double minX2 = Math.min(other.p1.getX(), other.p2.getX());
            if (other.getIncline() == 0) {
                return (Math.abs(this.p1.getY() - other.p1.getY()) <= epsilon
                        && minX2 <= this.p1.getX() && this.p1.getX() <= maxX2);
            }
            if (other.getIncline() > 10000) {
                return minY2 <= other.p1.getY() && other.p1.getY() <= maxY2
                        && Math.abs(other.p1.getX() - this.p1.getX()) <= epsilon;
            }
            return this.p1.getY() - (other.getIncline() * this.p1.getX() + other.getConstant()) <= epsilon;
        }
        if (other.isDot()) {
            double maxY1 = Math.max(this.p2.getY(), this.p1.getY());
            double minY1 = Math.min(this.p2.getY(), this.p1.getY());
            double maxX2 = Math.max(other.p1.getX(), other.p2.getX());
            double minX2 = Math.min(other.p1.getX(), other.p2.getX());
            if (this.getIncline() == 0) {
                return (Math.abs(other.p1.getY() - this.p1.getY()) <= epsilon
                        && (minX2 <= this.p1.getX() && this.p1.getX() <= maxX2));
            }
            if (this.getIncline() > 100000) {
                return Math.abs(other.p1.getX() - this.p1.getX()) <= epsilon
                        && (minY1 <= other.p1.getY() && other.p1.getY() <= maxY1);

            }
            return other.p1.getY() - (this.getIncline() * other.p1.getX() + this.getConstant()) <= epsilon;
        }
        //if the current line is vertical, and other isn't vertical.
        if (this.isVertical()) {
            //checking if the y value of the none vertical line is between the y values of the vertical line.
            double currentY = other.getIncline() * this.p1.getX() + other.getConstant();
            double maxY = Math.max(this.p2.getY(), this.p1.getY());
            double minY = Math.min(this.p2.getY(), this.p1.getY());
            double maxX = Math.max(other.p1.getX(), other.p2.getX());
            double minX = Math.min(other.p1.getX(), other.p2.getX());
            return minY <= currentY && currentY <= maxY && minX <= this.p1.getX() && this.p1.getX() <= maxX;

        }
        //if the checking line is vertical, and the current line isn't vertical.
        if (other.isVertical()) {
            //checking if the y value of the none vertical line is between the y values of the vertical line.
            double currentY = this.getIncline() * other.p1.getX() + this.getConstant();
            double maxY = Math.max(other.p2.getY(), other.p1.getY());
            double minY = Math.min(other.p2.getY(), other.p1.getY());
            double maxX = Math.max(this.p1.getX(), this.p2.getX());
            double minX = Math.min(this.p1.getX(), this.p2.getX());
            return minY <= currentY && currentY <= maxY && minX <= other.p1.getX() && other.p1.getX() <= maxX;

        }
        //checking if both lines have the same incline.
        if (this.getIncline() == other.getIncline()) {
            //checking if both lines have the same equation.
            if (this.getConstant() == other.getConstant()) {
                //if both lines contains same x, they intersect.
                return (this.p1.getX() <= other.p2.getX() && other.p2.getX() <= this.p2.getX())
                        || (this.p1.getX() <= other.p1.getX() && other.p1.getX() <= this.p2.getX())
                        || (this.p2.getX() <= other.p2.getX() && other.p2.getX() <= this.p1.getX())
                        || (this.p2.getX() <= other.p1.getX() && other.p1.getX() <= this.p1.getX());

            }
            //if both lines have same incline but don't have the same constant, they won't intersect.
            return false;
        }
        //lines aren't parallel.
        //find the "x" solution for the lines equation.
        double currentX = -((this.getConstant() - other.getConstant()) / (this.getIncline() - other.getIncline()));
        // if both lines contains that "x", they intersect!
        return (Math.min(other.p1.getX(), other.p2.getX()) < currentX
                || Math.abs(Math.min(other.p1.getX(), other.p2.getX()) - currentX) <= epsilon)
                && (currentX < Math.max(other.p1.getX(), other.p2.getX())
                || Math.abs(currentX - Math.max(other.p1.getX(), other.p2.getX())) <= epsilon)
                && ((Math.min(this.p1.getX(), this.p2.getX()) < currentX
                || Math.abs(currentX - Math.min(this.p1.getX(), this.p2.getX())) <= epsilon)
                && (currentX < Math.max(this.p1.getX(), this.p2.getX()))
                || Math.abs(currentX - Math.max(this.p1.getX(), this.p2.getX())) <= epsilon);
    }

    /**
     * returns the point if lines intersecting, otherwise return null.
     *
     * @param other the other line that we want to check intersection with.
     * @return the intersection point if lines intersect, otherwise return null.
     */
    public Point intersectionWith(Line other) {
        //if lines doesn't intersect return null
        if (!this.isIntersecting(other)) {
            return null;
        }
        //if lines have more than one intersection point return null
        //if this is the same lines, return null
        if (this.equals(other)) {
            //if both lines are one identical dot
            if (this.isDot() && other.isDot()
                    && this.p1.getY() == other.p1.getY() && this.p1.getX() == other.p1.getX()) {
                return this.p1;
            }
            return null;
        }
        //lines will have more than one intersection point only if they are parallel
        if (this.getIncline() == other.getIncline()) {
            //checking if lines are parallel to the x-axis
            if (this.getIncline() == 0) {
                if (!(this.p1.getX() == other.p2.getX() || this.p2.getX() == other.p1.getX())) {
                    return null;
                }
            }
            //checking if the lines have exactly one intersection point
            if (!((this.p1.getY() == other.p2.getY()) || (this.p2.getY() == other.p1.getY()))) {
                return null;
            }
            if ((this.p1.getY() == other.p2.getY())) {
                return new Point(this.p1.getX(), this.p1.getY());
            }
            if ((this.p2.getY() == other.p1.getY())) {
                return new Point(this.p2.getX(), this.p2.getY());
            }
        }
        //if one of the lines are dots, the intersection point will be the point
        if (this.isDot()) {
            return this.p1;
        }
        if (other.isDot()) {
            return other.p1;
        }
        //if one of the lines is vertical
        if (this.isVertical()) {
            return new Point(this.p1.getX(), other.getIncline() * this.p1.getX() + other.getConstant());
        }
        if (other.isVertical()) {
            return new Point(other.p1.getX(), this.getIncline() * other.p1.getX() + this.getConstant());
        }
        //the x value of the point
        double currentX = -((this.getConstant() - other.getConstant()) / (this.getIncline() - other.getIncline()));
        //the y value of the point
        double currentY = this.getIncline() * currentX + this.getConstant();
        //return the point
        return new Point(currentX, currentY);
    }

    /**
     * This function returns true if lines are identical, otherwise return false.
     *
     * @param other the line that we want to check if they are equal.
     * @return true if lines identical.
     */
    public boolean equals(Line other) {
        return this.p1.equals(other.start()) && this.p2.equals(other.end());
    }

    /**
     * this function returns the closest intersection to the start of the line.
     * @param rect - the rect that the line intersects with.
     * @return the closest intersection to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //if list is empty, return null
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        //if there is only one point in the list, return the point
        if (rect.intersectionPoints(this).size() == 1) {
            return rect.intersectionPoints(this).get(0);
        }
        //if there are 2 points in the list, return the one that closest to start
        Point point1 = rect.intersectionPoints(this).get(0);
        Point point2 = rect.intersectionPoints(this).get(1);
        if (point1.distance(this.p1) <= point2.distance(this.p1)) {
            return point1;
        }
        return point2;
    }
}
