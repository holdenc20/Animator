/**
 * Represents an animator that moves shapes.
 * TODO: document more
 */
public interface Animator extends AnimatorState {

  /**
   * Adds a shape object to this animation.
   *
   * @param shapeID - The shape ID.
   * @param shape   - the starting shape.
   */
  void makeAnimatedShape(String shapeID, Shape shape);

  /**
   * Gets a shape with a given ID.
   * @param shapeID The shape ID.
   * @return
   */
  AnimatedShape getAnimatedShape(String shapeID);

  /**
   * Deletes the shape with the given ID.
   *
   * @param shapeID the id of the shape to be deleted.
   */
  void deleteShape(String shapeID);

  /**
   * Adds a motion for a shape.
   *
   * @param shapeID     - The ID of the shape we want to give motion to.
   * @param target      - The target shape for the shape to move to.
   * @param initialTime - The starting time of this motion.
   * @param endTime     - The ending time of this motion.
   * @throws IllegalArgumentException if the motion cannot be added.
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

  /**
   * Sets the start shape of the shape with the given ID.
   *
   * @param shapeID the ID of the shape to be changed.
   * @param shape   the new starting shape.
   * @throws IllegalArgumentException if the shapeID is invalid, or the new starting shape is
   *                                  invalid.
   */
  void setStartShape(String shapeID, Shape shape);

  /**
   * Sets the creation time of the shape with the given ID.
   *
   * @param shapeID the ID of the shape to be changed.
   * @param time    the new creation time.
   * @throws IllegalArgumentException if the shapeID is invalid, or if the time is invalid.
   */
  void setCreationTime(String shapeID, int time);

  /**
   * Sets the deletion time of the shape with the given ID.
   *
   * @param shapeID the ID of the shape to be changed.
   * @param time    the new deletion time.
   * @throws IllegalArgumentException if the shapeID is invalid, or if the time is invalid.
   */
  void setDeletionTime(String shapeID, int time);


}
