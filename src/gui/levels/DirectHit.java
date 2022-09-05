package gui.levels;

import gui.game.Velocity;
import gui.shapes.Block;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import gui.sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aviya elgrably 209251891.
 * the first level called direct hit.
 */
public class DirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>();
        Velocity v = new Velocity(0, -8);
        listVelocity.add(v);
        return listVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), Color.black);
    }

    @Override
    public List<Block> blocks() {
        List<Block> listBlock = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(380, 100), 40, 40), Color.red);
        listBlock.add(block);
        return listBlock;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }


}
