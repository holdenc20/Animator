package model;

/**
 * An enumeration for the type of the shape.
 */
public enum ShapeType {
  RECTANGLE("Rectangle"),
  ELLIPSE("Ellipse");

  private String type;

  private ShapeType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
