package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Cohen, ID=208428714
 * The class geometry.Rectangle represents geometry.Rectangle.
 */
public class Rectangle {
    //Fields
    private Point upLeft;
    private Point upRight;
    private Point downLeft;
    private Point downRight;
    private Line up;
    private Line down;
    private Line right;
    private Line left;
    private double width;
    private double height;

    /**
     * this is a constructor method.
     *
     * @param upperLeft - the up left point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upLeft = upperLeft;
        this.width = width;
        this.height = height;
        //we will find all the vertex of the geometry.Rectangle
        this.upRight = new Point(this.upLeft.getX() + width, this.upLeft.getY());
        this.downRight = new Point(this.upLeft.getX() + width, this.upLeft.getY() + height);
        this.downLeft = new Point(this.upLeft.getX(), this.upLeft.getY() + height);
        this.up = new Line(this.upLeft, this.upRight);
        this.down = new Line(this.downLeft, this.downRight);
        this.left = new Line(this.upLeft, this.downLeft);
        this.right = new Line(this.upRight, this.downRight);
    }

    /**
     * this function sets the upper left point of the rectangle.
     *
     * @param p - the up left point.
     */
    public void setUpLeft(Point p) {
        this.upLeft = p;
        this.upRight = new Point(this.upLeft.getX() + this.width, this.upLeft.getY());
        this.downRight = new Point(this.upLeft.getX() + this.width, this.upLeft.getY() + this.height);
        this.downLeft = new Point(this.upLeft.getX(), this.upLeft.getY() + this.height);
        this.up = new Line(this.upLeft, this.upRight);
        this.down = new Line(this.downLeft, this.downRight);
        this.left = new Line(this.upLeft, this.downLeft);
        this.right = new Line(this.upRight, this.downRight);
    }

    /**
     * this function returns a list of intersection points between a line and a rectangle.
     * @param line - the line that we want to get the intersection point.
     * @return a list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //we will create a list of points
        List<Point> list = new ArrayList<>();
        //if line is intersect with one of the edges of the rectangle, add it to the list
        if (line.intersectionWith(this.up) != null) {
            list.add(line.intersectionWith(this.up));
        }
        if (line.intersectionWith(this.down) != null) {
            list.add(line.intersectionWith(this.down));
        }
        if (line.intersectionWith(this.left) != null) {
            list.add(line.intersectionWith(this.left));
        }
        if (line.intersectionWith(this.right) != null) {
            list.add(line.intersectionWith(this.right));
        }
        return list;
    }

    /**
     * this is an accessor method.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * this is an accessor method.
     * @return the up left point of the rectangle.
     */
    public Point getUpLeft() {
        return this.upLeft;
    }

    /**
     * this is an accessor method.
     * @return the up right point of the rectangle.
     */
    public Point getUpRight() {
        return this.upRight;
    }

    /**
     * this is an accessor method.
     * @return the down left point of the rectangle.
     */
    public Point getDownLeft() {
        return this.downLeft;
    }

    /**
     * this is an accessor method.
     * @return the down right point of the rectangle.
     */
    public Point getDownRight() {
        return this.downRight;
    }

    /**
     * this is an accessor method.
     * @return the up line of the rectangle.
     */
    public Line getUp() {
        return this.up;
    }

    /**
     * this is an accessor method.
     * @return the down line of the rectangle.
     */
    public Line getDown() {
        return this.down;
    }

    /**
     * this is an accessor method.
     * @return the left line of the rectangle.
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * this is an accessor method.
     * @return the right line of the rectangle.
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * this is an accessor method.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
}
