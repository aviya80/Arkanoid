package gui.game;
import gui.shapes.Point;
/**
 * @author Aviya elgrably 209251891
 * Class of velocity, by x-axis and y-axis, or by angle and speed.
 */
public class Velocity {
    private static double epsilon;
    //fields
    private double dx;
    private double dy;

    /**
     * @param dx - Axis speed x
     * @param dy - Axis speed y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        epsilon = Math.pow(10, -5);
    }

    /**
     * @return double - the sign on speed in dx
     */

    public double getSignDx() {
        if (this.dx < 0) {
            return (-1);
        } else if (this.dx == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * @return double - the sign on speed in dy
     */
    public double getSignDy() {
        if (this.dy < 0) {
            return (-1);
        } else if (this.dx == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * @return the velocity in axis-x
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the velocity in axis-y
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p - the point
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * @param angle - The angle with respect to the y-axis
     * @param speed - The speed of the ball
     * @return - Returns Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        if (Math.abs(dx) < epsilon) {
            dx = 0;
        }
        if (Math.abs(dy) < epsilon) {
            dy = 0;
        }
        return new Velocity(dx, -dy);
    }

    /**
     * @param velocity - the velocity
     */
    public void setVelocity(Velocity velocity) {
        this.dy = velocity.getDy();
        this.dx = velocity.getDx();
    }

    /**
     * @return - double. The square root of dx and dy.
     */
    public double getVelocity() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}