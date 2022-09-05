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
 * the second level - wide easy.
 */
public class WideEasy implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        double rightDeg = 15;
        double leftDeg = 297;
        for (int i = 0; i < 5; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(rightDeg, 10));
            velocityList.add(Velocity.fromAngleAndSpeed(leftDeg, 10));
            rightDeg += 15;
            leftDeg += 12;
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Color color = new Color(46, 196, 196);
        return new Block(new Rectangle(new Point(0, 0), 800, 600), color);
    }

    @Override
    public List<Block> blocks() {
        Color[] colorArray = new Color[15];
        colorArray[0] = Color.RED;
        colorArray[1] = Color.red;
        colorArray[2] = Color.orange;
        colorArray[3] = Color.orange;
        colorArray[4] = Color.yellow;
        colorArray[5] = Color.yellow;
        colorArray[6] = Color.green;
        colorArray[7] = Color.green;
        colorArray[8] = Color.green;
        colorArray[9] = Color.blue;
        colorArray[10] = Color.blue;
        colorArray[11] = Color.pink;
        colorArray[12] = Color.pink;
        colorArray[13] = Color.cyan;
        colorArray[14] = Color.cyan;
        List<Block> listBlock = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Block block = new Block(new Rectangle(new Point(50 * i + 22, 250), 50, 20), colorArray[i]);
            listBlock.add(block);
        }
        return listBlock;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
