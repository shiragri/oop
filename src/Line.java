import java.util.List;

public class Line {

    private Point start;
    private Point end;
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line
    public double length() {
        return start.distance(end);
    }

    // Returns the middle point of the line
    public Point middle() {
        return new Point((start.getX() + end.getX())/2, (start.getY() + end.getY())/2);
    }

    // Returns the start point of the line
    public Point start() {
        return start;
    }

    // Returns the end point of the line
    public Point end() {
        return end;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }


    public Point intersectionWith(Line other) {

        double x1 = this.start.getX(), y1 = this.start.getY();
        double x2 = this.end.getX(), y2 = this.end.getY();
        double x3 = other.start.getX(), y3 = other.start.getY();
        double x4 = other.end.getX(), y4 = other.end.getY();

        // <--- קוד חדש: בדיקת מקרים אנכיים (התיקון שמונע שגיאת חלוקה באפס)

        // נשתמש בפונקציית העזר areDoublesEqual כדי לבדוק שוויון X,Y
        boolean thisIsVertical = areDoublesEqual(x1, x2);
        boolean otherIsVertical = areDoublesEqual(x3, x4);

        if (thisIsVertical && otherIsVertical) {
            return null; // קווים אנכיים מקבילים או חופפים - אין חיתוך יחיד
        }

        if (thisIsVertical) {
            // הקו שלנו אנכי, והשני לא (אין סכנת חלוקה באפס)
            double m2 = (y3 - y4) / (x3 - x4);
            double b2 = y3 - m2 * x3;
            double x5 = x1;
            double y5 = m2 * x5 + b2;

            Point intersection = new Point(x5, y5);

            if (isPointOnSegment(intersection, this) && isPointOnSegment(intersection, other)) {
                return intersection;
            }
            return null;
        }

        if (otherIsVertical) {
            // הקו השני אנכי, ושלנו לא
            double m1 = (y1 - y2) / (x1 - x2);
            double b1 = y1 - m1 * x1;
            double x5 = x3;
            double y5 = m1 * x5 + b1;

            Point intersection = new Point(x5, y5);

            if (isPointOnSegment(intersection, this) && isPointOnSegment(intersection, other)) {
                return intersection;
            }
            return null;
        }
        // <--- סוף הלוגיקה לטיפול במקרים אנכיים

        // ---------------------------------------------
        // קוד קיים: אף קו לא אנכי - חישוב רגיל
        // ---------------------------------------------

        // חישוב שיפועים (עכשיו בטוח לבצע)
        double m1 = (y1 - y2) / (x1 - x2);
        double m2 = (y3 - y4) / (x3 - x4);

        // אם השיפועים שווים (מקבילים)
        if (areDoublesEqual(m1, m2)) { // <--- שימוש בבדיקת epsilon (חדש)
            return null;
        }

        double b1 = y1 - m1 * x1;
        double b2 = y3 - m2 * x3;

        double x5 = (b2 - b1) / (m1 - m2);
        double y5 = m1 * x5 + b1;

        Point intersection = new Point(x5, y5);

        // <--- קוד חדש: שימוש בפונקציית העזר החזקה לבדיקת טווח
        if (isPointOnSegment(intersection, this) && isPointOnSegment(intersection, other)) {
            return intersection;
        }
        return null;
    }

// ---------------------------------------------
// <--- פונקציות עזר חדשות שיש להוסיף למחלקה Line (מוסיפים מחוץ ל-intersectionWith)
// ---------------------------------------------

    /** משווה שני doubles עם סף קטן (epsilon). */
    private boolean areDoublesEqual(double a, double b) { // <--- פונקציה חדשה
        return Math.abs(a - b) < 0.00001;
    }

    /** בודק האם נקודה נמצאת בטווח הקואורדינטות של קטע נתון. */
    private boolean isPointOnSegment(Point p, Line line) { // <--- פונקציה חדשה
        double x1 = line.start().getX();
        double x2 = line.end().getX();
        double y1 = line.start().getY();
        double y2 = line.end().getY();

        // בודק שהנקודה p נמצאת בתוך טווח ה-X וה-Y של הקו. משתמש ב-epsilon.
        boolean inXRange = (p.getX() >= Math.min(x1, x2) || areDoublesEqual(p.getX(), Math.min(x1, x2))) &&
                (p.getX() <= Math.max(x1, x2) || areDoublesEqual(p.getX(), Math.max(x1, x2)));

        boolean inYRange = (p.getY() >= Math.min(y1, y2) || areDoublesEqual(p.getY(), Math.min(y1, y2))) &&
                (p.getY() <= Math.max(y1, y2) || areDoublesEqual(p.getY(), Math.max(y1, y2)));

        return inXRange && inYRange;
    }
//    // Returns the intersection point if the lines intersect,
//    // and null otherwise.
//    public Point intersectionWith(Line other) {
//
//
//        double x1 = this.start.getX(), y1 = this.start.getY();
//        double x2 = this.end.getX(), y2 = this.end.getY();
//
//        double x3 = other.start.getX(), y3 = other.start.getY();
//        double x4 = other.end.getX(), y4 = other.end.getY();
//
//
//        // finding the equations of lines
//        double m1= (y1-y2)/(x1-x2);
//        double m2= (y3-y4)/(x3-x4);
//
//        //if the lines are parallel
//        if(m1==m2) {
//            return null;
//        }
//
//        double b1 = y1 - m1 * x1;
//        double b2 = y3 - m2 * x3;
//
//        double x5 = (b2 - b1) / (m1 - m2);
//        double y5 = m1 * x5 + b1;
//
//
//        boolean onThisLine = x5 >= Math.min(x1, x2) && x5 <= Math.max(x1, x2) &&
//                y5 >= Math.min(y1, y2) && y5 <= Math.max(y1, y2);
//
//        boolean onOtherLine = x5 >= Math.min(x3, x4) && x5 <= Math.max(x3, x4) &&
//                y5 >= Math.min(y3, y4) && y5 <= Math.max(y3, y4);
//
//
//        if (onThisLine && onOtherLine) {
//            return new Point(x5, y5);
//        }
//        else {
//            return null;
//        }
//
//
//    }

    // equals return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (other == null) return false;

        return (this.start.equals(other.start) && this.end.equals(other.end)) ||
                (this.start.equals(other.end) && this.end.equals(other.start));
    }


    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect){
        List<Point> intersections = rect.intersectionPoints(this);

        if (intersections.isEmpty()) {
            return null;
        }

        // נניח שהנקודה הראשונה היא הכי קרובה, ואז נבדוק את השאר
        Point closest = intersections.get(0);
        double minDistance = this.start.distance(closest);

        for (Point p : intersections) {
            double currentDistance = this.start.distance(p);
            if (currentDistance < minDistance) {
                closest = p;
                minDistance = currentDistance;
            }
        }

        return closest;
    }

}