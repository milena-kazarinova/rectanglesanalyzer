package analyzers;

import dto.RectangleFeature;
import entities.Rectangle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import util.RectangleFactory;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ContainmentTest {

    private final Analyzer analyzer = new Containment();

    @Test
    public void givenEqualRectangles_shouldReturnNoContainment() {
        RectangleFeature expectedResult = Containment.ContainmentRectangleFeature.NO_CONTAINMENT;

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(6, 10, 4, 6);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);

        assertEquals(expectedResult, resultWithB);
    }

    @Test
    public void givenNoContainmentRectangles_shouldReturnNoContainment() {
        RectangleFeature expectedResult = Containment.ContainmentRectangleFeature.NO_CONTAINMENT;

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(5, 10, 4, 1);

        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);

        assertEquals(expectedResult, resultWithB);
    }

    @Test
    public void givenContainmentRectangles_shouldReturnContainment() {
        RectangleFeature expectedResult = Containment.ContainmentRectangleFeature.CONTAINMENT;

        Rectangle rectangleA = RectangleFactory.createRectangle(6, 10, 4, 6);
        Rectangle rectangleB = RectangleFactory.createRectangle(7, 9, 2, 4);
        Rectangle rectangleC = RectangleFactory.createRectangle(5, 11, 6, 8);


        RectangleFeature resultWithB = analyzer.analyze(rectangleA, rectangleB);
        RectangleFeature resultWithC = analyzer.analyze(rectangleA, rectangleC);

        assertEquals(expectedResult, resultWithB);
        assertEquals(expectedResult, resultWithC);
    }

}