package gui.shapes;
import java.util.List;

/**
 * @author Aviya elgrably 209251891
 * A line (actually a line-segment) connects two points
 * -- a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */

public class Line {
    //fields
    private Point start;
    private Point end;
    private final double epsilon;

    /**
     * A builder that initializes the start and end points of the line.
     *
     * @param start - the point start of the line
     * @param end   - the point end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        epsilon = Math.pow(10, -5);
    }

    /**
     * Initializes 2 points by x, y - beginning and end of the line.
     *
     * @param x1 - the x value of the start point
     * @param y1 - the y value of the start point
     * @param x2 - the x value of the end point
     * @param y2 - the y value of the start point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        epsilon = Math.pow(10, -5);
    }

    /**
     * @return the distance from start to end
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return Returns the middle point of the line
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param other - the second line
     * @return The method returns true if the lines are cut at one point, and if not - it returns false
     */
    public boolean isIntersecting(Line other) {
        Point cut = intersectionWith(other);
        return cut != null;
    }

    /**
     * @param other - the second line
     * @return - true/false - if start points and the end points are equals
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end));
    }


    /**
     * The method checks if the point of intersection is within the range of x and y values.
     *
     * @param intersectPoint - The point of intersection between the lines
     * @param begin          - The starting point of the range
     * @param finish         - The finish point of the range
     * @return - true/false
     */
    public boolean isInRange(Point intersectPoint, Point begin, Point finish) {
        double xMax = Math.max(begin.getX(), finish.getX());
        double yMax = Math.max(begin.getY(), finish.getY());
        double xMin = Math.min(begin.getX(), finish.getX());
        double yMin = Math.min(begin.getY(), finish.getY());
        double intersectX = intersectPoint.getX();
        double intersectY = intersectPoint.getY();
        return intersectX >= xMin && intersectX <= xMax && intersectY >= yMin && intersectY <= yMax;
    }

    /**
     * If the starting point is equal to the straight end point it is actually a point.
     *
     * @return true/false
     */
    private boolean thisIsPoint() {
        return this.start.equals(this.end);
    }

    /**
     * If the starting point is equal to the straight end point it is actually a point.
     *
     * @param other - the second line
     * @return true/false
     */
    private boolean otherIsPoint(Line other) {
        return other.start.equals(other.end);
    }

    /**
     * If the x value of the beginning is equal to the value of the end
     * - the line is parallel to the y axis.
     *
     * @return true/false
     */
    private boolean parallelToY() {
        if (this.start != null) {
            return this.start.getX() == this.end.getX();
        }
        return false;
    }

    /**
     * If the points are equal.
     *
     * @param other - the second line
     * @return true/false
     */
    private boolean itIsSamePoint(Line other) {
        return this.start.equals(other.start);
    }

    /**
     * The method checks whether the aggregation is at one
     * point by calculating the distance from the start / end point to the middle.
     *
     * @param other - the second line
     * @return true/false
     */
    private boolean isIntersectionInOnePoint(Line other) {
        Point midThis = this.middle();
        Point midOther = other.middle();
        double diff = midThis.distance(this.start) + midOther.distance(other.start) - midThis.distance(midOther);
        return Math.abs(diff) < epsilon;
    }

    /**
     * @param other - the second line
     * @return the point of intersection
     */
    private Point findIntersection(Line other) {
        if (this.start.equals(other.start()) || this.start.equals(other.end())) {
            return this.start;
        }
        return this.end;
    }

    /**
     * @return double to the incline of the line
     */
    private double getIncline() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * @return the y value of the intercept point
     */
    private double getYIntercept() {
        return this.start.getY() - 1 * getIncline() * this.start.getX();
    }

    /**
     * This method filters accuracies that cause the code
     * to be incorrect even though it is correct,
     * by comparing it to a very small number.
     *
     * @param x - The first parameter with which to compare
     * @param y - The second parameter with which to compare
     * @return true/false
     */
    private boolean epsilonCmp(double x, double y) {
        return Math.abs(x - y) < epsilon;
    }

