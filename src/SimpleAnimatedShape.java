import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implementation of an AnimatedShape the stores motions in a list and gets values, inserts,
 * and deletes.
 */
public class SimpleAnimatedShape implements AnimatedShape {

  private SortedSet<Motion> motions;
  private String shapeID;
  private Shape startShape;
  private int creationTime;
  private int deletionTime;

  /**
   * Constructor to create an AnimatedShape with a given ID and startingShape. By default, there
   * are no motions, the creation time is 0, and there is no deletion time.
   *
   * @param shapeID    the ID of the shape.
   * @param startShape the shape this AnimatedShape will have at the start of the animation.
   */
  public SimpleAnimatedShape(String shapeID, Shape startShape) {
    if (shapeID == null || shapeID.equals("")) {
      throw new IllegalArgumentException("ID cannot be null or empty");
    }
    if (startShape == null) {
      throw new IllegalArgumentException("startShape cannot be null");
    }
    this.shapeID = shapeID;
    this.startShape = startShape;
    creationTime = 0;
    deletionTime = -1;
    motions = new TreeSet<Motion>((o1, o2) -> o1.getStartTime() - o2.getStartTime());
  }

  /**
   * Copy Constructor that makes a copy of the given shape.
   *
   * @param shape the shape to be copied.
   */
  public SimpleAnimatedShape(AnimatedShape shape) {
    motions = new TreeSet<Motion>(shape.getMotions());
    shapeID = shape.getID();
    startShape = shape.getStartShape();
    creationTime = shape.getCreationTime();
    deletionTime = shape.getDeletionTime();
  }

  @Override
  public String getID() {
    return shapeID;
  }

  @Override
  public Shape getShapeAtTime(int time) {
    if (time < creationTime || (deletionTime != -1 && time > deletionTime)) {
      throw new IllegalArgumentException("Time is out of range!");
    }
    Shape prevShape = startShape;
    for (Motion m : motions) {
      if (m.getEndTime() > time) {
        if (time >= m.getStartTime()) {
          return weightedAverageShape(prevShape, m.getEndShape(), m.getStartTime(), m.getEndTime(),
                  time);
        } else {
          return prevShape;
        }
      }
      prevShape = m.getEndShape();
    }
    return prevShape;
  }

  private Shape weightedAverageShape(Shape start, Shape end, int tStart, int tEnd, int currTime) {
    return start.getAverageWith(end, tStart, tEnd, currTime);
  }

  @Override
  public List<Motion> getMotions() {
    return new ArrayList<>(motions.stream().toList());
  }

  @Override
  public int getCreationTime() {
    return creationTime;
  }

  @Override
  public int getDeletionTime() {
    return deletionTime;
  }

  @Override
  public void setCreationTime(int time) {
    if (time < 0 || (!motions.isEmpty() && time > motions.first().getStartTime())
            || (deletionTime != -1 && time >= deletionTime)) {
      throw new IllegalArgumentException("Time is not valid!");
    }
    this.creationTime = time;
  }

  @Override
  public void setDeletionTime(int time) {
    if (time < 0 || time <= creationTime
            || (!motions.isEmpty() && motions.last().getEndTime() > time)) {
      throw new IllegalArgumentException("Time is not valid!");
    }
    deletionTime = time;
  }

  @Override
  public void addMotion(Motion motion) {
    if (motion == null) {
      throw new IllegalArgumentException("Motion cannot be null");
    }
    if (creationTime > motion.getStartTime() || (deletionTime != -1 && deletionTime < motion.getEndTime())) {
      throw new IllegalArgumentException("Motion is out of bounds");
    }
    if (motion.getEndShape().getClass() != startShape.getClass()) {
      throw new IllegalArgumentException("New shape must have the same class as the old shape!");
    }
    for (Motion m : motions) {
      if (clamp(m.getStartTime(), m.getEndTime(), motion.getEndTime())
              || clamp(m.getStartTime(), m.getEndTime(), motion.getStartTime())
              || clamp(motion.getStartTime(), motion.getEndTime(), m.getStartTime())
              || clamp(motion.getStartTime(), motion.getEndTime(), m.getEndTime())) {
        throw new IllegalArgumentException("Motion overlaps with current motions");
      }
    }
    motions.add(motion);
  }

  private boolean clamp(int lo, int hi, int value) {
    return lo <= value && value < hi;
  }

  @Override
  public void removeMotion(int time) {
    for (Motion m : motions) {
      if (m.getEndTime() > time) {
        if (m.getStartTime() <= time) {
          motions.remove(m);
          return;
        } else {
          break;
        }
      }
    }
    throw new IllegalArgumentException("No motion can be removed.");
  }

  @Override
  public Shape getStartShape() {
    return startShape;
  }

  @Override
  public void setStartShape(Shape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    if (shape.getClass() != startShape.getClass()) {
      throw new IllegalArgumentException("New shape must have the same class as the old shape!");
    }
    this.startShape = shape;
  }


}
