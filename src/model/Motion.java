package model;

/**
 * Class to represent a motion for a given shape. This class contains information about the start
 * and end times of a given shape motion as well as the final position of the shape at the end of
 * the motion.
 *
 * <p>NOTE: the starting position of the shape before a motion is the ending position of the
 * previous motion.
 */
public class Motion {
  private final int startTime;
  private final int endTime;
  private final Shape endShape;

  /**
   * Constructor for a model.Motion with a start and end time as well as an endShape.
   *
   * @param startTime the time of the start of the motion.
   * @param endTime   the time of the end of the motion.
   * @param endShape  the model.Shape to be seen at the end of the motion.
   * @throws IllegalArgumentException if either time is negative, endTime is <= startTime, or
   *                                  endShape is null.
   */
  public Motion(int startTime, int endTime, Shape endShape) {
    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Times cannot be negative!");
    }
    if (endShape == null) {
      throw new IllegalArgumentException("endShape cannot be null!");
    }
    if (startTime > endTime) {
      throw new IllegalArgumentException("start time must be strictly less than end time!");
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.endShape = endShape;
  }

  public int getStartTime() {
    return this.startTime;
  }

  public int getEndTime() {
    return this.endTime;
  }

  public Shape getEndShape() {
    return this.endShape;
  }
}
