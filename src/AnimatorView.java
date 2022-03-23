/**
 * Interface to represent the view of a given animation.
 */
public interface AnimatorView {

  /**
   * Represents an Animation through text in the following format:
   * <pre>
   *   t == time
   *   p == position as (x, y)
   *   w == width
   *   h == height
   *   c == color as (r, g, b)
   *   t pos w h color -> t pos w h color
   *   ----------------------------------------------------------------------
   *   [AnimatedShape1]
   *
   *   [AnimatedShape2]
   *
   *   ...
   * </pre>
   * where [AnimatedShapeN] is of the format:
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
  String toString();

  /**
   * Renders the Animator to a given destination.
   */
  void renderAnimation();



















}
