package game;

import collidable.Collidable;
import collidable.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;

/**
 * @author Amit Cohen, ID=208428714
 * The class game.GameEnvironment represents an environment for the game.
 */
public class GameEnvironment {
    //Fields
    private ArrayList<Collidable> list;

    /**
     * this is a constructor function that initialize the list.
     */
    public GameEnvironment() {
        this.list = new ArrayList<Collidable>();
    }

    /**
     * this function adds a collidable to the list.
     *
     * @param c - the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * this is an accessor method.
     *
     * @return the list of the collidables.
     */
    public ArrayList<Collidable> getList() {
        return this.list;
    }

    /**
     * this function sets a collidable list for the game environment.
     *
     * @param list1 the list that we want to set.
     */
    public void setList(ArrayList<Collidable> list1) {
        this.list = list1;
    }

    /**
     * this function returns the closest collision between a line and a rectangle.
     *
     * @param trajectory - the trajectory of the ball.
     * @return the closest intersection between the ball and the rectangle.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //epsilon used for numbers that are really close to 0 (because the numbers are double)
        //every check for equality will contain epsilon because of the double numbers.
        double epsilon = Math.pow(10, -8);
        //if there are no collidable elements, return null
        if (list.isEmpty()) {
            return null;
        }
        boolean flag = false;
        //if there aren't collisions return null
        for (int i = 0; i < this.list.size(); i++) {
            //checking for every collidable in the list if he have intersection points with line
            if (trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle()) != null) {
                //if there are a collision, flag is true
                flag = true;
            }
        }
        //if there are no collisions, return null
        if (!flag) {
            return null;
        }
        //we know that there is a collision, we will find the closest collision point
        Point closest = trajectory.end();
        int j = 0;
        for (int i = 0; i < this.list.size(); i++) {
            //checking for every collidable in the list if there are collision
            if (trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle()) != null) {
                //checking if current collision point is the closest to the start point
                Point cur = trajectory.closestIntersectionToStartOfLine(list.get(i).getCollisionRectangle());
                if (trajectory.start().distance(cur) - trajectory.start().distance(closest) < epsilon) {
                    closest = cur;
                    j = i;
                }
            }

        }
        //return the closest collision
        return new CollisionInfo(closest, this.list.get(j));
    }
}
