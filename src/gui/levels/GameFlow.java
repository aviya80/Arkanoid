package gui.levels;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import gui.game.AnimationRunner;
import gui.game.Counter;
import gui.game.GameLevel;
import gui.game.KeyPressStoppableAnimation;
import gui.game.ScoreIndicator;

import java.util.List;

/**
 * @author Aviya elgrably 209251891.
 * the class which allows transition between levels.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final ScoreIndicator scoreIndicator;
    private final GUI gui;

    /**
     * @param ar  - the AnimationRunner.
     * @param ks  - the KeyboardSensor.
     * @param gui - the gui of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoreIndicator = new ScoreIndicator(new Counter(0));
        this.gui = gui;
    }

    /**
     * @param levels - list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, gui, this.animationRunner, scoreIndicator);
            level.initialize();

            while (!level.noBlocks() && !level.noBalls()) {
                level.run();
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "Space",
                new YouWin(scoreIndicator, keyboardSensor), false, gui));
    }
}
