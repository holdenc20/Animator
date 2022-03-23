/**
 * Represents an animated shape object. This is an immutable shape that stores the state of a given
 * shape. Each shape has a position, width, height, and a color.
 */
public interface Shape {

  /**
   * Gets the position of the shape.
   *
   * @return - The position.
   */
  Position getPosition();

  /**
   * Gets the width of this shape.
   *
   * @return - The width.
   */
  int getWidth();

  /**
   * Gets the height of this shape.
   *
   * @return - The height.
   */
  int getHeight();

  /**
   * Gets the color of the shape.
   *
   * @return - The color.
   */
  Color getColor();

  /**
   * Computes the state of a shape during a continuous motion over a time.
   *
   * @param other  The other shape.
   * @param startT The start time.
   * @param endT   The end time.
   * @param currT  The current time.
   * @return The state of the shape at the current time in the motion.
   * @throws IllegalArgumentException if other is not the same class as this or if startT, endT,
   *                                  or currT are invalid.
   */
  Shape getAverageWith(Shape other, int startT, int endT, int currT);

  /**
   * Returns a string in the following format:
   * <pre>
   *   (x, y) w h (r, g, b)
   * </pre>
   * where (x, y) == pos, w == width, h == height, and (r, g, b) == color.
   *
   * @return the formatted string as shown above
   */
  String toString();

  /**
   * Gets the type of the shape.
   *
   * @return the shape type.
   */
  ShapeType getShapeType();
}
