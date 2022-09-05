package gui.game;

import gui.shapes.Ball;
import gui.shapes.Block;

/**
 * @author Aviya elgrably 209251891.
 * class of Score Tracking Listener that implement HitListener.
 */
public class ScoreTrackingListener implements HitListener {
    // fields
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter - the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * getter.
     *
     * @return - Counter
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * setter of the new score.
     *
     * @param newCounter - the new score.
     */
    public void setCurrentScore(Counter newCounter) {
        this.currentScore = newCounter;
    }

    /**
     * the is a hit, so increase the score for 5.
     *
     * @param beingHit - the block.
     * @param hitter   - the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
