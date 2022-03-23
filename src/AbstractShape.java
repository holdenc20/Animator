/**
 * Represents an abstract shape. This shape stores a position, width, height, and a color.
 */
public abstract class AbstractShape implements Shape {
  private final Position position;
  private final int width;
  private final int height;
  private final Color color;
  private final ShapeType type;

  /**
   * Constructor for an abstract shape that takes in a position, width, height and a color.
   *
   * @param position - The position of a shape.
   * @param width    - The width of a shape.
   * @param height   - The height of a shape.
   * @param color    - the color of a shape.
   */
  public AbstractShape(Position position, int width, int height, Color color, ShapeType type) {
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

  protected int weightedAverage(int start, int end, int tStart, int tEnd, int currTime) {
    return (end - start) * (currTime - tStart) / (tEnd - tStart) + start;
  }

  protected Position weightedAveragePosition(Position start, Position end, int tStart, int tEnd,
                                             int currTime) {
    return new Position(weightedAverage(start.getX(), end.getX(), tStart, tEnd, currTime),
            weightedAverage(start.getY(), end.getY(), tStart, tEnd, currTime));
  }

  protected Color weightedAverageColor(Color start, Color end, int tStart, int tEnd,
                                       int currTime) {
    return new Color(weightedAverage(start.getRed(), end.getRed(), tStart, tEnd, currTime),
            weightedAverage(start.getGreen(), end.getGreen(), tStart, tEnd, currTime),
            weightedAverage(start.getBlue(), end.getBlue(), tStart, tEnd, currTime));
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