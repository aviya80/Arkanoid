package gui.levels;

import gui.game.Velocity;
import gui.shapes.Block;
import gui.sprite.Sprite;

import java.util.List;

/**
 * @author Aviya elgrably 209251891.
 * interface of all the information of each level.
 */
public interface LevelInformation {
    /**
     * @return - numbers of the ball in each level.
     */
    int numberOfBalls();

    /**
     * @return - The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return - the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return - the width of the paddle.
     */
    int paddleWidth();

    /**
     * @return - the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return - Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return - The Blocks that make up this level, each block contains,
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return - Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
