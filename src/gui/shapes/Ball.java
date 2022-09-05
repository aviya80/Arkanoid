package gui.shapes;

import gui.sprite.Sprite;
import gui.game.GameLevel;
import gui.game.Velocity;
import biuoop.DrawSurface;
import gui.collision.CollisionInfo;
import gui.game.FrameSize;
import gui.game.GameEnvironment;

import java.awt.Color;

/**
 * @author Aviya elgrably 209251891
 * Class of ball, initials radius, midpoint and color
 */
public class Ball implements Sprite {
    //fields
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private FrameSize size;
    private GameEnvironment gameEnvironment;
    private CollisionInfo collisionInfo;


    /**
     * It's a ball builder. It initializes its color, radius.
     *
     * @param center - is the center of the ball
     * @param r      - is the radius of the ball
     * @param color  - is tha color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.size = new FrameSize(new Point(0, 0), 200, 200);
    }

    /**
     * It's a ball builder. It initializes its color, radius, center and enviroment.
     *
     * @param center          -is the center of the ball
     * @param r               -is the radius of the ball
     * @param color           - is the color of the ball
     * @param gameEnvironment - the Environment of the game
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.size = new FrameSize(new Point(0, 0), 200, 200);
        this.gameEnvironment = gameEnvironment;
        this.collisionInfo = collisionInfo;
    }

    /**
     * This is another constructor, which gets the x,y values of the midpoint.
     *
     * @param x     - the x value of the center of ball
     * @param y     - the y value of the center of ball
     * @param r     - the radius of ball
     * @param color - the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
        this.velocity = new Velocity(0, 0);
        this.size = new FrameSize(new Point(0, 0), 200, 200);
        this.gameEnvironment = gameEnvironment;
        this.collisionInfo = collisionInfo;
    }

    /**
     * another constructor. now with gameEnvironment
     *
     * @param x               - the x of the center
     * @param y               -the y of the center
     * @param r               - the radius of the ball
     * @param color           - the color
     * @param gameEnvironment - the environment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this(new Point(x, y), r, color);
        this.velocity = new Velocity(0, 0);
        this.size = new FrameSize(new Point(0, 0), 200, 200);
        this.gameEnvironment = gameEnvironment;
        this.collisionInfo = collisionInfo;
    }

    /**
     * get method.
     *
     * @return GameEnvironment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * set method.
     *
     * @param environment setGameEnvironment
     */

    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * get method.
     *
     * @return radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @return point of the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return the x value of center
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * @return the y value of center
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * @return the radius of the ball
     */
    public int getSize() {
        return Math.round(this.radius);
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param surface - is the surface that we want to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.white);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @param v - the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx - The speed on the x-axis
     * @param dy - The speed on the y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The method creates for each ball the frame in which it can move.
     *
     * @param x      - The x value of the center of the ball
     * @param y      - The y value of the center of the ball
     * @param length - The length of the frame where the ball can move
     * @param width  - The width of the frame where the ball can move
     */
    public void setFrame(int x, int y, int length, int width) {
        this.size = new FrameSize(new Point(x, y), length, width);
    }

    /**
     * This method calculates the boundaries of the screen.
     * She thinks about the next step the ball is supposed to take.
     * If the next step comes out of the frame of the ball
     * - we will subtract or add the radius.
     * If the next step is a collision on one side -
     * vertical or horizontal - we will double the speed by one minus accordingly.
     */
    public void moveOneStep() {
        Point start = this.center;
        double signDx = velocity.getSignDx();
        double signDy = velocity.getSignDy();
        double dx = velocity.getDx();
        double dy = velocity.getDy();
        Point end = new Point(center.getX() + 2 * dx + signDx, center.getY() + 2 * dy + signDy);
        Line trajectory = new Line(start, end);
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        if ((info.collisionPoint() == null) && (info.collisionObject() == null)) {
            this.center = getVelocity().applyToPoint(this.center);
        } else {
            //there is a collision
            double xStartTra = trajectory.start().getX();
            double yStartTra = trajectory.start().getY();
            double xCollision = info.collisionPoint().getX();
            double yCollision = info.collisionPoint().getY();
            double xNewCenter = 0;
            double yNewCenter = 0;

//            Rectangle collisionRec = info.collisionObject().getCollisionRectangle();
//            if (collisionRec.getBottomLine().isPointOnLine(info.collisionPoint(), collisionRec.getBottomLine())) {
//                yNewCenter = yCollision + radius;
//                xNewCenter = this.center.getX();
//            }
//            if (collisionRec.getUpperLine().isPointOnLine(info.collisionPoint(), collisionRec.getUpperLine())) {
//                yNewCenter = yCollision - radius;
//                xNewCenter = this.center.getX();
//            }
//            if (collisionRec.getLeftline().isPointOnLine(info.collisionPoint(), collisionRec.getLeftline())) {
//                xNewCenter = xCollision - radius;
//                yNewCenter = this.center.getY();
//            } else {
//                xNewCenter = xCollision + radius;
//                yNewCenter = this.center.getY();
//            }
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), velocity);
            Point centerNew = this.velocity.applyToPoint(this.center);
            if (centerNew.getY() < 40) {
                centerNew.setY(40 + this.radius + 1);
            }
            if (centerNew.getY() > 580) {
                centerNew.setY(580 - this.radius - 1);
            }
            this.center = centerNew;
        }
    }


    /**
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
