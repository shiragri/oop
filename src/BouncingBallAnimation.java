import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;



public class BouncingBallAnimation {

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title",500,500);

        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setScreenSize(500,500);
        Velocity v = Velocity.fromAngleAndSpeed(90, 2);
        ball.setVelocity(v);
       // ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(500);  // wait for 50 milliseconds.
        }
    }
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Please provide 4 integers: x y dx dy");
            return;
        }

        double x = Integer.parseInt(args[0]);
        double y = Integer.parseInt(args[1]);
        double dx = Integer.parseInt(args[2]);
        double dy = Integer.parseInt(args[3]);

        drawAnimation(new Point(x, y), dx, dy);

    }
}
