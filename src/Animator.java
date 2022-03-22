/**
 * Represents an animator that moves shapes.
 * TODO: document more
 */
public interface Animator extends AnimatorState {

  /**
   * Adds a shape object to this animation.
   *
   * @param s - A shape.
   */
  void makeShape(Shape s);

  /**
   * Adds a motion for a shape.
   *
   * @param shapeID     - The ID of the shape we want to give motion to.
   * @param target      - The target shape for the shape to move to.
   * @param initialTime - The starting time of this motion.
   * @param endTime     - The ending time of this motion.
   */
  void addMotion(String shapeID, Shape target, int initialTime, int endTime);

  /**
   * Removes the motion for a given shape that begins at a given time.
   *
   * @param shapeID     the ID of the shape for which the motion will be removed.
   * @param initialTime the initial time of the motion to be removed.
   * @throws IllegalArgumentException if shapeID does not match any shapes.
   * @throws IllegalArgumentException if there is no motion that matches the given initialTime.
   */
  void removeMotion(String shapeID, int initialTime);


}
