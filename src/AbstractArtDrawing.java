import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {

    // Generate a random line within the window boundaries
    public Line generateRandomLine() {
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1;
        int y2 = rand.nextInt(300) + 1;
        return new Line(x1, y1, x2, y2);
    }

    // Draw a line on the DrawSurface
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                (int) l.end().getX(), (int) l.end().getY());
    }

    public void drawRandomLines() {
        Random rand = new Random();
        // Create a window with the title "Random Lines"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface d = gui.getDrawSurface();

        // Keep an array of the previously drawn lines
        Line[] lines = new Line[10];

        // Generate and draw 10 random lines in black
        for (int i = 0; i < 10; ++i) {
            lines[i] = generateRandomLine();
            drawLine(lines[i], d);
        }

        // Draw the middle points in blue
        for (int i = 0; i < 10; ++i) {
            Point middle = lines[i].middle();
            d.setColor(Color.BLUE);
            d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);
        }

        // Draw the intersection points in red
        for (int i = 0; i < 10; ++i) {
            for (int j = i + 1; j < 10; ++j) {
                Point intersection = lines[i].intersectionWith(lines[j]);
                if (intersection != null) {
                    d.setColor(Color.RED);
                    d.fillCircle((int) intersection.getX(), (int) intersection.getY(), 3);
                }
            }
        }

        gui.show(d);
    }

    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}
