package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to create a Planet Animation.
 */
public class PlanetCreator {

  /**
   * Main method to create the Planet animation and add it to the proper file.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    new PlanetCreator();
  }

  private int width = 600;
  private int height = 600;

  private int totalTicks = 500;

  private String fileOutputPath = "resources/planets.txt";
  private List<Planet> planetList;

  private class Planet {
    final double radius;
    final double distance;
    final double revSpeed;
    final double red;
    final double green;
    final double blue;
    final int ID;
    final double startAngle;

    /**
     * Creates a planet with a radius, distance from the sun, and revSpeed;
     */
    public Planet(int radius, int distance, double revSpeed, double red, double green,
                  double blue, int ID, double startAngle) {
      this.radius = radius;
      this.distance = distance;
      this.revSpeed = revSpeed;
      this.red = red;
      this.green = green;
      this.blue = blue;
      this.ID = ID;
      this.startAngle = startAngle;
    }
  }

  /**
   * Creates a Planet Animation and writes it to the file.
   */
  public PlanetCreator() {
    initPlanets();
    writeAnimation();
  }

  private void initPlanets() {
    planetList = new ArrayList<>();
    planetList.add(new Planet(8, 60, 20, 0.8, 0.4, 0, 0,
            Math.random()));
    planetList.add(new Planet(12, 90, 18, 0.7, 0.6, 0, 1,
            Math.random()));
    planetList.add(new Planet(13, 110, 17, 0, 0, 1, 2,
            Math.random()));
    planetList.add(new Planet(10, 130, 13, 1, 0, 0, 3,
            Math.random()));
    planetList.add(new Planet(25, 170, 8, 0.6, 0.6, 0.3, 4,
            Math.random()));
    planetList.add(new Planet(22, 210, 5, 0.6, 0.6, 0.4, 5,
            Math.random()));
    planetList.add(new Planet(18, 250, 3, 0, 0.8, 0.8, 6,
            Math.random()));
    planetList.add(new Planet(20, 290, 1.5, 0.3, 0, 1, 7,
            Math.random()));
  }

  private void writeAnimation() {
    try {
      FileWriter writer = new FileWriter(fileOutputPath);
      writer.write(toString());
      writer.flush();
      writer.close();
    } catch (IOException e) {
      System.out.println("IOException");
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    createShapes(builder);
    addAnimations(builder);
    return builder.toString();
  }

  private void createShapes(StringBuilder builder) {
    builder.append(String.format("canvas %d %d\n", width, height));
    for (Planet p : planetList) {
      int[] pos = getPosAtTime(p, 0);
      builder.append(String.format("oval name P%d center-x %d center-y %d x-radius %d " +
                      "y-radius %d color %f %f %f from %d to %d\n", p.ID,
              pos[0], pos[1],
              (int) (p.radius), (int) (p.radius),
              p.red, p.green, p.blue, 0, totalTicks));
    }
    builder.append(String.format("oval name SUN center-x %d center-y %d x-radius %d " +
                    "y-radius %d color %d %d %d from %d to %d\n", width / 2, height / 2,
            50, 50, 1, 1, 0, 0, totalTicks));
  }

  private void addAnimations(StringBuilder builder) {
    for (int i = 0; i < totalTicks - 1; i++) {
      for (Planet p : planetList) {
        int[] posStart = getPosAtTime(p, i);
        int[] posEnd = getPosAtTime(p, i+1);
        builder.append(String.format("move name P%d moveto %d %d %d %d from %d to %d\n",
                p.ID, posStart[0], posStart[1], posEnd[0], posEnd[1], i, i+1));
      }
    }
  }

  private int[] getPosAtTime(Planet p, int tick) {
    double angle = tick*p.revSpeed/100+p.startAngle*2*Math.PI;
    double x = width / 2 + p.distance * Math.cos(angle);
    double y = height / 2 + p.distance * Math.sin(angle);
    int[] pos = {(int) x, (int) y};
    return pos;
  }
}
