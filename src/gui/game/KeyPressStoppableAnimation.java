package gui.game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Aviya elgrably 209251891.
 * class that ,make the key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private final KeyboardSensor keyboardSensor;
    private final Animation animation;
    private final Boolean goOut;
    private boolean stop;
    private final GUI gui;

    /**
     * @param keyboardSensor - the keyboard.
     * @param str            - the str.
     * @param animation      - the animation.
     * @param goOut          - go from the game.
     * @param gui            - the gui.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String str,
                                      Animation animation, Boolean goOut, GUI gui) {
        this.keyboardSensor = keyboardSensor;
        this.animation = animation;
        this.goOut = goOut;
        this.stop = false;
        this.gui = gui;
        this.isAlreadyPressed = true;
    }

    /**
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
            this.stop = true;
        } else {
            isAlreadyPressed = false;
        }
        if (goOut && stop) {
            gui.close();
            System.exit(0);
        }
    }

    /**
     * @return true/false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
