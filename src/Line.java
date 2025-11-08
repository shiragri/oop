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

    }

    // Returns the middle point of the line
    public Point middle() { }

    // Returns the start point of the line
    public Point start() { }

    // Returns the end point of the line
    public Point end() { }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) { }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) { }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) { }

}