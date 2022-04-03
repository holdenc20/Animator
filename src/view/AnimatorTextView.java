package view;

import java.io.IOException;

import model.AnimatedShape;
import model.AnimatorState;
import model.Motion;
import model.Shape;

/**
 * Implementation of the view.AnimatorView in text that appends the toString method to a given
 * appendable upon call of the renderAnimation() method.
 */
public class AnimatorTextView implements AnimatorView {

  private Appendable app;
  private AnimatorState state;
  private double tickRate;

  /**
   * Default Constructor for the view.AnimatorTextView which initializes all variables.
   */
  public AnimatorTextView() {
    this.app = System.out;
    this.state = null;
    this.tickRate = 1;
  }

  /**
   * Constructor for an view.AnimatorTextView that uses the console as output.
   *
   * @param state the state of the animation.
   */
  public AnimatorTextView(AnimatorState state) {
    this.state = state;
    this.app = System.out;
    this.tickRate = 1;
  }

  @Override
  public void renderAnimation() throws IOException {
    app.append(this.toString());
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

  /**
   * Represents an Animation through text in the following format.
   * <pre>
   *   t == time
   *   p == position as (x, y)
   *   w == width
   *   h == height
   *   c == color as (r, g, b)
   *   t pos w h color -> t pos w h color
   *   ----------------------------------
   *   [AnimatedShape1]
   *
   *   [AnimatedShape2]
   *
   *   ...
   * </pre>
   * where [AnimatedShapeN] is of the format.
   * <pre>
   * Shapetype ShapeID:
   * [shape1] -> [shape2]
   * [shape2] -> [shape3]
   * [shape3] -> [shape4]
   * ...
   * </pre>
   * and [shapeN] is of the format:
   * <pre>
   *   t (x, y) w h (r, g, b)
   * </pre>
   * @return the formatted string as shown above
   */
  @Override
  public String toString() {
    //TODO: Add creation and deletion markers for text view
    StringBuilder builder = new StringBuilder();
    addIntro(builder);
    for (String s : state.getShapeIDs()) {
      addAnimatedShape(builder, state.getAnimatedShape(s));
      builder.append("\n");
    }
    return builder.toString();
  }

  private void addAnimatedShape(StringBuilder builder, AnimatedShape shape) {
    addIntro(builder, shape);
    Shape prevShape = shape.getStartShape();
    for (Motion m : shape.getMotions()) {
      builder.append(convertSeconds(m.getStartTime()) + " " + prevShape.toString() + " -> ");
      builder.append(convertSeconds(m.getEndTime()) + " " + m.getEndShape().toString() + "\n");
      prevShape = m.getEndShape();
    }
  }

  private double convertSeconds(double ticks) {
    return ticks / tickRate;
  }

  private void addIntro(StringBuilder builder, AnimatedShape shape) {
    builder.append(shape.getStartShape().getShapeType() + " " + shape.getID() + ":\n");
  }

  private void addIntro(StringBuilder builder) {
    builder.append("t == time\n");
    builder.append("p == position as (x, y)\n");
    builder.append("w == width\n");
    builder.append("h == height\n");
    builder.append("c == color as (r, g, b)\n");
    builder.append("t pos w h color -> t pos w h color\n");
    builder.append("----------------------------------\n");
  }
}
