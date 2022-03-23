import java.util.List;

/**
 * Interface which contains several getter methods for an Animator that can be called to obtain
 * information about the state of an animation. It has the ability to get the information about a given
 * shape at a specific time.
 */
public interface AnimatorState {

  /**
   * Returns the shape with the given shapeID at a specific time within the animation.
   *
   * @param shapeID the ID of the shape to be returned.
   * @param time    the time that is being looked for.
   * @return Shape representing the data of the given shape at the given time.
   * @throws IllegalArgumentException if time is negative.
   * @throws IllegalArgumentException if shapeID does not match any shapes.
   */
  Shape getShapeAtTime(String shapeID, int time);

  /**
   * Gets the animated shape with a given ID.
   *
   * @param shapeID The shape ID.
   * @return the animated shape corresponding to the id.
   * @throws IllegalArgumentException if the shapeID is invalid.
   */
  AnimatedShape getAnimatedShape(String shapeID);

  /**
   * Gets a list of all shapeIDs in the animation.
   *
   * @return the list of IDs.
   */
  List<String> getShapeIDs();

  /**
   * Returns a string representing the animated shape as follows:
   * <pre>
   * Shapetype ShapeID:
   * [shape1] -> [shape2]
   * [shape2] -> [shape3]
   * [shape3] -> [shape4]
   * ...
   * </pre>
   * @return the formatted string as seen above.
   */
  String toString();

}
