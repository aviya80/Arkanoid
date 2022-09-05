package gui.sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aviya elgrably 209251891
 * Class of Sprite Collection, will hold a collection of sprites.
 */
public class SpriteCollection {
    //fields
    private final List<Sprite> collectionOfSprite;

    /**
     * A builder who initializes the Sprite collection.
     *
     * @param collectionOfSprite - the collection
     */
    public SpriteCollection(List<Sprite> collectionOfSprite) {
        this.collectionOfSprite = collectionOfSprite;
    }

    /**
     * @return collectionOfSprite
     */
    public List<Sprite> getCollectionOfSprite() {
        return collectionOfSprite;
    }

    /**
     * another builder who initializes the Sprite collection.
     */
    public SpriteCollection() {
        this.collectionOfSprite = new ArrayList<>();
    }

    /**
     * this method add s to the collection Of Sprite.
     *
     * @param s - the sprite
     */
    public void addSprite(Sprite s) {
        this.collectionOfSprite.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < collectionOfSprite.size(); i++) {
            collectionOfSprite.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d - the Surface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < collectionOfSprite.size(); i++) {
            collectionOfSprite.get(i).drawOn(d);
        }
    }

    /**
     * @param s - the Sprite.
     */
    public void removeSprite(Sprite s) {
        this.collectionOfSprite.remove(s);
    }
}
