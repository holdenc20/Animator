/**
 * Represents an animated shape object.
 * Test Push
 */
public interface Shape {

  /**
   * Gets the name id of this shape.
   * @return - The id of this shape.
   */
  public String getID();

  /**
   * Gets the X position of this shape.
   * @return - The X position.
   */
  public int getX();

  /**
   * Gets the Y position of this shape.
   * @return - The Y position.
   */
  public int getY();

  /**
   * Gets the width of this shape.
   * @return - The width.
   */
  public int getWidth();

  /**
   * Gets the height of this shape.
   * @return - The height.
   */
  public int getHeight();

  /**
   * Gets the rgb red value of this shape.
   * @return - The redness of the shape.
   */
  public int getRed();

  /**
   * Gets the rgb green value of this shape.
   * @return - The greenness of the shape.
   */
  public int getGreen();

  /**
   * Gets the rgb blue value of this shape.
   * @return - The blueness of the shape.
   */
  public int getBlue();
}
