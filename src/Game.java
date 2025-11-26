import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
// נניח ש-Block, Ball, Point, Rectangle קיימים

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    // בנאי
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        // הגדרת GUI כאן או ב-initialize
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sleeper = new Sleeper();
    }

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    public void initialize() {

        // הגדרת GUI (כדי שיהיה נגיש ל-run)
        // GUI gui = new GUI("Arkanoid", 800, 600); // אם לא הגדרת בבנאי

        // 1. יצירת הבלוקים (קירות גבול)
        Block topWall = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        Block bottomWall = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY);
        Block leftWall = new Block(new Rectangle(new Point(0, 20), 20, 560), Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(780, 20), 20, 560), Color.GRAY);

        // 2. יצירת בלוק משחק מרכזי
        Block centerBlock = new Block(new Rectangle(new Point(350, 250), 100, 30), Color.BLUE);

        // 3. יצירת כדור
        Ball gameBall = new Ball(400, 500, 10, Color.RED);
        gameBall.setVelocity(5, 5);

        // 4. הוספה למשחק באמצעות addToGame (הפונקציה יודעת מה להוסיף!)
        topWall.addToGame(this);
        bottomWall.addToGame(this);
        leftWall.addToGame(this);
        rightWall.addToGame(this);
        centerBlock.addToGame(this);

        gameBall.addToGame(this); // הכדור מתחבר אוטומטית ל-GameEnvironment
    }

    // הרצת המשחק -- לולאת האנימציה החדשה
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis(); // // timing - זמן התחלה

            DrawSurface d = gui.getDrawSurface();

            // 1. ציור כל ה-Sprites
            this.sprites.drawAllOn(d);
            gui.show(d);

            // 2. קריאה ל-timePassed לכל ה-Sprites (הכדור זז)
            this.sprites.notifyAllTimePassed();

            // timing - חישוב זמן השינה
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    public GameEnvironment getEnvironment() {
        return this.environment;
    }
}