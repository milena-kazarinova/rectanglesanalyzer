package entities;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class Point {
    int x1, y1, x2, y2;
}
