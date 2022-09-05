package gui.levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.game.Animation;
import gui.game.ScoreIndicator;

/**
 * @author Aviya elgrably 209251891.
 * screen of situation that the player win.
 */
public class YouWin implements Animation {

    private final ScoreIndicator scoreIndicator;
    private final boolean stop;
    private final KeyboardSensor keyboardSensor;

    /**
     * constructor.
     *
     * @param scoreIndicator - the score.
     * @param keyboardSensor - the keyboard.
     */
    public YouWin(ScoreIndicator scoreIndicator, KeyboardSensor keyboardSensor) {
        this.scoreIndicator = scoreIndicator;
        this.stop = false;
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(290, 250, "You Win!", 50);
        d.drawText(240, 350, "Your Score is "
                + scoreIndicator.getScoreIndicator().getValue(), 40);
    }

    /**
     * @return true/false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
