package analyzers;

import dto.RectangleFeature;
import entities.Edge;
import entities.Rectangle;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Intersection implements Analyzer {

    public static final String FEATURE_NAME = "Intersection";
    public static final String NO_INTERSECTION = "No intersection";
    public static final String EQUAL = "Equal";

    @ToString
    @EqualsAndHashCode
    public static class IntersectionRectangleFeature implements RectangleFeature {

        private final String name;
        private final boolean state;
        private final String details;

        public IntersectionRectangleFeature(boolean state, String details) {
            this.name = FEATURE_NAME;
            this.state = state;
            this.details = details;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean getState() {
            return state;
        }

        @Override
        public String getDetails() {
            return details;
        }
    }

    @Override
    public RectangleFeature analyze(Rectangle rectangleA, Rectangle rectangleB) {
        String intersectionsDetails = getIntersectionsDetails(rectangleA, rectangleB);
        if (StringUtils.isNotBlank(intersectionsDetails)) {
            return new IntersectionRectangleFeature(Boolean.TRUE, intersectionsDetails);
        }
        return new IntersectionRectangleFeature(Boolean.FALSE, NO_INTERSECTION);
    }

    private String getIntersectionsDetails(Rectangle rectangleA, Rectangle rectangleB) {
        if(rectangleA.equals(rectangleB)) {
            return EQUAL;
        }
        return Stream.of(getIntersection(rectangleA.getEdgeA(), rectangleB.getEdgeB()),
                getIntersection(rectangleA.getEdgeA(), rectangleB.getEdgeD()),
                getIntersection(rectangleA.getEdgeC(), rectangleB.getEdgeB()),
                getIntersection(rectangleA.getEdgeC(), rectangleB.getEdgeD()),
                getIntersection(rectangleB.getEdgeA(), rectangleA.getEdgeB()),
                getIntersection(rectangleB.getEdgeA(), rectangleA.getEdgeD()),
                getIntersection(rectangleB.getEdgeC(), rectangleA.getEdgeB()),
                getIntersection(rectangleB.getEdgeC(), rectangleA.getEdgeD()))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(", "));
    }

    private String getIntersection(Edge edgeHorizontal, Edge edgeVertical) {
        boolean isXInRange = isInRangeExclusive(edgeVertical.getPointA().getX(),
                edgeHorizontal.getPointA().getX(), edgeHorizontal.getPointB().getX());
        boolean isYInRange = isInRangeExclusive(edgeHorizontal.getPointA().getY(),
                edgeVertical.getPointB().getY(), edgeVertical.getPointA().getY());
        if (isXInRange && isYInRange) {
            return String.format("(%d,%d)", edgeVertical.getPointA().getX(), edgeHorizontal.getPointA().getY());
        }
        return StringUtils.EMPTY;
    }

    private boolean isInRangeExclusive(int point, int min, int max) {
        return min < point && max > point;
    }
}
