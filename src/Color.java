/**
 * Class representing a color object with rgb values from 0 to 255 inclusive.
 */
public class Color {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor to create a simple color object with rgb values.
   *
   * @param red   the red value.
   * @param green the green value.
   * @param blue  the blue value.
   * @throws IllegalArgumentException if rbg values are not between 0 and 255 inclusive.
   */
  public Color(int red, int green, int blue) {
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
  public int getRed() {
    return red;
  }

  /**
   * Gets the green value.
   *
   * @return green value.
   */
  public int getGreen() {
    return green;
  }

  /**
   * Gets the blue value.
   *
   * @return blue value.
   */
  public int getBlue() {
    return blue;
  }

  @Override
  public String toString() {
    return "(" + red + ", " + green + ", " + blue + ")";
  }
}
