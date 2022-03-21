/**
 * Represents an abstract shape.
 */
public abstract class AbstractShape implements Shape{
  private final String id;
  private final int xPosn;
  private final int yPosn;
  private final int width;
  private final int height;
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor for an abstract shape.
   * @param id - The ID of a shape.
   * @param xPosn - The X position of a shape.
   * @param yPosn - The Y position of a shape.
   * @param width - The width of a shape.
   * @param height - The height of a shape.
   * @param red - The rgb red value.
   * @param green - The rgb green value.
   * @param blue - The rgb blue value.
   */
  public AbstractShape(String id, int xPosn, int yPosn, int width, int height, int red, int green, int blue) {
    if(red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255){
      throw new IllegalArgumentException("RGB values are between 0 and 255!");
    }
    if(width < 0 || height < 0){
      throw new IllegalArgumentException("Width/Height cannot be negative!");
    }
    if(id == null){
      throw new IllegalArgumentException("ID cannot be null!");
    }
    this.id = id;
    this.xPosn = xPosn;
    this.yPosn = yPosn;
    this.width = width;
    this.height = height;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public String getID() {
    return this.id;
  }

  @Override
  public int getX() {
    return this.xPosn;
  }

  @Override
  public int getY() {
    return this.yPosn;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }
}