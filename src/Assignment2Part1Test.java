import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

public class Assignment2Part1Test {

    public static void main(String[] args) {
        // 1. יצירת החלון (GUI)
        GUI gui = new GUI("Assignment 2 - Part 1 Test", 800, 600);
        Sleeper sleeper = new Sleeper();

        // 2. יצירת סביבת המשחק (GameEnvironment)
        GameEnvironment environment = new GameEnvironment();

        // 3. יצירת הבלוקים (Blocks)
        // שמים בלוקים בקצוות כדי שהכדור לא "ייפול" מהמסך
        // (שים לב: Rectangle מקבל נקודה שמאלית עליונה, רוחב, גובה)

        Block topWall = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        Block bottomWall = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY);
        Block leftWall = new Block(new Rectangle(new Point(0, 20), 20, 560), Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(780, 20), 20, 560), Color.GRAY);

        // בלוק נוסף באמצע לבדיקה
        Block middleBlock = new Block(new Rectangle(new Point(350, 250), 100, 30), Color.BLUE);

        // 4. הוספת הבלוקים לסביבה
        environment.addCollidable(topWall);
        environment.addCollidable(bottomWall);
        environment.addCollidable(leftWall);
        environment.addCollidable(rightWall);
        environment.addCollidable(middleBlock);

        // 5. יצירת הכדור (Ball)
        // מתחילים אותו במיקום בטוח (לא בתוך קיר)
        Ball ball = new Ball(100, 100, 10, Color.RED);
        ball.setVelocity(5, 5); // מהירות

        // *** החלק הכי חשוב: הכדור חייב להכיר את הסביבה! ***
        ball.setGameEnvironment(environment);

        // 6. לולאת האנימציה (Animation Loop)
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // ציור הרקע (סתם שיהיה לבן/נקי)
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, 800, 600);

            // ציור הבלוקים (חייבים לצייר אותם ידנית בלולאה הזו כרגע)
            topWall.drawOn(d);
            bottomWall.drawOn(d);
            leftWall.drawOn(d);
            rightWall.drawOn(d);
            middleBlock.drawOn(d);

            // תזוזה וציור הכדור
            ball.moveOneStep();
            ball.drawOn(d);

            gui.show(d);
            sleeper.sleepFor(20); // המתנה קצרה לאנימציה חלקה (50 FPS בערך)
        }
    }
}