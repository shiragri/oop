public class Point {

    private double x;
    private double y;

    // constructor
    public Point(double x, double y) {
        this.x=x;
        this.y=y;
    }

    // distance return the distance of this point to the other point
    public double distance(Point other) {
        double distanceX= this.x-other.x;
        double distanceY= this.y-other.y;
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    }

    // equals return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if (other==null) {
            return false;
        }
        return this.x==other.x && this.y==other.y;
    }

    // Return the x and y values of this point
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }


}
