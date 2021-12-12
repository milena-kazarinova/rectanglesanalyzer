package analyzers;

import dto.RectangleFeature;
import entities.Edge;
import entities.Rectangle;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.stream.Stream;

public class Adjacency implements Analyzer {

    public static final String FEATURE_NAME = "Adjacency";

    @ToString
    public enum AdjacencyRectangleFeature implements RectangleFeature {

        SUB_LINE(FEATURE_NAME, Boolean.TRUE, "Sub-line"),
        PROPER(FEATURE_NAME, Boolean.TRUE, "Proper"),
        PARTIAL(FEATURE_NAME, Boolean.TRUE, "Partial"),
        NOT_ADJACENT(FEATURE_NAME, Boolean.FALSE, "Not adjacent");

        private final String name;
        private final boolean state;
        private final String details;

        AdjacencyRectangleFeature(String name, boolean state, String details) {
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
        if(rectangleA.equals(rectangleB)){
            return AdjacencyRectangleFeature.NOT_ADJACENT;
        }
        return Stream.of(getHorizontalAdjacency(rectangleA.getEdgeA(), rectangleB.getEdgeC()),
                getVerticalAdjacency(rectangleA.getEdgeB(), rectangleB.getEdgeD()),
                getHorizontalAdjacency(rectangleA.getEdgeC(), rectangleB.getEdgeA()),
                getVerticalAdjacency(rectangleA.getEdgeD(), rectangleB.getEdgeB()))
                .filter(RectangleFeature::getState).findFirst()
                .orElse(AdjacencyRectangleFeature.NOT_ADJACENT);
    }

    private RectangleFeature getVerticalAdjacency(Edge edgeA, Edge edgeB) {
        if (edgeA.getPointA().getX() == edgeB.getPointA().getX()) {
            Line lineA = new Line(edgeA.getPointA().getY(), edgeA.getPointB().getY());
            Line lineB = new Line(edgeB.getPointA().getY(), edgeB.getPointB().getY());
            return getAdjacency(lineA, lineB);
        } else {
            return AdjacencyRectangleFeature.NOT_ADJACENT;
        }
    }

    private RectangleFeature getHorizontalAdjacency(Edge edgeA, Edge edgeB) {
        if (edgeA.getPointA().getY() == edgeB.getPointA().getY()) {
            Line lineA = new Line(edgeA.getPointA().getX(), edgeA.getPointB().getX());
            Line lineB = new Line(edgeB.getPointA().getX(), edgeB.getPointB().getX());
            return getAdjacency(lineA, lineB);
        } else {
            return AdjacencyRectangleFeature.NOT_ADJACENT;
        }
    }

    private RectangleFeature getAdjacency(Line lineA, Line lineB) {
        if (isProper(lineA, lineB)) {
            return AdjacencyRectangleFeature.PROPER;
        } else if (isSubLine(lineA, lineB) || isSubLine(lineB, lineA)) {
            return AdjacencyRectangleFeature.SUB_LINE;
        } else if (isPartial(lineA, lineB) || isPartial(lineB, lineA)) {
            return AdjacencyRectangleFeature.PARTIAL;
        }
        return AdjacencyRectangleFeature.NOT_ADJACENT;
    }

    private boolean isProper(Line lineA, Line lineB) {
        return lineA.axisPointA == lineB.axisPointA &&
                lineA.axisPointB == lineB.axisPointB;
    }

    private boolean isSubLine(Line lineA, Line lineB) {
        return lineA.axisPointA > lineB.axisPointA &&
                lineA.axisPointB < lineB.axisPointB;
    }

    private boolean isPartial(Line lineA, Line lineB) {
        return lineA.getAxisPointA() < lineB.getAxisPointA() &&
                lineA.getAxisPointB() < lineB.getAxisPointB();
    }

    @Data
    @Builder
    private static class Line {
        int axisPointA, axisPointB;
    }

}
