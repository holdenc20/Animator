/**
 * Represents an abstract shape. This shape stores a position, width, height, and a color.
 */
public abstract class AbstractShape implements Shape {
  private final Position position;
  private final int width;
  private final int height;
  private final Color color;

  /**
   * Constructor for an abstract shape that takes in a position, width, height and a color.
   *
   * @param position - The position of a shape.
   * @param width    - The width of a shape.
   * @param height   - The height of a shape.
   * @param color    - the color of a shape.
   */
  public AbstractShape(Position position, int width, int height, Color color) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width/Height cannot be negative!");
    }
    this.color = color;
    this.width = width;
    this.height = height;
    this.position = position;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Position getPosition() {
    return position;
  }
}