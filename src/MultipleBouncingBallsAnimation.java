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


        Ball[] balls = new Ball[args.length];

        System.out.println(args.length);

        for (int i = 0; i < args.length; i++) {
            int r = Integer.parseInt(args[i]);
            int x = rand.nextInt(screenWidth - 2 * r) + r;
            int y = rand.nextInt(screenHeight - 2 * r) + r;

            Ball ball = new Ball(x, y, r, randomColor());


            double speed = getSpeedBySize(r);
            double angle = rand.nextDouble(360);// * 2 * Math.PI;
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            System.out.println("speed:"+speed);
            System.out.println("angle:"+angle);
            System.out.println("dx:"+v.getDx());
            System.out.println("dy:"+v.getDy());
            ball.setVelocity(v);
            ball.setScreenSize(500,500);
           // double dx = speed * Math.cos(angle);
           // double dy = speed * Math.sin(angle);

          //  ball.setVelocity(dx, dy);
            balls[i] = ball;
        }


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


    private static double getSpeedBySize(int size) {
        if (size >= 50) {
            return 2;
        } else {
            return Math.max(2, 50.0 / size);
        }
    }


    private static Color randomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}
