package analyzers;

import dto.RectangleFeature;
import entities.Rectangle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import util.RectangleFactory;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AdjacencyTest {

    private final Analyzer analyzer = new Adjacency();

    @Test
    public void givenEqualRectangles_shouldReturnNotAdjacent() {
        RectangleFeature expectedResult = Adjacency.AdjacencyRectangleFeature.NOT_ADJACENT;

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(6, 10, 4, 6);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);

        assertEquals(expectedResult, resultWithB);
    }

    @Test
    public void givenSubLineRectangles_shouldReturnAdjacent() {
        RectangleFeature expectedResult = Adjacency.AdjacencyRectangleFeature.SUB_LINE;

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(12, 9, 1, 1);
        Rectangle rectangleC = RectangleFactory.createRectangle(5, 9, 1, 1);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);
        RectangleFeature resultWithC = analyzer.analyze(rectangleA, rectangleC);

        assertEquals(expectedResult, resultWithB);
        assertEquals(expectedResult, resultWithC);
    }

    @Test
    public void givenProperRectangles_shouldReturnAdjacent() {
        RectangleFeature expectedResult = Adjacency.AdjacencyRectangleFeature.PROPER;

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(12, 10, 4, 1);
        Rectangle rectangleC = RectangleFactory.createRectangle(5, 10, 4, 1);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);
        RectangleFeature resultWithC = analyzer.analyze(rectangleA, rectangleC);

        assertEquals(expectedResult, resultWithB);
        assertEquals(expectedResult, resultWithC);
    }

    @Test
    public void givenPartialRectangles_shouldReturnAdjacent() {
        RectangleFeature expectedResult = Adjacency.AdjacencyRectangleFeature.PARTIAL;

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(12, 11, 4, 6);
        Rectangle rectangleC = RectangleFactory.createRectangle(5, 11, 4, 1);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);
        RectangleFeature resultWithC = analyzer.analyze(rectangleA, rectangleC);

        assertEquals(expectedResult, resultWithB);
        assertEquals(expectedResult, resultWithC);
    }
}