package analyzers;

import dto.RectangleFeature;
import entities.Rectangle;
import lombok.ToString;

public class Containment implements Analyzer {

    public static final String FEATURE_NAME = "Containment";

    @ToString
    public enum ContainmentRectangleFeature implements RectangleFeature {

        CONTAINMENT(FEATURE_NAME, Boolean.TRUE, "Containment"),
        NO_CONTAINMENT(FEATURE_NAME, Boolean.FALSE, "No containment");

        private final String name;
        private final boolean state;
        private final String details;

        ContainmentRectangleFeature(String name, boolean state, String details) {
            this.name = name;
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
        if (isContainment(rectangleA, rectangleB) || isContainment(rectangleB, rectangleA)) {
            return ContainmentRectangleFeature.CONTAINMENT;
        }
        return ContainmentRectangleFeature.NO_CONTAINMENT;
    }

    private boolean isContainment(Rectangle rectangleA, Rectangle rectangleB) {
        return rectangleA.getEdgeA().getPointA().getX() < rectangleB.getEdgeA().getPointA().getX() &&
                rectangleA.getEdgeA().getPointA().getY() > rectangleB.getEdgeA().getPointA().getY() &&
                rectangleA.getEdgeB().getPointB().getX() > rectangleB.getEdgeB().getPointB().getX() &&
                rectangleA.getEdgeB().getPointB().getY() < rectangleB.getEdgeB().getPointB().getX();
    }
}
