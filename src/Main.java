import biuoop.GUI;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        drawAnimation();
    }

    static private void drawAnimation() {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            Ball ball = new Ball(rand.nextInt(200), rand.nextInt(200), 30, java.awt.Color.BLACK);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}

