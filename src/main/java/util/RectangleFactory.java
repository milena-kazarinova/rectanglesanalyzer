package factories;

import entities.Edge;
import entities.Point;
import entities.Rectangle;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RectangleFactory {

    public Rectangle createRectangle(int x, int y, int height, int width) {
        Edge edgeA = getEdgeA(x, y, width);
        Edge edgeB = getEdgeB(x, y, height, width);
        Edge edgeC = getEdgeC(x, y, height, width);
        Edge edgeD = getEdgeD(x, y, height);

        return new Rectangle(edgeA, edgeB, edgeC, edgeD);
    }

    private Edge getEdgeA(int x, int y, int width) {
        Point pointA = new Point(x, y);
        Point pointB = new Point(x + width, y);

        return new Edge(pointA, pointB);
    }

    private Edge getEdgeB(int x, int y, int height, int width) {
        Point pointA = new Point(x + width, y);
        Point pointB = new Point(x + width, y - height);

        return new Edge(pointA, pointB);
    }

    private Edge getEdgeC(int x, int y, int height, int width) {
        Point pointA = new Point(x, y - height);
        Point pointB = new Point(x + width, y - height);

        return new Edge(pointA, pointB);
    }

    private Edge getEdgeD(int x, int y, int height) {
        Point pointA = new Point(x, y);
        Point pointB = new Point(x, y - height);

        return new Edge(pointA, pointB);
    }
}
