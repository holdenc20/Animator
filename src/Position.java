/**
 * Class to represent the x and y values of a position on the animation.
 */
public class Position {

  private final int xPos;
  private final int yPos;

  /**
   * Constructs a Position object with an x and y position.
   *
   * @param xPos the x position.
   * @param yPos the y position.
   * @throws IllegalArgumentException if either position is negative.
   */
  public Position(int xPos, int yPos) {
    if (xPos < 0 || yPos < 0) {
      throw new IllegalArgumentException("x and y values cannot be negative!");
    }
    this.xPos = xPos;
    this.yPos = yPos;
  }

  /**
   * Gets the x position.
   *
   * @return the x position.
   */
  public int getX() {
    return xPos;
  }

  /**
   * Gets the y position.
   *
   * @return the y position.
   */
  public int getY() {
    return yPos;
  }
}
