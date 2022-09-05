package gui.sprite;
import biuoop.DrawSurface;

/**
 * @author Aviya elgrably 209251891
 * interface of Sprite
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d - the Surface
     */

    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
