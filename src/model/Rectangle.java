package model;

/**
 * Represents a rectangle shape. Concrete implementation of the view.AbstractShape class of type
 * model.Rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for a model.Rectangle that takes in a position, width, height, and color.
   *
   * @param position - The position.
   * @param width    - The width.
   * @param height   - The height.
   * @param color    - The color.
   */
  public Rectangle(Position position, float width, float height, Color color) {
    super(position, width, height, color, ShapeType.RECTANGLE);
  }

  @Override
  public Shape getAverageWith(Shape other, int startT, int endT, int currT) {
    if (other.getClass() != this.getClass()) {
      throw new IllegalArgumentException("Other shape must have the same class");
    }
    if (!validateTimes(startT, endT, currT)) {
      throw new IllegalArgumentException("Times are not valid");
    }
    return new Rectangle(
            this.getPosition().weightedAverage(other.getPosition(), startT, endT, currT),
            Utility.weightedAverage(this.getWidth(), other.getWidth(), startT, endT, currT),
            Utility.weightedAverage(this.getHeight(), other.getHeight(), startT, endT, currT),
            this.getColor().weightedAverage(other.getColor(), startT, endT, currT));
  }
}
