package gui.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Aviya elgrably 209251891.
 * class of the final of the game. a kind of animation.
 */
public class GameOver implements Animation {
    private final boolean stop;
    private final ScoreIndicator scoreIndicator;
    private final KeyboardSensor keyboard;

    /**
     * constructor.
     *
     * @param k              - the keyboard.
     * @param scoreIndicator - the score indicator.
     */
    public GameOver(KeyboardSensor k, ScoreIndicator scoreIndicator) {
        this.keyboard = k;
        this.scoreIndicator = scoreIndicator;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(250, 250, "Game Over ", 60);
        d.drawText(280, 350, "Your score is "
                + scoreIndicator.getScoreIndicator().getValue(), 30);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
