package gui.shapes;

/**
 * @author Aviya elgrably 209251891
 * A class named a dot initializes 2 variables - x, y.
 * It performs a number of operations -
 * it measures a distance between 2 points,
 * and also checks whether 2 points are equal or not.
 */
public class Point {
    //fields
    private final double x;
    private double y;
    private final double epsilon;


    /**
     * A constructor that accepts the value x and value y,
     * and initializes them to be the local variables.
     *
     * @param x - The value of point x.
     * @param y - The value of point y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        epsilon = Math.pow(10, -5);
    }

    /**
     * the method calculate the distance of this point to the other point.
     *
     * @param other - The value of the point at which the distance is measured.
     * @return d - the distance between the points.
     */
    public double distance(Point other) {
        double base = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2);
        return Math.sqrt(base);
    }

    /**
     * This method checks if 2 points are equal.
     *
     * @param other - The point to which one compares.
     * @return The method returns true if 2 points are equal, and false if not.
     */
    public boolean equals(Point other) {
        if (other != null) {
            return ((Math.abs(this.x - other.x) < epsilon) && (Math.abs(this.y - other.y) < epsilon));
        }
        return false;
    }

    /**
     * @return double - the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return double - the y value of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param yNew - the new y that we want to set.
     */
    public void setY(double yNew) {
        this.y = yNew;
    }
}
