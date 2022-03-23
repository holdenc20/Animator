/**
 * An enumeration for the type of the shape.
 */
public enum ShapeType {
  RECTANGLE, ELLIPSE;

  @Override
  public String toString() {
    switch (this) {
      case RECTANGLE:
        return "Rectangle";
      case ELLIPSE:
        return "Ellipse";
      default:
        return "";
    }
  }
}
