package gui.levels;

import biuoop.DrawSurface;
import gui.game.GameLevel;
import gui.sprite.Sprite;

import java.awt.Color;

/**
 * @author Aviya elgrably 209251891.
 * class of level name that are Type of sprite.
 */
public class LevelName implements Sprite {
    private final String levelName;

    /**
     * constructor.
     *
     * @param levelName - the name.
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(510, 15, "Level Name: " + levelName, 18);
    }

    @Override
    public void timePassed() {

    }

    /**
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
