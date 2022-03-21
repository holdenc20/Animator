/**
 * Represents an animator that moves shapes.
 */
public interface Animator {

  /**
   * Adds a shape object to this animation.
   * @param s - A shape.
   */
  public void makeShape(Shape s);

  /**
   * Adds a motion for a shape.
   *
   * @param shapeID - The name of the shape we want to give motion to .
   * @param target - The target shape for the shape to move to.
   * @param initialTime - The starting time of this motion.
   * @param endTime - The ending time of this motion.
   */
  public void addMotion(String shapeID, Shape target, int initialTime, int endTime);

}
