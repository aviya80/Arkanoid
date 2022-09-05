package gui.game;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Aviya elgrably 209251891.
 * class of that run the animation.
 */
public class AnimationRunner {
    private final GUI gui;
    private final biuoop.Sleeper sleeper;
    private final int framesPerSecond;
    private final biuoop.KeyboardSensor keyboard;

    /**
     * constructor.
     *
     * @param gui - the gui of the game.
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 60;
        this.sleeper = new biuoop.Sleeper();
        this.gui = gui;
        this.keyboard = this.gui.getKeyboardSensor();
    }

    /**
     * @param animation - the animation.
     */
    public void run(Animation animation) {

        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
