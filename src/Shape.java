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
}
