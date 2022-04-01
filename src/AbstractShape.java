/**
 * Represents an abstract shape of a given ShapeType.
 * This shape stores a position, width, height, and a color.
 */
public abstract class AbstractShape implements Shape {
  private final Position position;
  private final float width;
  private final float height;
  private final Color color;
  private final ShapeType type;

  /**
   * Constructor for an abstract shape that takes in a position, width, height and a color.
   *
   * @param position - The position of a shape.
   * @param width    - The width of a shape.
   * @param height   - The height of a shape.
   * @param color    - the color of a shape.
   * @throws IllegalArgumentException if width or height is negative.
   */
  public AbstractShape(Position position, float width, float height, Color color, ShapeType type) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width/Height cannot be negative!");
    }
    this.color = color;
    this.width = width;
    this.height = height;
    this.position = position;
    this.type = type;
  }

  @Override
  public float getWidth() {
    return this.width;
  }

  @Override
  public float getHeight() {
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

  /**
   * Validates that: start <= curr <= end.
   *
   * @param start the start time.
   * @param end   the end time.
   * @param curr  the current time.
   * @return true if the conditions are met.
   */
  protected boolean validateTimes(int start, int end, int curr) {
    return curr >= start && end >= curr;
  }

  @Override
  public String toString() {
    return position.toString() + " " + width + " " + height + " " + color.toString();
  }

  @Override
  public ShapeType getShapeType() {
    return type;
  }
}