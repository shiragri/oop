import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

public class MultipleBouncingBallsAnimation {
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple Bouncing Balls", 500, 500);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();

        int screenWidth = 500;
        int screenHeight = 500;

        // ניצור מערך של כדורים לפי כמות המספרים בקלט
        Ball[] balls = new Ball[args.length];

        // ניצור כל כדור לפי הגודל שהוזן
        for (int i = 0; i < args.length; i++) {
            int r = Integer.parseInt(args[i]); // גודל הכדור
            int x = rand.nextInt(screenWidth - 2 * r) + r;
            int y = rand.nextInt(screenHeight - 2 * r) + r;

            Ball ball = new Ball(x, y, r, randomColor());

            // נגדיר מהירות - ככל שהכדור גדול יותר, הוא איטי יותר
            double speed = getSpeedBySize(r);
            double angle = rand.nextDouble() * 2 * Math.PI;
            double dx = speed * Math.cos(angle);
            double dy = speed * Math.sin(angle);

            ball.setVelocity(dx, dy);
            balls[i] = ball;
        }

        // לולאת האנימציה
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    // פונקציה למהירות לפי גודל
    private static double getSpeedBySize(int size) {
        if (size >= 50) {
            return 2;
        } else {
            return Math.max(2, 50.0 / size);
        }
    }

    // פונקציה לצבע רנדומלי
    private static Color randomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}
