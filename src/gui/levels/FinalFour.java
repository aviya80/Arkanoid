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
 * the final four - last level of the game.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(330, 7));
        velocityList.add(Velocity.fromAngleAndSpeed(30, 7));
        velocityList.add(Velocity.fromAngleAndSpeed(0, 7));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 12;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Color color = new Color(62, 155, 226);
        return new Block(new gui.shapes.Rectangle(new Point(0, 0), 800, 600), color);
    }

    @Override
    public List<Block> blocks() {
        double startX = 780;
        double width = 50;
        Color[] colorArray = new Color[7];
        colorArray[0] = Color.gray;
        colorArray[1] = Color.red;
        colorArray[2] = Color.YELLOW;
        colorArray[3] = Color.green;
        colorArray[4] = Color.white;
        colorArray[5] = Color.pink;
        colorArray[6] = Color.cyan;
        List<Block> listBlock = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                double x = startX - width * (j + 1);
                Block block = new Block(new Rectangle(new Point(x, 100 + i * 20), 50, 20), colorArray[i]);
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
