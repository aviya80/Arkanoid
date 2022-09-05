package gui.game;

import biuoop.DrawSurface;

/**
 * @author Aviya elgrably 209251891.
 * interface of Animation.
 */
public interface Animation {
    /**
     * @param d - the surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true/false.
     */
    boolean shouldStop();
}
