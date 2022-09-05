package gui.game;

import gui.collision.Collidable;
import gui.collision.CollisionInfo;
import gui.shapes.Line;
import gui.shapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aviya elgrably 209251891
 * Class of Game Environment, initials the Environment of the game
 */
public class GameEnvironment {
    //fields
    private final List<Collidable> collidableList;

    /**
     * constructor to the class.
     *
     * @param collidableList - the list of collidables
     */
    public GameEnvironment(List<Collidable> collidableList) {
        this.collidableList = collidableList;
    }

    /**
     * another constructor the initialized the list.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * method get of collidable List.
     *
     * @return List<Collidable>
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c -  the given collidable
     */

    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - The trajectory of the ball
     * @return CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //there is no collision
        if (collidableList.isEmpty()) {
            return null;
        }
        //We will initialize double mid, and the Collision Info
        Double minD = null;
        CollisionInfo minCollisionInfo = new CollisionInfo(null, null);
        for (Collidable object : collidableList) {
            Point p = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            if (p != null) {
                double d = trajectory.start().distance(p);
                //find the minimum intersection
                if (minD == null || d < minD) {
                    minD = d;
                    minCollisionInfo = new CollisionInfo(p, object);
                }
            }
        }
        return minCollisionInfo;
    }
}