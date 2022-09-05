package gui.collision;

import biuoop.DrawSurface;
import gui.game.Velocity;
import gui.shapes.Ball;
import gui.shapes.Rectangle;
import gui.shapes.Point;

/**
 * @author Aviya elgrably 209251891
 * a interface of Collidable.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  - the collision point
     * @param currentVelocity - the velocity before the hit
     * @param hitter          - the ball
     * @return - the velocity after hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * draw on d.
     *
     * @param d - the surface
     */
    void drawOn(DrawSurface d);
}
