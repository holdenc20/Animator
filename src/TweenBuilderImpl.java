import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of the TweenModelBuilder interface that creates an animation of type Animator
 * from a file.
 * TODO: consider validating inputs for addmotion methods.
 */
public class TweenBuilderImpl implements TweenModelBuilder<Animator> {

  private int width;
  private int height;
  private Map<String, AnimatedShape> posShapes;
  private Map<String, AnimatedShape> dimShapes;
  private Map<String, AnimatedShape> colorShapes;

  /**
   * Default constructor for a TweenBuilder that initializes the builder.
   */
  public TweenBuilderImpl() {
    this.width = 0;
    this.height = 0;
    posShapes = new HashMap<>();
    dimShapes = new HashMap<>();
    colorShapes = new HashMap<>();
  }

  @Override
  public TweenModelBuilder<Animator> setBounds(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  @Override
  public TweenModelBuilder<Animator> addOval(String name, float cx, float cy, float xRadius,
                                             float yRadius, float red, float green, float blue,
                                             int startOfLife, int endOfLife) {
    Shape startShape = new Ellipse(new Position((cx - xRadius), (cy - yRadius)),
            2 * xRadius, 2 * yRadius, new Color(red, green, blue));
    createShape(name, startShape, startOfLife, endOfLife);
    return this;
  }

  @Override
  public TweenModelBuilder<Animator> addRectangle(String name, float lx, float ly, float width,
                                                  float height, float red, float green, float blue,
                                                  int startOfLife, int endOfLife) {
    Shape startShape = new Rectangle(new Position(lx, ly), width, height,
            new Color(red, green, blue));
    createShape(name, startShape, startOfLife, endOfLife);
    return null;
  }

  private void createShape(String name, Shape shape, int startTime, int endTime) {
    posShapes.put(name,
            setTimes(new SimpleAnimatedShape(name, onlyPos(shape)), startTime, endTime));
    colorShapes.put(name,
            setTimes(new SimpleAnimatedShape(name, onlyColor(shape)), startTime, endTime));
    dimShapes.put(name,
            setTimes(new SimpleAnimatedShape(name, onlyDim(shape)), startTime, endTime));
  }

  private AnimatedShape setTimes(AnimatedShape aShape, int startTime, int endTime) {
    aShape.setCreationTime(startTime);
    aShape.setDeletionTime(endTime);
    return aShape;
  }

  private Shape onlyColor(Shape shape) {
    if (shape.getShapeType() == ShapeType.ELLIPSE) {
      return new Ellipse(new Position(0, 0), 0, 0, shape.getColor());
    } else {
      return new Rectangle(new Position(0, 0), 0, 0, shape.getColor());
    }
  }

  private Shape onlyPos(Shape shape) {
    if (shape.getShapeType() == ShapeType.ELLIPSE) {
      return new Ellipse(shape.getPosition(), 0, 0, new Color(0, 0, 0));
    } else {
      return new Rectangle(shape.getPosition(), 0, 0, new Color(0, 0, 0));
    }
  }

  private Shape onlyDim(Shape shape) {
    if (shape.getShapeType() == ShapeType.ELLIPSE) {
      return new Ellipse(new Position(0, 0), shape.getWidth(), shape.getHeight(),
              new Color(0, 0, 0));
    } else {
      return new Rectangle(new Position(0, 0), shape.getWidth(), shape.getHeight(),
              new Color(0, 0, 0));
    }
  }

  @Override
  public TweenModelBuilder<Animator> addMove(String name, float moveFromX, float moveFromY,
                                             float moveToX, float moveToY, int startTime,
                                             int endTime) {
    Shape moveTo;
    if (posShapes.get(name).getStartShape().getShapeType() == ShapeType.ELLIPSE) {
      moveTo = new Ellipse(new Position(moveToX, moveToY), 0, 0,
              new Color(0, 0, 0));
    } else {
      moveTo = new Rectangle(new Position(moveToX, moveToY), 0, 0,
              new Color(0, 0, 0));
    }
    posShapes.get(name).addMotion(new Motion(startTime, endTime, moveTo));
    return this;
  }

  @Override
  public TweenModelBuilder<Animator> addColorChange(String name, float oldR, float oldG, float oldB,
                                                    float newR, float newG, float newB,
                                                    int startTime, int endTime) {
    Shape moveTo;
    if (colorShapes.get(name).getStartShape().getShapeType() == ShapeType.ELLIPSE) {
      moveTo = new Ellipse(new Position(0, 0), 0, 0,
              new Color(newR, newG, newB));
    } else {
      moveTo = new Rectangle(new Position(0, 0), 0, 0,
              new Color(newR, newG, newB));
    }
    colorShapes.get(name).addMotion(new Motion(startTime, endTime, moveTo));
    return this;
  }

  @Override
  public TweenModelBuilder<Animator> addScaleToChange(String name, float fromSx, float fromSy,
                                                      float toSx, float toSy, int startTime,
                                                      int endTime) {
    Shape moveTo;
    if (dimShapes.get(name).getStartShape().getShapeType() == ShapeType.ELLIPSE) {
      moveTo = new Ellipse(new Position(0, 0), toSx, toSy,
              new Color(0, 0, 0));
    } else {
      moveTo = new Rectangle(new Position(0, 0), toSx, toSy,
              new Color(0, 0, 0));
    }
    dimShapes.get(name).addMotion(new Motion(startTime, endTime, moveTo));
    return this;
  }

  @Override
  public Animator build() {
    Animator animator = new ShapeAnimator(width, height);
    for (String name : posShapes.keySet()) {
      AnimatedShape combined = combine(posShapes.get(name), colorShapes.get(name),
              dimShapes.get(name));
      addAnimatedShape(animator, combined);
    }
    return animator;
  }

  private void addAnimatedShape(Animator animator, AnimatedShape shape) {
    animator.makeAnimatedShape(shape.getID(), shape.getStartShape());
    animator.setCreationTime(shape.getID(), shape.getCreationTime());
    animator.setDeletionTime(shape.getID(), shape.getDeletionTime());
    for (Motion m : shape.getMotions()) {
      animator.addMotion(shape.getID(), m.getEndShape(), m.getStartTime(), m.getEndTime());
    }
  }

  private AnimatedShape initAnimatedShape(AnimatedShape posShape, AnimatedShape colorShape,
                                          AnimatedShape dimShape) {
    Shape startShape;
    if (posShape.getStartShape().getShapeType() == ShapeType.ELLIPSE) {
      startShape = new Ellipse(posShape.getStartShape().getPosition(),
              dimShape.getStartShape().getWidth(), dimShape.getStartShape().getHeight(),
              colorShape.getStartShape().getColor());
    } else {
      startShape = new Rectangle(posShape.getStartShape().getPosition(),
              dimShape.getStartShape().getWidth(), dimShape.getStartShape().getHeight(),
              colorShape.getStartShape().getColor());
    }
    AnimatedShape animatedShape = new SimpleAnimatedShape(posShape.getID(), startShape);
    animatedShape.setCreationTime(posShape.getCreationTime());
    animatedShape.setDeletionTime(posShape.getDeletionTime());
    return animatedShape;
  }

  private AnimatedShape combine(AnimatedShape posShape, AnimatedShape colorShape,
                                AnimatedShape dimShape) {
    AnimatedShape animatedShape = initAnimatedShape(posShape, colorShape, dimShape);
    List<Integer> times = getNotableTimes(posShape, colorShape, dimShape).stream().toList();
    Collections.sort(times);
    if (times.isEmpty()) {
      return animatedShape;
    }
    int prevTime = 0;
    for (int t : times) {
      animatedShape.addMotion(new Motion(prevTime, t,
              combineAtTime(posShape, colorShape, dimShape, t)));
      prevTime = t;
    }
    return animatedShape;
  }

  private Shape combineAtTime(AnimatedShape posShape, AnimatedShape colorShape,
                              AnimatedShape dimShape, int time) {
    Shape pos = posShape.getShapeAtTime(time);
    Shape color = colorShape.getShapeAtTime(time);
    Shape dim = dimShape.getShapeAtTime(time);
    Shape comb;
    if (pos.getShapeType() == ShapeType.ELLIPSE) {
      return new Ellipse(pos.getPosition(), dim.getWidth(), dim.getHeight(), color.getColor());
    } else {
      return new Rectangle(pos.getPosition(), dim.getWidth(), dim.getHeight(), color.getColor());
    }
  }

  private Set<Integer> getNotableTimes(AnimatedShape s1, AnimatedShape s2, AnimatedShape s3) {
    Set<Integer> times = getNotableTimes(s1);
    times.addAll(getNotableTimes(s2));
    times.addAll(getNotableTimes(s3));
    return times;
  }

  private Set<Integer> getNotableTimes(AnimatedShape s) {
    Set<Integer> times = new HashSet<>();
    for (Motion m : s.getMotions()) {
      times.add(m.getStartTime());
      times.add(m.getEndTime());
    }
    return times;
  }

}
