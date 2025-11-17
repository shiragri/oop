import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple frames Bouncing Balls", 800, 800);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();

        Frame bigFrame = new Frame(new Point(50,50),450,450,Color.GRAY);
        Frame smallFrame = new Frame(new Point(450,450),150,150,Color.YELLOW);






        Ball[] balls = new Ball[args.length];

        System.out.println(args.length);

        for (int i = 0; i < args.length; i++) {
            int r = Integer.parseInt(args[i]);

            int x;
            int y;
            Ball ball;
            if (i<args.length/2) {
                x = rand.nextInt(bigFrame.getWidth() - 2 * r) +r+ (int) bigFrame.getTopLeft().getX();
                y = rand.nextInt(bigFrame.getHeight() - 2 * r) +r+ (int)bigFrame.getTopLeft().getY();
                ball = new Ball(x, y, r, randomColor());
                ball.setFrame(bigFrame);
            }
            else{
                x = rand.nextInt(smallFrame.getWidth() - 2 * r) +r+ (int)smallFrame.getTopLeft().getX();
                y = rand.nextInt(smallFrame.getHeight() - 2 * r) +r+ (int) smallFrame.getTopLeft().getY();
                ball = new Ball(x, y, r, randomColor());
                ball.setFrame(smallFrame);
            }

           // Ball ball = new Ball(x, y, r, randomColor());


            double speed = getSpeedBySize(r);
            double angle = rand.nextDouble(360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
          //  ball.setScreenSize(500,500);
           // double dx = speed * Math.cos(angle);
           // double dy = speed * Math.sin(angle);

          //  ball.setVelocity(dx, dy);
            balls[i] = ball;
        }


        while (true) {
            DrawSurface d = gui.getDrawSurface();
            bigFrame.drawOn(d);
            smallFrame.drawOn(d);
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
