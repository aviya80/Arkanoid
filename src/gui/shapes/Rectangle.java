package gui.shapes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aviya elgrably 209251891
 * A class of a rectangle,
 * defines the points of the rectangle,
 * the sides - and thus delimits the rectangle on the axis of numbers.
 */

public class Rectangle {
    //fields - 4 vertices and 4 sides of the rectangle
    private final Point upperLeft;
    private Point upperRight;
    private Point bottomRight;
    private Point bottomLeft;
    private Line upperLine;
    private Line bottomLine;
    private Line leftline;
    private Line rightLine;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft - of the triangle
     * @param width     - of the triangle
     * @param height    - of the triangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperLine = new Line(upperLeft, upperRight);
        this.bottomLine = new Line(bottomLeft, bottomRight);
        this.leftline = new Line(upperLeft, bottomLeft);
        this.rightLine = new Line(bottomRight, upperRight);
    }

    /**
     * Create a new rectangle with four points.
     *
     * @param upLeft   - The upper left vertex of the rectangle
     * @param upRight  - The upper right vertex of the rectangle
     * @param botRight - The bottom right vertex of the rectangle
     * @param botLeft  - The bottom left vertex of the rectangle
     * @param width    - between the points
     * @param height   - between the points
     */
    public Rectangle(Point upLeft, Point upRight, Point botRight, Point botLeft, double width, double height) {
        this.upperLeft = upLeft;
        this.upperRight = new Point(upLeft.getX() + width, upLeft.getY());
        this.bottomLeft = new Point(upLeft.getX(), upLeft.getY() + height);
        this.bottomRight = new Point(upLeft.getX() + width, upLeft.getY() + height);
        this.upperLine = new Line(upLeft, upperRight);
        this.bottomLine = new Line(bottomLeft, bottomRight);
        this.leftline = new Line(upLeft, bottomLeft);
        this.rightLine = new Line(bottomRight, upperRight);
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line - The straight with which we test a cut with their rectangle
     * @return - List of intersections between the line and the sides of the rectangle
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<>();

        //For each of the sides of the rectangle,
        // we will check if there is a point of intersection
        // between the line and the side. If there is a point of intersection,
        //  we will add it to the list.
        Point intersectBottom = line.intersectionWith(bottomLine);
        if (line.isIntersecting(rightLine)) {
            pointList.add(new Point(line.intersectionWith(rightLine).getX(), line.intersectionWith(rightLine).getY()));
        }
        if (line.isIntersecting(leftline)) {
            pointList.add(new Point(line.intersectionWith(leftline).getX(), line.intersectionWith(leftline).getY()));
        }
        if (line.isIntersecting(upperLine)) {
            pointList.add(new Point(line.intersectionWith(upperLine).getX(), line.intersectionWith(upperLine).getY()));
        }
        if (line.isIntersecting(bottomLine)) {
            pointList.add(new Point(intersectBottom.getX(), line.intersectionWith(bottomLine).getY()));
        }

        return pointList;
    }


    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper Left of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the upper right of the rectangle
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * @return the bottom right of the rectangle
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }

    /**
     * @return the bottom Left of the rectangle
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * @return the upper line of the rectangle
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * @return the bottom line of the rectangle
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }

    /**
     * @return the left line of the rectangle
     */
    public Line getLeftline() {
        return this.leftline;
    }

    /**
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return this.rightLine;
    }
}
