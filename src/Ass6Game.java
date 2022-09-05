import biuoop.GUI;
import gui.game.AnimationRunner;
import gui.levels.DirectHit;
import gui.levels.FinalFour;
import gui.levels.GameFlow;
import gui.levels.Green3;
import gui.levels.LevelInformation;
import gui.levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aviya elgrably 209251891
 * class that run the game.
 */
public class Ass6Game {

    /**
     * @param args - the arguments from the cmd.
     * @return - list of level info.
     */
    public static List<LevelInformation> levelList(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation directHit = new DirectHit();
        LevelInformation wideEasy = new WideEasy();
        LevelInformation green3 = new Green3();
        LevelInformation finalFour = new FinalFour();

        for (String arg : args) {
            switch (arg) {
                case "1" -> levels.add(directHit);
                case "2" -> levels.add(wideEasy);
                case "3" -> levels.add(green3);
                case "4" -> levels.add(finalFour);
            }
        }

        if ((levels.size() == 0) && (args.length == 0)) {
            levels.add(directHit);
            levels.add(wideEasy);
            levels.add(green3);
            levels.add(finalFour);
        } else if (levels.size() == 0) {
            System.exit(0);

        }
        return levels;
    }

    /**
     * @param args - from the args.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = levelList(args);
        GUI gui = new GUI("Arkanoid", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, keyboard, gui);
        gameFlow.runLevels(levels);
        gui.close();
    }
}
