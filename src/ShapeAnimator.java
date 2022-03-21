import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of the Animator.
 */
public class ShapeAnimator implements Animator {

  private final Map<String, ArrayList<Motion>> motions;

  /**
   * Constructor for the ShapeAnimator class.
   */
  public ShapeAnimator() {
    this.motions = new HashMap<String, ArrayList<Motion>>();
  }


  @Override
  public void makeShape(Shape s) {
    if(motions.containsKey(s.getID())){
      throw new IllegalArgumentException("This shape already exists");
    }
    ArrayList<Motion> temp = new ArrayList<Motion>();
    Motion starting = new Motion(0, 0, s);
    temp.add(starting);
    motions.put(s.getID(), temp);
  }

  @Override
  public void addMotion(String shapeID, Shape target, int initialTime, int endTime) {
    if(!motions.containsKey(shapeID)){
      throw new IllegalArgumentException("ShapeID does not exist!");
    }
    if(!motions.get(shapeID).getClass().equals(target.getClass())){
      throw new IllegalArgumentException("The target has to be the same shape type!");
    }
    if(initialTime < 0){
      throw new IllegalArgumentException("Time cannot be negative!");
    }
    if(initialTime > endTime){
      throw new IllegalArgumentException("The ending time has to be " +
              "greater or equals to the starting time!");
    }
    //Checking for time overlap
    for(Motion m : motions.get(shapeID)){
      if(initialTime > m.getStartTime() && initialTime < m.getEndTime()){
        throw new IllegalArgumentException("Initial time cannot be " +
                "within other motions of this shape!");
      }
      if(endTime > m.getStartTime() && endTime < m.getEndTime()){
        throw new IllegalArgumentException("End time cannot be " +
                "within other motions of this shape!");
      }
      if(m.getStartTime() > initialTime && m.getStartTime() < endTime){
        throw new IllegalArgumentException("Other motions cannot be within this motion!");
      }
    }
    Motion temp = new Motion(initialTime, endTime, target);
    motions.get(shapeID).add(temp);
  }
}
