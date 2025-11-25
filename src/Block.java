public class Block implements Collidable{
    private Rectangle rect;

    //constructor
    public Block(Rectangle rect){
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
}


