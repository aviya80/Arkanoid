package gui.game;

import biuoop.DrawSurface;
import gui.sprite.Sprite;

/**
 * @author Aviya elgrably 209251891
 * class for score insicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor.
     *
     * @param score - the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d - the Surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 15, "Score: " + this.score.getValue(), 18);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * getter.
     *
     * @return - the score.
     */
    public Counter getScoreIndicator() {
        return this.score;
    }

    /**
     * @param num - the number that we want to increase.
     */
    public void increaseScore(int num) {
        this.score = new Counter(score.getValue() + num);
    }
}
