package model;

/**
 * Represents an animator that moves shapes. An animation is made out of AnimatedShapes that are
 * referred to by their ID. To create an Animated shape, the makeAnimatedShape method is used
 * with an ID and a model.Shape to represent the shape that will be displayed at the time of creation.
 * This shape can be changed later with the setStartShape method.
 *
 * <p>By default, Animated shapes are created at time 0 and have no deletion time, but these times
 * can
 * be set with the setCreationTime and setDeletionTime methods respectively. To delete a shape, use
 * the deleteShape method.
 *
 * <p>To actually animate shapes, use the addMotion method and pass in a start and end time for the
 * animation to occur as well as a target shape that will be the final state of the shape at the
 * end of the motion. During the time of the animation, the shape will gradually transform into
 * the target shape. Motions can be removed by passing in a time value that is during the motion
 * to be deleted.
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


  /**
   * Sets the width and height of the canvas.
   * @param width Width of the canvas.
   * @param heigth Height of the canvas.
   */
  void setDimensions(int width, int heigth);

}
