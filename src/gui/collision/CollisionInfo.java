package gui.collision;

import gui.shapes.Point;

/**
 * @author Aviya elgrably 209251891
 * A class that describes the collision properties -
 * the point of collision and the object with which the ball collided.
 */

public class CollisionInfo {
    //fields
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * @param collisionPoint  - the collision Point
     * @param collisionObject - the collision Object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
