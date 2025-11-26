import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> sprites; // נשתמש ברשימה גנרית של Sprite

    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    // קריאה ל-timePassed() על כל ה-sprites.
    public void notifyAllTimePassed() {
        // עותק של הרשימה כדי למנוע שגיאות אם Sprite מסיר את עצמו במהלך הריצה
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    // קריאה ל-drawOn(d) על כל ה-sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}