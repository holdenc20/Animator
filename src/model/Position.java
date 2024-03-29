package model;

/**
 * Class to represent the x and y values of a position on the animation.
 */
public class Position {

  private final float xPos;
  private final float yPos;

  /**
   * Constructs a model.Position object with an x and y position.
   *
   * @param xPos the x position.
   * @param yPos the y position.
   */
  public Position(float xPos, float yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  /**
   * Gets the x position.
   *
   * @return the x position.
   */
  public float getX() {
    return xPos;
  }

  /**
   * Gets the y position.
   *
   * @return the y position.
   */
  public float getY() {
    return yPos;
  }

  @Override
  public String toString() {
    return "(" + xPos + ", " + yPos + ")";
  }

  /**
   * Returns the weighted average between two positions over a period of time.
   *
   * @param other    the other position.
   * @param tStart   the start of the time period.
   * @param tEnd     the end of the time period.
   * @param currTime the time of the average.
   * @return the model.Position representing the average.
   */
  public Position weightedAverage(Position other, int tStart, int tEnd, int currTime) {
    return new Position(Utility.weightedAverage(getX(), other.getX(), tStart, tEnd, currTime),
            Utility.weightedAverage(getY(), other.getY(), tStart, tEnd, currTime));
  }
}
