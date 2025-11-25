
import java.util.List;
import java.util.ArrayList;

public class GameEnvironment {


    private List<Collidable> collidables;

    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c){
        this.collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory){

        Point closestPoint = null;
        Collidable closestObject = null;

        double minDistance = Double.MAX_VALUE;

        for(Collidable c : this.collidables){
            Rectangle rect = c.getCollisionRectangle();

            // checking whether the ball's trajectory intersects the given rectangle
            Point intersection = trajectory.closestIntersectionToStartOfLine(rect);

            if(intersection != null){
                double distance = trajectory.start().distance(intersection);
                // if this collision is the closest we will update
                if(distance < minDistance){
                    minDistance = distance;
                    closestPoint = intersection;
                    closestObject = c;
                }
            }
        }

        // if we didn't find
        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestObject);
    }

}


