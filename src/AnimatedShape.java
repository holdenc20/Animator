import java.util.List;

/**
 * Class to represent a specific shape throughout an animation. This class has a startingShape,
 * an ID, and a list of motions that the shape will perform over the course of the animation.
 */
public class AnimatedShape {

  private List<Motion> motions;
  private final String shapeID;
  private Shape startShape;
  private int creationTime;
  private int deletionTime;

  /**
   * Constructor to create an AnimatedShape with a given ID, startingShape, and creationTime.
   *
   * @param shapeID      the ID of the shape.
   * @param startShape   the shape this AnimatedShape will have at the start of the animation.
   * @param creationTime the time of the shape's creation within the animation.
   */
  public AnimatedShape(String shapeID, Shape startShape, int creationTime) {
    this.shapeID = shapeID;
    this.startShape = startShape;
    this.creationTime = creationTime;
  }



}
