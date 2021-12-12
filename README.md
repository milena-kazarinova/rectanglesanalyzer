# Rectangles Analyzer
Algorithms that analyze rectangles and analyzers that exist among rectangles.

**Input:**

1.Use _RectangleFactory.createRectangle(x, y, height, width)_ to create rectangle objects.

2.Then instantiate any analyzer object to pass created rectangles:

 _Analyzer adjacency = new Adjacency();_
 
_adjacency.analyze(rectangleA, rectangleB);_

**Output:**

Rectangle feature result contains:
1. Feature name
2. Boolean state of the feature
3. Details of the state

_An example is given in the ApplicationRunner class._

**Preconditions:**
1. Java 8
2. Lombok plugin (for IntelliJIDEA)
3. Apache Maven
