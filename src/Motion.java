public class Motion {
  private final int startTime;
  private final int endTime;
  private final Shape endShape;

  public Motion(int startTime, int endTime, Shape endShape) {
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
