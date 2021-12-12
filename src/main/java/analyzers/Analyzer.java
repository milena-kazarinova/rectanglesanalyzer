package analyzers;

import dto.RectangleFeature;
import entities.Rectangle;

public interface Analyzer {
    RectangleFeature analyze(Rectangle rectangleA, Rectangle rectangleB);
}
