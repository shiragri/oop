import biuoop.GUI;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // 1. יוצרים מופע חדש של המשחק
        Game game = new Game();

        // 2. מאתחלים את המשחק (יוצר GUI, בלוקים, כדור ומחבר את הסביבה)
        game.initialize();

        // 3. מריצים את לולאת האנימציה החדשה (שמשתמשת ב-SpriteCollection)
        game.run();
    }
}