package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rectangle {
    private Edge edgeA, edgeB, edgeC, edgeD;
}
