package gui.game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gui.sprite.SpriteCollection;

import java.awt.Color;

/**
 * @author Aviya elgrably 209251891.
 * class that count down until the animation begin.
 */
public class CountdownAnimation implements Animation {

    private int countFrom;
    private final SpriteCollection gameScreen;
    private final Sleeper sleeper;
    private boolean stop;

    /**
     * constructor.
     *
     * @param numOfSeconds - the seconds we want to count.
     * @param countFrom    - from 3.
     * @param gameScreen   - the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.sleeper = new Sleeper();
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (countFrom == (0)) {
            this.stop = true;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.white);
        d.drawText(360, 450, "" + countFrom, 120);
        sleeper.sleepFor(600);
        countFrom--;

    }

    /**
     * @return true/false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
