package gui.game;

import gui.shapes.Ball;
import gui.shapes.Block;

/**
 * @author Aviya elgrably 209251891.
 * interface of the HitListener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - the block.
     * @param hitter   - the ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