    /**
     * A method that examines all kinds of cases,
     * types of aggregation of straight lines.
     * In any case if there is a cohesion, it returns the point. If not- null.
     *
     * @param other - the second line
     * @return - the point of intersection
     */
    public Point intersectionWith(Line other) {
        //If the lines are parallel to the Y axis,
        // if we consider whether the slope we will
        // have to divide by zero and therefore we will check before.
        if (parallelToY() && other.parallelToY()) {
            if (this.start.getX() != other.start.getX()) {
                return null;
            }
            if (isIntersectionInOnePoint(other)) {
                return findIntersection(other);
            }

            /*If only one of the axes is parallel to the y-axis,
             * we will classify each one to find who is parallel and who is not.
             * Then we will check if the x values are within the range,
             * and if so - we will check if the y satisfies the conditions of the second line.
             */
        } else if (parallelToY() || other.parallelToY()) {
            Line ver = parallelToY() ? this : other;
            Line notVertical = parallelToY() ? other : this;
            double minXNotVert = Math.min(notVertical.end.getX(), notVertical.start.getX());
            double maxXNotVert = Math.max(notVertical.end.getX(), notVertical.start.getX());
            if (ver.start.getX() >= minXNotVert && ver.start.getX() <= maxXNotVert) {
                double vsx = ver.start.getX();
                Point intersect = new Point(vsx, vsx * notVertical.getIncline() + notVertical.getYIntercept());
                if (isInRange(intersect, ver.start, ver.end)) {
                    return intersect;
                }
                return null;
            }
            return null;
        }

        //calculate the inclines and the y intercept
        double a1 = getIncline();
        double a2 = other.getIncline();
        double b1 = getYIntercept();
        double b2 = other.getYIntercept();

        //if the both lines are only one point - check if they are equals
        if (thisIsPoint() && otherIsPoint(other)) {
            if (itIsSamePoint(other)) {
                return new Point(other.start.getX(), other.end.getY());
            }
            return null;
        }

        //this is a point, other is line, check if there is a intersect between them
        if (thisIsPoint() && !otherIsPoint(other)) {
            if (epsilonCmp(this.start.getY(), a2 * this.start.getX() + b2)) {
                if (isInRange(this.start, other.start, other.end)) {
                    return new Point(this.start.getX(), this.start.getY());
                }
            }
        }

        //other is a point, "our line" is line, check if there is a intersect between them
        if (otherIsPoint(other) && !thisIsPoint()) {
            if (epsilonCmp(other.start.getY(), a1 * other.start.getX() + b1)) {
                if (isInRange(other.start(), this.start, this.end)) {
                    return new Point(other.start.getX(), other.start.getY());
                }
            }
        }

        if (a1 != a2) {
            double intersectX = (b2 - b1) / (a1 - a2); //Cutting point between the lines - the X
            double intersectY = a1 * intersectX + b1; //Cutting point between the lines - the Y
            Point intersectPoint = new Point(intersectX, intersectY); //Cutting point
            if (isInRange(intersectPoint, other.start(), other.end())
                    && (isInRange(intersectPoint, this.start, this.end))) {
                return intersectPoint;
            }
            return null;
        }
        //If the slopes are equal, not parallel to the axis,
        //and also intersected by the Y axis at a different point -
        // there is no cutting with certainty
        if (a1 == a2) {
            if (b1 != b2) {
                return null;
                //If there is the same cut if the y-axis, we will check if the cut is single
            } else if (b1 == b2) {
                if (isIntersectionInOnePoint(other)) {
                    return findIntersection(other);
                }
            }
        }
        return null;
    }

    /**
     * In total there can be 2 points of intersection and below.
     * If there is one in the list of points - we will return it.
     * If there are 2 - find with the help of the distance
     * method what is closer to the beginning of the line and we will return it.
     *
     * @param rect - the Rectangle
     * @return - the closest Intersection To Start Of this Line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        } else if (pointList.size() == 1) {
            return ((Point) pointList.get(0));
        } else if (pointList.size() == 2) {
            double d1 = this.start.distance((Point) pointList.get(0));
            double d2 = this.start.distance((Point) pointList.get(1));
            if (d1 < d2) {
                return (Point) pointList.get(0);
            }
            return (Point) pointList.get(1);
        }
        return null;
    }

    /**
     * @param point - the point that we want to check
     * @param other - the line
     * @return - true/false
     */
    public boolean isPointOnLine(Point point, Line other) {
        double a = point.distance(other.start);
        double b = point.distance(other.end);
        double c = other.start.distance(other.end);
        if (epsilonCmp(a + b, c)) {
            return true;
        }
        return false;
    }
}
