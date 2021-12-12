package analyzers;

import dto.RectangleFeature;
import entities.Rectangle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import util.RectangleFactory;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IntersectionTest {

    private final Analyzer analyzer = new Intersection();

    @Test
    public void givenNotIntersectingRectangles_shouldReturnNoIntersection() {
        RectangleFeature expectedResultWithB = new Intersection.IntersectionRectangleFeature(Boolean.FALSE, Intersection.NO_INTERSECTION);

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(11, 10, 4, 6);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);

        assertEquals(expectedResultWithB, resultWithB);
    }

    @Test
    public void givenContainmentRectangles_shouldReturnNoIntersection() {
        RectangleFeature expectedResultWithB = new Intersection.IntersectionRectangleFeature(Boolean.FALSE, Intersection.NO_INTERSECTION);

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(7, 9, 3, 5);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);

        assertEquals(expectedResultWithB, resultWithB);
    }

    @Test
    public void givenEqualRectangles_shouldReturnEqualIntersection() {
        RectangleFeature expectedResultWithB = new Intersection.IntersectionRectangleFeature(Boolean.TRUE, Intersection.EQUAL);

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(6, 10, 4, 6);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);

        assertEquals(expectedResultWithB, resultWithB);
    }

    @Test
    public void givenFourPointsIntersectingRectangles_shouldReturnIntersection() {
        RectangleFeature expectedResultWithB = new Intersection.IntersectionRectangleFeature(Boolean.TRUE, "(11,10), (7,10), (11,6), (7,6)");
        RectangleFeature expectedResultWithC = new Intersection.IntersectionRectangleFeature(Boolean.TRUE, "(12,9), (6,9), (12,7), (6,7)");

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(7, 11, 6, 4);
        Rectangle rectangleC = RectangleFactory.createRectangle(5, 9, 2, 8);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);
        RectangleFeature resultWithC = analyzer.analyze(rectangleA, rectangleC);

        assertEquals(expectedResultWithB, resultWithB);
        assertEquals(expectedResultWithC, resultWithC);
    }

    @Test
    public void givenTwoPointsIntersectingRectangles_shouldReturnIntersection() {
        RectangleFeature expectedResultWithB = new Intersection.IntersectionRectangleFeature(Boolean.TRUE, "(11,10), (7,10)");
        RectangleFeature expectedResultWithC = new Intersection.IntersectionRectangleFeature(Boolean.TRUE, "(6,9), (6,7)");

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(7, 11, 5, 4);
        Rectangle rectangleC = RectangleFactory.createRectangle(5, 9, 2, 6);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);
        RectangleFeature resultWithC = analyzer.analyze(rectangleA, rectangleC);

        assertEquals(expectedResultWithB, resultWithB);
        assertEquals(expectedResultWithC, resultWithC);
    }
}