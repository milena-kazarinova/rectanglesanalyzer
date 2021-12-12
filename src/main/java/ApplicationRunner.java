import analyzators.Adjacency;
import analyzators.Containment;
import analyzators.Intersection;
import entities.Rectangle;
import factories.RectangleFactory;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangleA = RectangleFactory.createRectangle(2, 7, 5, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(5, 4, 3, 5);

        Adjacency.analyze(rectangleA, rectangleB);
        Containment.analyze(rectangleA, rectangleB);
        Intersection.analyze(rectangleA, rectangleB);
    }
}
