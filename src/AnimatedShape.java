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
   * @return
   */
  Shape getShapeAtTime(int time);
  List<Motion> getMotions();
  int getCreationTime();
  int getDeletionTime();
  void setCreationTime(int time);
  void setDeletionTime(int time);
  void addMotion(Motion motion);
  void removeMotion(int time);

}
