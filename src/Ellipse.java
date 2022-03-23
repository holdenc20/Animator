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

  @Override
  public Shape getAverageWith(Shape other, int startT, int endT, int currT) {
    if (other.getClass() != this.getClass()) {
      throw new IllegalArgumentException("Other shape must have the same class");
    }
    if (!validateTimes(startT, endT, currT)) {
      throw new IllegalArgumentException("Times are not valid");
    }
    return new Ellipse(
            weightedAveragePosition(this.getPosition(), other.getPosition(), startT, endT, currT),
            weightedAverage(this.getWidth(), other.getWidth(), startT, endT, currT),
            weightedAverage(this.getHeight(), other.getHeight(), startT, endT, currT),
            weightedAverageColor(this.getColor(), other.getColor(), startT, endT, currT));
  }
}