/**
 * Class representing a color object with rgb values from 0 to 255 inclusive.
 */
public class Color {

  private final float red;
  private final float green;
  private final float blue;

  /**
   * Constructor to create a simple color object with rgb values.
   *
   * @param red   the red value.
   * @param green the green value.
   * @param blue  the blue value.
   * @throws IllegalArgumentException if rbg values are not between 0 and 255 inclusive.
   */
  public Color(float red, float green, float blue) {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("RGB values are between 0 and 255!");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets the red value.
   *
   * @return red value.
   */
  public float getRed() {
    return red;
  }

  /**
   * Gets the green value.
   *
   * @return green value.
   */
  public float getGreen() {
    return green;
  }

  /**
   * Gets the blue value.
   *
   * @return blue value.
   */
  public float getBlue() {
    return blue;
  }

  @Override
  public String toString() {
    return "(" + red + ", " + green + ", " + blue + ")";
  }

  /**
   * Returns the weighted average between two colors over a period of time.
   *
   * @param other the other color.
   * @param tStart the start of the time period.
   * @param tEnd the end of the time period.
   * @param currTime the time of the average.
   * @return the Color representing the average.
   */
  public Color weightedAverage(Color other, int tStart, int tEnd, int currTime) {
    return new Color(Utility.weightedAverage(getRed(), other.getRed(), tStart, tEnd, currTime),
            Utility.weightedAverage(getGreen(), other.getGreen(), tStart, tEnd, currTime),
            Utility.weightedAverage(getBlue(), other.getBlue(), tStart, tEnd, currTime));
  }

}
