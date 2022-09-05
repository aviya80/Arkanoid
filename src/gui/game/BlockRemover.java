package gui.game;

import gui.shapes.Ball;
import gui.shapes.Block;

/**
 * @author Aviya elgrably 209251891
 * the class of BlockRemover.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * @param gameLevel          - the game.
     * @param removedBlocks - the counter.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit - the block.
     * @param hitter   - the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeSprite(beingHit);
        gameLevel.removeCollidable(beingHit);
        remainingBlocks.decrease(1);
    }
}
