/**
 * Represents an ellipse shape.
 */
public class Ellipse extends AbstractShape {
  /**
   * Constructor for an Ellipse that takes in a position, width, height, and color.
   *
   * @param position - The position.
   * @param width    - The width.
   * @param height   - The height.
   * @param color    - The color.
   */
  public Ellipse(Position position, int width, int height, Color color) {
    super(position, width, height, color);
  }
}