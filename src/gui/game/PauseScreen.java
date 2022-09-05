package gui.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Aviya elgrably 209251891.
 * class of pause screen.
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;

    /**
     * @param k - the KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
