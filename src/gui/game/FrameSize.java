package gui.game;

import gui.shapes.Point;

/**
 * @author Aviya elgrably 209251891
 * A class called - frame size. Allows us to
 * access and control all the frames of the balls
 * - this is a necessary thing in light of the fact that
 * the balls only need to move in certain places.
 */

public class FrameSize {
    //fields
    private final Point pointStart;
    private final int length;
    private final int width;

    /**
     * @param pointStart - The starting point of the frame.
     * @param length     - of the frame
     * @param width      - the width of the frame
     */
    public FrameSize(Point pointStart, int length, int width) {
        this.pointStart = pointStart;
        this.length = length;
        this.width = width;
    }

    /**
     * @return the length of the frame
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @return the width of the frame
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return the start point of the frame
     */
    public Point getPointStart() {
        return this.pointStart;
    }
}
