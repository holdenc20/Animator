import java.util.List;

/**
 * Interface to represent a specific shape throughout an animation. This Interface has a
 * startingShape, an ID, and a list of motions that the shape will perform over the course of the
 * animation. The interface also has a creationTime and a deletionTime for any given shape that
 * represents when the shape will be added or removed from the animation.
 */
public interface AnimatedShape {

  /**
   * Gets the ID of the animated shape.
   *
   * @return - The ID of the shape.
   */
  String getID();

  /**
   * Gets the state of the animated shape at a given time.
   *
   * @param time - The time to be shown.
   * @return Shape representing the state of the shape at the given time.
   * @throws IllegalArgumentException if time is out of range.
   */
  Shape getShapeAtTime(int time);

  /**
   * Gets a list of all motions to be performed by the shape during the animation.
   *
   * @return List of Motion objects.
   */
  List<Motion> getMotions();

  /**
   * Gets the creation time of the shape.
   *
   * @return the creation time.
   */
  int getCreationTime();

  /**
   * Gets the deletion time of the shape.
   *
   * @return the deletion time.
   */
  int getDeletionTime();

  /**
   * Sets the creation time of the shape.
   *
   * @param time - The time of the creation.
   * @throws IllegalArgumentException if time is negative, time is after any motions begin, or time
   *                                  is after deletion.
   */
  void setCreationTime(int time);

  /**
   * Sets the deletion time of the shape.
   *
   * @param time - The time of the deletion.
   * @throws IllegalArgumentException if time is negative, time is before creation, or time is
   *                                  before any motions end.
   */
  void setDeletionTime(int time);

  /**
   * Adds a motion to the shape.
   *
   * @param motion - The motion to be added.
   * @throws IllegalArgumentException if motion starts before creation, ends after deletion, or
   *                                  overlaps with any prior motion.
   */
  void addMotion(Motion motion);

  /**
   * Removes the motion taking place at the given time.
   *
   * @param time - The time.
   * @throws IllegalArgumentException if no motion can be removed.
   */
  void removeMotion(int time);

}
