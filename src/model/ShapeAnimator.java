package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.AnimatedShape;
import model.Animator;
import model.Motion;
import model.Shape;

/**
 * An implementation of the view.Animator. This implementation uses a Map of string IDs to AnimatedShapes
 * in order to store information. Otherwise, this is a straightforward implementation to the
 * view.Animator interface.
 */
public class ShapeAnimator implements Animator {

  private final Map<String, AnimatedShape> shapes;
  private int animationWidth;
  private int animationHeight;

  /**
   * Default constructor for the model.ShapeAnimator class.
   */
  public ShapeAnimator() {
    this.shapes = new LinkedHashMap<String, AnimatedShape>();
    this.animationWidth = 0;
    this.animationHeight = 0;
  }

  /**
   * Constructor for the model.ShapeAnimator class.
   */
  public ShapeAnimator(int width, int height) {
    this.shapes = new LinkedHashMap<String, AnimatedShape>();
    this.animationWidth = width;
    this.animationHeight = height;
  }


  @Override
  public void makeAnimatedShape(String shapeID, Shape s) {
    if (shapes.containsKey(shapeID)) {
      throw new IllegalArgumentException("This shape already exists");
    }
    shapes.put(shapeID, new SimpleAnimatedShape(shapeID, s));
  }

  @Override
  public SimpleAnimatedShape getAnimatedShape(String shapeID) {
    AnimatedShape animatedShape = getFromID(shapeID);
    return new SimpleAnimatedShape(animatedShape);
  }

  @Override
  public List<String> getShapeIDs() {
    return new ArrayList<>();
  }

  @Override
  public void deleteShape(String shapeID) {
    if (!shapes.containsKey(shapeID)) {
      throw new IllegalArgumentException("ShapeID does not exist!");
    }
    shapes.remove(shapeID);
  }

  private AnimatedShape getFromID(String id) {
    if (!shapes.containsKey(id)) {
      throw new IllegalArgumentException("ShapeID does not exist!");
    }
    return shapes.get(id);
  }

  @Override
  public void addMotion(String shapeID, Shape target, int initialTime, int endTime) {
    AnimatedShape animatedShape = getFromID(shapeID);
    Motion toAdd = new Motion(initialTime, endTime, target);
    animatedShape.addMotion(toAdd);
  }

  @Override
  public void removeMotion(String shapeID, int initialTime) {
    AnimatedShape animatedShape = getFromID(shapeID);
    animatedShape.removeMotion(initialTime);
  }

  @Override
  public void setStartShape(String shapeID, Shape shape) {
    AnimatedShape animatedShape = getFromID(shapeID);
    animatedShape.setStartShape(shape);
  }

  @Override
  public void setCreationTime(String shapeID, int time) {
    AnimatedShape animatedShape = getFromID(shapeID);
    animatedShape.setCreationTime(time);
  }

  @Override
  public void setDeletionTime(String shapeID, int time) {
    AnimatedShape animatedShape = getFromID(shapeID);
    animatedShape.setDeletionTime(time);
  }

  @Override
  public void setDimensions(int width, int heigth) {
    this.animationWidth = width;
    this.animationHeight = heigth;
  }

  @Override
  public int getWidth() {
    return this.animationWidth;
  }

  @Override
  public int getHeight() {
    return this.animationHeight;
  }

  @Override
  public Shape getShapeAtTime(String shapeID, int time) {
    AnimatedShape animatedShape = getFromID(shapeID);
    return animatedShape.getShapeAtTime(time);
  }
}
