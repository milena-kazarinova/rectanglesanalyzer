package analyzers;

import dto.RectangleFeature;
import entities.Edge;
import entities.Rectangle;
import lombok.Builder;
import lombok.Data;

public class AdjacencyAnalyzer implements Analyzer {

    private static final String FEATURE_NAME = "Adjacency";

    public enum AdjacencyRectangleFeature implements RectangleFeature {

        ADJACENCY_FEATURE(FEATURE_NAME, Boolean.FALSE, "Sub-line");
        ADJACENCY_FEATURE(FEATURE_NAME, Boolean.FALSE, "Proper");
        ADJACENCY_FEATURE(FEATURE_NAME, Boolean.FALSE, "");
        ADJACENCY_FEATURE(FEATURE_NAME, Boolean.FALSE, "Not Adjacent");

        String name;
        private boolean state;
        private String details;

        AdjacencyRectangleFeature(String featureName, boolean state, String details) {
            this.name = featureName;
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
    public AnalyzerResult analyze(Rectangle rectangleA, Rectangle rectangleB) {
        //add scenario for same rectangles (equal) add initial values h and w to rectangle
        AnalyzerResult result = new AnalyzerResult(AdjacencyRectangleFeature.ADJACENCY_FEATURE, Boolean.FALSE, "Not Adjacent")
        getAdjacency(rectangleA.getEdgeA(), rectangleB.getEdgeC());
        getAdjacency(rectangleA.getEdgeB(), rectangleB.getEdgeD());
        getAdjacency(rectangleA.getEdgeC(), rectangleB.getEdgeA());
        getAdjacency(rectangleA.getEdgeD(), rectangleB.getEdgeB());

    }

    private void getAdjacency(Edge edgeA, Edge edgeB) {

        Line lineA, lineB;

        if (edgeA.getPointA().getY() == edgeB.getPointA().getY()) {
            lineA = new Line(edgeA.getPointA().getX(), edgeA.getPointB().getX());
            lineB = new Line(edgeB.getPointA().getX(), edgeB.getPointB().getX());
        } else if (edgeA.getPointA().getX() == edgeB.getPointA().getX()) {
            lineA = new Line(edgeA.getPointA().getY(), edgeA.getPointB().getY());
            lineB = new Line(edgeB.getPointA().getY(), edgeB.getPointB().getY());
        } else {
            System.out.println("not adjacent");
            return;
        }

        getAdjacency(lineA, lineB);
    }

    private void getAdjacency(Line lineA, Line lineB) {
        if (isProper(lineA, lineB)) {
            System.out.println("proper");
        } else if (isSubLine(lineA, lineB) || isSubLine(lineB, lineA)) {
            System.out.println("sub-line");
        } else if (isPartial(lineA, lineB) || isPartial(lineB, lineA)) {
            System.out.println("partial");
        }
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
    private class Line {
        int axisPointA, axisPointB;
    }

}
