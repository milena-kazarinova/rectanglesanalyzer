package analyzators;

import entities.Rectangle;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Containment {

    public void analyze(Rectangle rectangleA, Rectangle rectangleB) {

        if(isContainment(rectangleA, rectangleB) || isContainment(rectangleB, rectangleA)){
            System.out.println("Containment");
            return;
        }
        System.out.println("No containment");
    }

    private boolean isContainment(Rectangle rectangleA, Rectangle rectangleB) {
        return rectangleA.getEdgeA().getPointA().getX() < rectangleB.getEdgeA().getPointA().getX() &&
                rectangleA.getEdgeA().getPointA().getY() > rectangleB.getEdgeA().getPointA().getY() &&
                rectangleA.getEdgeB().getPointB().getX() > rectangleB.getEdgeB().getPointB().getX() &&
                rectangleA.getEdgeB().getPointB().getY() < rectangleB.getEdgeB().getPointB().getX();
    }
}
