package gui.shapes;

import java.util.ArrayList;
import java.util.List;

import gui.game.HitListener;
import gui.game.HitNotifier;
import gui.sprite.Sprite;
import gui.game.GameLevel;
import gui.game.Velocity;

import biuoop.DrawSurface;
import gui.collision.Collidable;


import java.awt.Color;

/**
 * @author @author Aviya elgrably 209251891
 * A class that describes the "blocking" object
 * with which it collides. She instructs how the ball
 * should behave when it hits the block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields
    private final Rectangle rectangle;
    private final Color color;
    private List<HitListener> hitListeners;

    /**
     * @return - List<HitListener>
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * constructor that Initializes the rectangle.
     *
     * @param rectangle - the block
     */
    public Block(Rectangle rectangle) {
        this(rectangle, Color.black);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @param rectangle - the shape of the block
     * @param color     - of the rectangle
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        //import the 4 ribs of rectangle
        Line right = rectangle.getRightLine();
        Line left = rectangle.getLeftline();
        Line upper = rectangle.getUpperLine();
        Line bottom = rectangle.getBottomLine();
        if ((right.isPointOnLine(collisionPoint, right) || left.isPointOnLine(collisionPoint, left))
                && (upper.isPointOnLine(collisionPoint, upper) || bottom.isPointOnLine(collisionPoint, bottom))) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (right.isPointOnLine(collisionPoint, right) || left.isPointOnLine(collisionPoint, left)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (upper.isPointOnLine(collisionPoint, upper) || bottom.isPointOnLine(collisionPoint, bottom)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        double xUpperLeft = rectangle.getUpperLeft().getX();
        double yUpperLeft = rectangle.getUpperLeft().getY();
        double width = rectangle.getWidth();
        double height = rectangle.getHeight();
        d.setColor(color);
        d.fillRectangle((int) xUpperLeft, (int) yUpperLeft, (int) width, (int) height);
        d.setColor(Color.black);
        d.drawRectangle((int) xUpperLeft, (int) yUpperLeft, (int) width, (int) height);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add the block to the game - to the Collidables and to the Sprites.
     *
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * @param gameLevel - the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        return;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @param hitter - the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
