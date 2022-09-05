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
 * the third level - green 3.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(350, 8));
        velocityList.add(Velocity.fromAngleAndSpeed(20, 8));
        return velocityList;
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Color green = new Color(10, 144, 32);
        return new Block(new gui.shapes.Rectangle(new Point(0, 0), 800, 600), green);
    }

    @Override
    public List<Block> blocks() {
        double startX = 780;
        double width = 50;
        Color[] colorArray = new Color[5];
        colorArray[0] = Color.RED;
        colorArray[1] = Color.gray;
        colorArray[2] = Color.YELLOW;
        colorArray[3] = Color.blue;
        colorArray[4] = Color.white;
        List<Block> listBlock = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                double x = startX - width * (j + 1);
                Block block = new Block(new Rectangle(new Point(x, 100 + i * 25), 50, 25), colorArray[i]);
                listBlock.add(block);
            }
        }
        return listBlock;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
