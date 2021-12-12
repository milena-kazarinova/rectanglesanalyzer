package analyzators;

import entities.Edge;
import entities.Rectangle;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Intersection {

    //1.1 to 2.2 and 2.4
    //1.3 to 2.2 and 2.4

    //2.1 to 1.2 and 1.4
    //2.3 to 1.2 and 1.4

    public void analyze(Rectangle rectangleA, Rectangle rectangleB) {
        getIntersection(rectangleA.getEdgeA(), rectangleB.getEdgeB());
        getIntersection(rectangleA.getEdgeA(), rectangleB.getEdgeD());
        getIntersection(rectangleA.getEdgeC(), rectangleB.getEdgeB());
        getIntersection(rectangleA.getEdgeC(), rectangleB.getEdgeD());
        getIntersection(rectangleB.getEdgeA(), rectangleA.getEdgeB());
        getIntersection(rectangleB.getEdgeA(), rectangleA.getEdgeD());
        getIntersection(rectangleB.getEdgeC(), rectangleA.getEdgeB());
        getIntersection(rectangleB.getEdgeC(), rectangleA.getEdgeD());
    }

    private void getIntersection(Edge edgeHorizontal, Edge edgeVertical) {
        boolean isXInRange = isInRangeExclusive(edgeVertical.getPointA().getX(),
                edgeHorizontal.getPointA().getX(), edgeHorizontal.getPointB().getX());
        boolean isYInRange = isInRangeExclusive(edgeHorizontal.getPointA().getY(),
                edgeVertical.getPointB().getY(), edgeVertical.getPointA().getY());
        if (isXInRange && isYInRange) {
            System.out.println("{" + edgeVertical.getPointA().getX() + "," + edgeHorizontal.getPointA().getY() + "}");
            return;
        }
    }

    private boolean isInRangeExclusive(int point, int min, int max) {
        return min < point && max > point;
    }
}
