import analyzers.Adjacency;
import analyzers.Analyzer;
import analyzers.Containment;
import analyzers.Intersection;
import entities.Rectangle;
import util.RectangleFactory;

public class ApplicationRunner {
    public static void main(String[] args) {

        Analyzer adjacency = new Adjacency();
        Analyzer containment = new Containment();
        Analyzer intersection = new Intersection();

        Rectangle rectangleA = RectangleFactory.createRectangle(2, 7, 5, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(5, 4, 3, 5);

        System.out.println(adjacency.analyze(rectangleA, rectangleB));
        System.out.println(containment.analyze(rectangleA, rectangleB));
        System.out.println(intersection.analyze(rectangleA, rectangleB));
    }
}
