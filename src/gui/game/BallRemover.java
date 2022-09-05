package gui.game;

import gui.shapes.Ball;
import gui.shapes.Block;

/**
 * @author Aviya elgrably 209251891
 * class for remove the ball from the game.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * constructor.
     *
     * @param gameLevel           - the game.
     * @param remainingBalls - how much balls remain in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - the block.
     * @param hitter   - the ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
