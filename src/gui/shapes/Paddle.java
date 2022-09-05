package gui.shapes;

import gui.sprite.Sprite;
import gui.game.GameLevel;
import gui.game.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gui.collision.Collidable;

import java.awt.Color;

/**
 * @author Aviya elgrably 209251891
 * Paddles's class. Implements the interfaces -Sprite, Collidable.
 */
public class Paddle implements Sprite, Collidable {
    //fields
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;

    /**
     * constructor for the paddle.
     *
     * @param keyboard - the keyboard
     * @param paddle   - the paddle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle paddle) {
        this.keyboard = keyboard;
        this.paddle = paddle;
    }

    /**
     * get method.
     *
     * @return KeyboardSensor
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * get method.
     *
     * @return Rectangle
     */
    public Rectangle getPaddle() {
        return paddle;
    }

    /**
     * Move the paddle to the left unless it has reached the edge of the screen.
     */
    public void moveLeft() {
        double upperX = paddle.getUpperLeft().getX();
        double upperY = paddle.getUpperLeft().getY();
        double width = paddle.getWidth();
        double height = paddle.getHeight();
        if (upperX == 20) {
            return;
        }
        paddle = new Rectangle(new gui.shapes.Point(upperX - 10, upperY), width, height);
    }

    /**
     * Move the paddle to the left unless it has reached the edge of the screen.
     */
    public void moveRight() {
        double upperX = paddle.getUpperLeft().getX();
        double upperY = paddle.getUpperLeft().getY();
        double width = paddle.getWidth();
        double height = paddle.getHeight();
        if (upperX + width == 780) {
            return;
        }
        paddle = new Rectangle(new gui.shapes.Point(upperX + 10, upperY), width, height);
    }

    // Sprite

    /**
     * move the Keyboard.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * draw and fill the Rectangle.
     *
     * @param d - the surface
     */
    public void drawOn(DrawSurface d) {
        double xUpperLeft = paddle.getUpperLeft().getX();
        double yUpperLeft = paddle.getUpperLeft().getY();
        double width = paddle.getWidth();
        double height = paddle.getHeight();
        d.setColor(Color.orange);
        d.fillRectangle((int) xUpperLeft, (int) yUpperLeft, (int) width, (int) height);
        d.setColor(Color.black);
        d.drawRectangle((int) xUpperLeft, (int) yUpperLeft, (int) width, (int) height);
    }

    // Collidable

    /**
     * get method.
     *
     * @return - Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * @param collisionPoint  - the collision point
     * @param currentVelocity - the velocity before the hit
     * @param hitter          - the ball
     * @return - Velocity
     */
    public Velocity hit(Ball hitter, gui.shapes.Point collisionPoint, Velocity currentVelocity) {
        //import the 4 ribs of rectangle
        Line right = paddle.getRightLine();
        Line left = paddle.getLeftline();
        Line upper = paddle.getUpperLine();
        Line bottom = paddle.getBottomLine();
        if ((right.isPointOnLine(collisionPoint, right) || left.isPointOnLine(collisionPoint, left))
                && (upper.isPointOnLine(collisionPoint, upper) || bottom.isPointOnLine(collisionPoint, bottom))) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (right.isPointOnLine(collisionPoint, right) || left.isPointOnLine(collisionPoint, left)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        double part = (paddle.getWidth()) / 5;
        Line[] parts = new Line[5];
        for (int i = 0; i < 5; i++) {
            double partUpX = paddle.getUpperLeft().getX() + (i * (part));
            double partUpY = paddle.getUpperLeft().getY();
            gui.shapes.Point partUpperLeft = new gui.shapes.Point(partUpX, partUpY);
            gui.shapes.Point partUpperRight = new Point(partUpX + part, partUpY);
            parts[i] = new Line(partUpperLeft, partUpperRight);
        }

        //Each part and its condition
        if (parts[0].isPointOnLine(collisionPoint, parts[0])) {
            return Velocity.fromAngleAndSpeed((300), currentVelocity.getVelocity());
        } else if (parts[1].isPointOnLine(collisionPoint, parts[1])) {
            return Velocity.fromAngleAndSpeed((330), currentVelocity.getVelocity());
        } else if (parts[2].isPointOnLine(collisionPoint, parts[2])) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (parts[3].isPointOnLine(collisionPoint, parts[3])) {
            return Velocity.fromAngleAndSpeed((30), currentVelocity.getVelocity());
        } else if (parts[4].isPointOnLine(collisionPoint, parts[4])) {
            return Velocity.fromAngleAndSpeed((60), currentVelocity.getVelocity());
        } else {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
