import biuoop.DrawSurface; // כדי שהמחשב יכיר את DrawSurface, fillRectangle, וכו'.
import java.awt.Color;    // כדי שהמחשב יכיר את המחלקה Color (צבע).

public class Block implements Collidable,Sprite{
    private Rectangle rect;
    private java.awt.Color color;

    //constructor
    public Block(Rectangle rect, java.awt.Color color) {
        this.color = color;
        this.rect = rect;
    }

    //collidable
    @Override
    public Rectangle getCollisionRectangle(){
        return this.rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity){

        // קודם כל, ניקח את המהירות הנוכחית כדי שנוכל לשנות אותה
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        //the point of collision
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        // block boundaries
        double minX = this.rect.getUpperLeft().getX();
        double maxX = this.rect.getUpperLeft().getX() + this.rect.getWidth();
        double minY = this.rect.getUpperLeft().getY();
        double maxY = this.rect.getUpperLeft().getY() + this.rect.getHeight();

        double epsilon = 0.0001;
        // checking if the collision is on one side
        if (Math.abs(x - minX) < epsilon ||
                Math.abs(x - maxX) < epsilon) {

            newDx = -newDx; //reverse the horizontal velocity
        }

        //checking if the collision is on a top or bottom wall
        if (Math.abs(y - minY) < epsilon ||
                Math.abs(y - maxY) < epsilon) {

            newDy = -newDy; // reverse the vertical velocity
        }

        return new Velocity(newDx, newDy);
    }


    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());

        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
    }

    @Override
    public void timePassed() {
        // הבלוק אינו מונפש כרגע
    }

    public void addToGame(Game g) {
        g.addCollidable(this); // כבלוק, אני צריך להיכנס לסביבת ההתנגשות
        g.addSprite(this);     // כספרייט, אני צריך להיכנס לאוסף הציור
    }
}


