package game;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.CountdownAnimation;
import animation.Animation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import collidable.Ball;
import collidable.Block;
import collidable.Collidable;
import collidable.Paddle;
import geometry.Point;
import geometry.Rectangle;
import level.LevelInformation;
import listener.BallRemover;
import listener.BlockRemover;
import listener.ScoreTrackingListener;
import sprite.ScoreIndicator;
import sprite.Sprite;
import sprite.SpriteCollection;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Amit Cohen, ID=208428714
 * The class game.Game represents a game.
 */
public class GameLevel implements Animation {
    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 600;
    public static final int HORIZONTAL_HEIGHT = 50;
    public static final int VERTICAL_WIDTH = 25;
    public static final int VERTICAL_HEIGHT = 575;
    public static final int START_POINT = 0;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Counter blocksCounter;
    private final Counter ballsCounter;
    private final Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * This function creates a new list and a new game environment for the game.
     * @param lvl =the level that runs.
     * @param score - the score of the player.
     * @param gui - the GUI that runs.
     */
    public GameLevel(LevelInformation lvl, Counter score, GUI gui) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.gui = gui;
        this.runner = new AnimationRunner(this.gui);
        this.keyboard = this.gui.getKeyboardSensor();
        this.level = lvl;
    }

    /**
     * this function adds a collidable to the game environment list.
     *
     * @param c - the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * this function adds a sprite to the game environment list.
     *
     * @param s - the sprite we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This function returns the score counter.
     *
     * @return - the score counter.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * this is a getter function.
     * @return - the level that runs.
     */
    public LevelInformation getLevel() {
        return this.level;
    }

    /**
     * this is a getter function.
     * @return - the balls counter.
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * this is a getter function.
     * @return - the blocks counter.
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }

    /**
     * this function creates the borders of the game.
     *
     * @param br - the listener that we will add to bot block, in order the remove ball when he hits bottom.
     */
    public void createBorders(BallRemover br) {
        Block top = new Block(new Rectangle(new Point(START_POINT, START_POINT),
                MAX_WIDTH, HORIZONTAL_HEIGHT), Color.DARK_GRAY);
        Block bot = new Block(new Rectangle(new Point(START_POINT, MAX_HEIGHT - VERTICAL_WIDTH),
                MAX_WIDTH, HORIZONTAL_HEIGHT), Color.DARK_GRAY);
        Block left = new Block(new Rectangle(new Point(START_POINT, HORIZONTAL_HEIGHT),
                VERTICAL_WIDTH, VERTICAL_HEIGHT), Color.DARK_GRAY);
        Block right = new Block(new Rectangle(new Point(MAX_WIDTH - VERTICAL_WIDTH, HORIZONTAL_HEIGHT),
                VERTICAL_WIDTH, VERTICAL_HEIGHT), Color.DARK_GRAY);
        top.addToGame(this);
        left.addToGame(this);
        bot.addToGame(this);
        right.addToGame(this);
        bot.addHitListener(br);
    }

    /**
     * this function creates the paddle of the game.
     *
     * @return - the paddle that the function created.
     */
    public Paddle createPaddle() {
        Paddle p = this.level.createPaddle(this.gui);
        p.addToGame(this);
        return p;
    }

    /**
     * this function creates the balls of the game.
     *
     * @param p - the paddle of the game, that we will add the balls to.
     */
    public void createBalls(Paddle p) {
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball b = new Ball(new Point(400, 550), 5, Color.orange, this.environment);
            b.addToGame(this);
            b.setVelocity(this.level.initialBallVelocities().get(i));
            p.addBall(b);
        }
        this.ballsCounter.increase(this.level.numberOfBalls());
    }

    /**
     * this function creates the blocks of the game.
     *
     * @param br  - the listener that removes block, we will add it to every block.
     * @param stl - the listener that creates the score.
     */
    public void createBlocks(BlockRemover br, ScoreTrackingListener stl) {
        for (int i = 0; i < this.level.blocks().size(); i++) {
            Block b = this.level.blocks().get(i);
            b.addToGame(this);
            b.addHitListener(br);
            b.addHitListener(stl);
            this.blocksCounter.increase(1);
        }
    }

    /**
     * This function creates the block score at the top of the screen.
     */
    public void createScoreBlock() {
        ScoreIndicator block = new ScoreIndicator(new Rectangle(new Point(START_POINT, START_POINT),
                MAX_WIDTH, HORIZONTAL_HEIGHT / 2), Color.GRAY, this);
        this.addSprite(block);
    }

    /**
     * this function initialize all the elements for the game.
     */
    public void initialize() {
        BlockRemover br = new BlockRemover(this, this.blocksCounter);
        BallRemover ballr = new BallRemover(this, this.ballsCounter);
        ScoreTrackingListener scorelistener = new ScoreTrackingListener(this.score);
        Paddle p = createPaddle();
        createBorders(ballr);
        createBalls(p);
        createBlocks(br, scorelistener);
        createScoreBlock();
    }

    /**
     * this function runs the game.
     */
    public void run() {
        this.running = true;
        //countdown before game starts
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.level));
        this.runner.run(this);
    }

    /**
     * This function removes collidable from the game.
     *
     * @param c - the collidable that we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getList().remove(c);
    }

    /**
     * This function removes sprite from the game.
     *
     * @param s - the sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getList().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.level.drawBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //if player hitted all blocks
        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        //if player lost all balls
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            PauseScreen ps = new PauseScreen(this.keyboard);
            Animation kps = new KeyPressStoppableAnimation(this.keyboard, "space", ps);
            this.runner.run(kps);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
