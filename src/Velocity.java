// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        double angleInRadians = Math.toRadians(angle);


        double dx = speed * Math.sin(angleInRadians);


        double dy = -speed * Math.cos(angleInRadians);
        return new Velocity(dx, dy);
    }


    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }
    public double getDy() {
        return dy;
    }
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        return new Point(p.getX()+dx, p.getY()+dy);
    }
}


