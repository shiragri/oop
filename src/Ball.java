import biuoop.DrawSurface;
import java.awt.Color;

public class Ball {

    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private int screenWidth;
    private int screenHeight;
    private Frame frame;


    // constructor
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    // accessors
    public int getX() {
        return (int) center.getX();
    }

    public int getY() {
        return (int) center.getY();
    }

    public int getSize() {
        return this.r;
    }

    public Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    public void setVelocity(Velocity v){
        this.velocity=v;
    }
    public void setVelocity(double dx, double dy){
        this.velocity=new Velocity(dx,dy);
    }
    public Velocity getVelocity(){
        return this.velocity;
    }

    public void setScreenSize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }

    public void setFrame(Frame f){
        this.frame=f;
    }

    public Frame getFrame() {
        return this.frame;
    }

    public void moveOneStep() {
        if (this.velocity == null) {
            return;
        }

        int topLeftx = 0;
        int topLefty = 0;
        int width = screenWidth;
        int height = screenHeight;

        if (frame != null) {
            topLeftx = (int)frame.getTopLeft().getX();
            topLefty = (int)frame.getTopLeft().getY();
            width = frame.getWidth() + topLeftx;
            height = frame.getHeight() + topLefty;
        }


        Point next = this.velocity.applyToPoint(this.center);


        if (next.getX() - r < topLeftx || next.getX() + r > width) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }


        if (next.getY() - r < topLefty || next.getY() + r > height) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }

        this.center = this.getVelocity().applyToPoint(this.center);
    }


}