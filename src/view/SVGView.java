package view;

import java.io.IOException;

import model.AnimatedShape;
import model.AnimatorState;
import model.Color;
import model.Motion;
import model.Shape;
import model.ShapeType;

/**
 * Implementation of AnimatorView that creates an SVG formatted text.
 */
public class SVGView implements AnimatorView {

  private Appendable app;
  private AnimatorState state;
  private double tickRate;

  /**
   * Constructs an SVGView with default values.
   */
  public SVGView() {
    this.app = null;
    this.state = null;
    this.tickRate = 1;
  }

  @Override
  public void renderAnimation() throws IOException {
    app.append(toString());
  }

  @Override
  public void setModelState(AnimatorState state) {
    this.state = state;
  }

  @Override
  public void setTickRate(double tickRate) {
    this.tickRate = tickRate;
  }

  @Override
  public void setOutput(Appendable app) {
    this.app = app;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    addHeader(builder);
    for (String name : state.getShapeIDs()) {
      addAnimatedShape(state.getAnimatedShape(name), builder);
    }
    builder.append("</svg>");
    return builder.toString();
  }

  private void addAnimatedShape(AnimatedShape shape, StringBuilder builder) {
    addShapeHeader(shape, builder);
    Shape prevShape = shape.getStartShape();

    addCreation(shape.getID(), builder);

    for (Motion m : shape.getMotions()) {
      addMotion(m, builder, prevShape);
      prevShape = m.getEndShape();
    }

    addDeletion(shape.getID(), builder);

    if (shape.getStartShape().getShapeType() == ShapeType.ELLIPSE) {
      builder.append("</ellipse>\n");
    } else {
      builder.append("</rect>\n");
    }
  }

  private void addCreation(String shapeID, StringBuilder builder) {
    int creationTime = state.getAnimatedShape(shapeID).getCreationTime();
    addAnimateTag(0, creationTime,
            "visibility", "hidden", "visible", builder);
  }


  private void addDeletion(String shapeID, StringBuilder builder) {
    int delTime = state.getAnimatedShape(shapeID).getDeletionTime();
    if (delTime != -1) {
      addAnimateTag(delTime, delTime + 1,
              "visibility", "visible", "hidden", builder);
    }
  }

  private void addMotion(Motion m, StringBuilder builder, Shape prevShape) {
    if (prevShape.getShapeType() == ShapeType.ELLIPSE) {
      addEllipseMotion(m, builder, prevShape);
    } else {
      addRectMotion(m, builder, prevShape);
    }
  }

  private void addRectMotion(Motion m, StringBuilder builder, Shape prevShape) {
    addAnimateTag(m.getStartTime(), m.getEndTime(), "x",
            "" + (int) (prevShape.getPosition().getX()),
            "" + (int) (m.getEndShape().getPosition().getX()),
            builder);
    addAnimateTag(m.getStartTime(), m.getEndTime(), "y",
            "" + (int) (prevShape.getPosition().getY()),
            "" + (int) (m.getEndShape().getPosition().getY()),
            builder);
    addAnimateTag(m.getStartTime(), m.getEndTime(), "width",
            "" + (int) (prevShape.getWidth()),
            "" + (int) (m.getEndShape().getWidth()),
            builder);
    addAnimateTag(m.getStartTime(), m.getEndTime(), "height",
            "" + (int) (prevShape.getHeight()),
            "" + (int) (m.getEndShape().getHeight()),
            builder);
    addAnimateTag(m.getStartTime(), m.getEndTime(), "fill",
            svgColorString(prevShape.getColor()),
            svgColorString(m.getEndShape().getColor()),
            builder);
  }

  private void addEllipseMotion(Motion m, StringBuilder builder, Shape prevShape) {
    addAnimateTag(m.getStartTime(), m.getEndTime(), "cx",
            "" + (int) (prevShape.getPosition().getX() + prevShape.getWidth() / 2),
            "" + (int) (m.getEndShape().getPosition().getX() + m.getEndShape().getWidth() / 2),
            builder);
    addAnimateTag(m.getStartTime(), m.getEndTime(), "cy",
            "" + (int) (prevShape.getPosition().getY() + prevShape.getHeight() / 2),
            "" + (int) (m.getEndShape().getPosition().getY() + m.getEndShape().getHeight() / 2),
            builder);
    addAnimateTag(m.getStartTime(), m.getEndTime(), "rx",
            "" + (int) (prevShape.getWidth() / 2),
            "" + (int) (m.getEndShape().getWidth() / 2),
            builder);
    addAnimateTag(m.getStartTime(), m.getEndTime(), "ry",
            "" + (int) (prevShape.getHeight() / 2),
            "" + (int) (m.getEndShape().getHeight() / 2),
            builder);
    addAnimateTag(m.getStartTime(), m.getEndTime(), "fill",
            svgColorString(prevShape.getColor()),
            svgColorString(m.getEndShape().getColor()),
            builder);
  }

  private String svgColorString(Color color) {
    return String.format("rgb(%d,%d,%d)", (int) (255 * color.getRed()),
            (int) (255 * color.getGreen()),
            (int) (255 * color.getBlue()));
  }

  private void addAnimateTag(double startTime, double endTime, String attribute, String from,
                             String to, StringBuilder builder) {
    builder.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" " +
                    "dur=\"%sms\" attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
            (int) convertSeconds(startTime), (int) convertSeconds(endTime - startTime),
            attribute, from, to));
  }

  private double convertSeconds(double ticks) {
    return ticks * 1000 / tickRate;
  }

  private void addShapeHeader(AnimatedShape shape, StringBuilder builder) {
    if (shape.getStartShape().getShapeType() == ShapeType.ELLIPSE) {
      addEllipseHeader(shape.getStartShape(), builder, shape.getID());
    } else {
      addRectangleHeader(shape.getStartShape(), builder, shape.getID());
    }
  }

  private void addRectangleHeader(Shape shape, StringBuilder builder, String name) {
    builder.append(String.format("<rect id=\"%s\" x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" " +
                    "fill=\"%s\" visibility=\"hidden\" >\n", name,
            (int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
            (int) shape.getWidth(), (int) shape.getHeight(), svgColorString(shape.getColor())));
  }

  private void addEllipseHeader(Shape shape, StringBuilder builder, String name) {
    builder.append(String.format("<ellipse id=\"%s\" cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\" " +
                    "fill=\"%s\" visibility=\"hidden\" >\n", name,
            (int) (shape.getPosition().getX() + shape.getWidth() / 2),
            (int) (shape.getPosition().getY() + shape.getHeight() / 2),
            (int) (shape.getWidth() / 2), (int) (shape.getHeight() / 2),
            svgColorString(shape.getColor())));
  }

  private void addHeader(StringBuilder builder) {
    builder.append(String.format("<svg width=\"%s\" height=\"%s\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">", state.getWidth(), state.getHeight()));
    builder.append("\n");
  }
}
