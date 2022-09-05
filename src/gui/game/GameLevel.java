package gui.game;

import biuoop.DrawSurface;
import biuoop.GUI;
import gui.collision.Collidable;
import gui.levels.LevelInformation;
import gui.sprite.Sprite;
import gui.sprite.SpriteCollection;
import gui.shapes.Ball;
import gui.shapes.Block;
import gui.shapes.Paddle;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import gui.levels.LevelName;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aviya elgrably 209251891
 * A class called - Game. Builds the environment of the game,
 * with Collidable and sprites.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private Counter counterBlocks;
    private final Counter counterBalls;
    private final Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private final GUI gui;
    private final LevelInformation levelInformation;
    private final ScoreIndicator scoreIndicator;
    private final ScoreTrackingListener scoreTrackingListener;


    /**
     * @param gui              - the gui.
     * @param animationRunner  - the animation.
     * @param scoreIndicator   - the score.
     * @param levelInformation - the information for each level.
     */
    public GameLevel(LevelInformation levelInformation, GUI gui,
                     AnimationRunner animationRunner, ScoreIndicator scoreIndicator) {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        this.counterBlocks = new Counter(0);
        this.counterBalls = new Counter(0);
        this.gui = gui;
        this.runner = animationRunner;
        this.levelInformation = levelInformation;
        this.scoreIndicator = scoreIndicator;
        this.score = new Counter(0);
        this.scoreTrackingListener = new ScoreTrackingListener(scoreIndicator.getScoreIndicator());
    }

    /**
     * add the Collidable to the enviroment.
     *
     * @param c - the Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add the sprite to the enviroment.
     *
     * @param s - the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //create background
        List<Sprite> backGroundList = new ArrayList<>();
        backGroundList.add(levelInformation.getBackground());
        for (Sprite i : backGroundList) {
            addSprite(i);
        }

        //create paddle
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), new Rectangle(new Point(350, 580),
                levelInformation.paddleWidth(), 20));

        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        BallRemover ballRemover = new BallRemover(this, counterBalls);

        // create level name
        LevelName levelName = new LevelName(levelInformation.levelName());

        // create sides blocks
        Block back = (Block) levelInformation.getBackground();
        Block blockScore = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.white);
        Block up = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.gray);
        Block down = new Block(new Rectangle(new Point(0, 599), 800, 30), Color.gray);
        down.addHitListener(ballRemover);
        Block left = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.gray);
        Block right = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.gray);
        back.addToGame(this);
        blockScore.addToGame(this);
        up.addToGame(this);
        down.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);


        // create block for each level
        List<Block> blocks = levelInformation.blocks();
        counterBlocks.increase(levelInformation.numberOfBlocksToRemove());
        for (Block i : blocks) {
            i.addToGame(this);
            i.addHitListener(blockRemover);
            i.addHitListener(scoreTrackingListener);
        }

        //create balls
        Ball[] ballsArray = new Ball[levelInformation.numberOfBalls()];
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Velocity velocityBall = levelInformation.initialBallVelocities().get(i);
            ballsArray[i] = new Ball(new Point(400, 570), 6, Color.white, environment);
            ballsArray[i].drawOn(gui.getDrawSurface());
            ballsArray[i].setVelocity(velocityBall);
            ballsArray[i].setFrame(0, 0, 800, 600);
            ballsArray[i].addToGame(this);
            ballsArray[i].setGameEnvironment(environment);
            counterBalls.increase(1);
        }

        scoreIndicator.addToGame(this);
        levelName.addToGame(this);
        paddle.addToGame(this);
        paddle.drawOn(gui.getDrawSurface());
        sprites.drawAllOn(gui.getDrawSurface());

    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }

    /**
     * @param c - the Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidableList().remove(c);
    }

    /**
     * @param s - the Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * @param c - the counter.
     */
    public void setCounter(Counter c) {
        this.counterBlocks = c;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (noBalls()) {
            this.running = false;
            runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "Space",
                    new GameOver(gui.getKeyboardSensor(), scoreIndicator), true, gui));
            gui.close();
            return;
        }

        if (this.gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(new PauseScreen(this.gui.getKeyboardSensor()));
        }

        if (noBlocks()) {
            scoreIndicator.increaseScore(score.getValue() + 100);
            scoreTrackingListener.setCurrentScore(scoreIndicator.getScoreIndicator());
            this.running = false;
            return;
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return - true / false - if there is balls in the game.
     */
    public boolean noBalls() {
        return this.counterBalls.getValue() == 0;
    }

    /**
     * @return - true / false - if there is blocks in the game.
     */
    public boolean noBlocks() {
        return this.counterBlocks.getValue() == 0;
    }

}
