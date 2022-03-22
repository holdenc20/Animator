/**
 * Represents an animated shape object. This is an immutable shape that stores all of the
 */
public interface Shape {

  /**
   * Gets the name id of this shape.
   *
   * @return - The id of this shape.
   */
  String getID();

  /**
   * Gets the X position of this shape.
   *
   * @return - The X position.
   */
  int getX();

  /**
   * Gets the Y position of this shape.
   *
   * @return - The Y position.
   */
  int getY();

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
   * Gets the rgb red value of this shape.
   *
   * @return - The redness of the shape.
   */
  int getRed();

  /**
   * Gets the rgb green value of this shape.
   *
   * @return - The greenness of the shape.
   */
  int getGreen();

  /**
   * Gets the rgb blue value of this shape.
   *
   * @return - The blueness of the shape.
   */
  int getBlue();
}
