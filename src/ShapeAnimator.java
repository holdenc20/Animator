import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of the Animator.
 */
public class ShapeAnimator implements Animator {

  private final Map<String, AnimatedShape> shapes;

  /**
   * Constructor for the ShapeAnimator class.
   */
  public ShapeAnimator() {
    this.shapes = new HashMap<String, AnimatedShape>();
  }


  @Override
  public void makeShape(String shapeID, Shape s) {
    if (shapes.containsKey(shapeID)) {
      throw new IllegalArgumentException("This shape already exists");
    }
    shapes.put(shapeID, new SimpleAnimatedShape(shapeID, s));
  }

  @Override
  public void addMotion(String shapeID, Shape target, int initialTime, int endTime) {
    if (!shapes.containsKey(shapeID)) {
      throw new IllegalArgumentException("ShapeID does not exist!");
    }
    if (!shapes.get(shapeID).getClass().equals(target.getClass())) {
      throw new IllegalArgumentException("The target has to be the same shape type!");
    }
    if (initialTime < 0) {
      throw new IllegalArgumentException("Time cannot be negative!");
    }
    if (initialTime > endTime) {
      throw new IllegalArgumentException("The ending time has to be " +
              "greater or equals to the starting time!");
    }
    Motion toAdd = new Motion(initialTime, endTime, target);
    shapes.get(shapeID).addMotion(toAdd);
  }

  @Override
  public void removeMotion(String shapeID, int initialTime) {
    //TODO: implement removeMotion
  }

  @Override
  public Shape getShapeAtTime(String shapeID, int time) {
    return null;
    //TODO: implement getShapeAtTime
  }

}
