import java.util.List;
import java.util.ArrayList;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;


    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // החזרת רשימה (אולי ריקה) של נקודות חיתוך עם הקו המצוין
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();

        // 1. נחלץ את הקואורדינטות כדי שיהיה נוח לעבוד
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();
        double w = this.width;
        double h = this.height;

        // 2. ניצור 4 קווים המייצגים את צלעות המלבן
        // שימי לב: השתמשנו בבנאי של Line שמקבל קואורדינטות (x1, y1, x2, y2)
        // כי זה כבר קיים אצלך במחלקה Line.

        Line top    = new Line(x, y, x + w, y);           // צלע עליונה
        Line bottom = new Line(x, y + h, x + w, y + h);   // צלע תחתונה
        Line left   = new Line(x, y, x, y + h);           // צלע שמאלית
        Line right  = new Line(x + w, y, x + w, y + h);   // צלע ימנית

        // 3. נבדוק חיתוך מול כל צלע. אם יש חיתוך (לא null), נוסיף לרשימה.
        Point p1 = line.intersectionWith(top);
        if (p1 != null) {
            intersections.add(p1);
        }

        Point p2 = line.intersectionWith(bottom);
        if (p2 != null) {
            intersections.add(p2);
        }

        Point p3 = line.intersectionWith(left);
        if (p3 != null) {
            intersections.add(p3);
        }

        Point p4 = line.intersectionWith(right);
        if (p4 != null) {
            intersections.add(p4);
        }

        return intersections;
    }

    // Getters

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
