import org.junit.Test;

import java.util.ArrayList;

import model.Animator;
import model.Color;
import model.Ellipse;
import model.Motion;
import model.Position;
import model.Rectangle;
import model.Shape;
import model.ShapeAnimator;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the Animator interface. Tests on the SimpleAnimator implementation.
 */
public class TestAnimator {
  private Animator a;
  private Position p = new Position(10, 10);
  private Position p2 = new Position(20, 30);
  private Color c = new Color(30, 40, 50);
  private Shape s1 = new Rectangle(p, 10, 20, c);
  private Shape s1dest = new Rectangle(p2, 50, 100, c);
  private Shape s2 = new Rectangle(p, 100, 200, c);
  private Shape s1destBad = new Ellipse(p2, 50, 100, c);

  /**
   * Initializes a ShapeAnimator that will be used in the test cases.
   */
  public TestAnimator() {
    a = new ShapeAnimator(300, 300);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeInvalidShape() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect1", s2);
  }

  @Test
  public void makeShape() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    assertEquals(a.getAnimatedShape("Rect1").getCreationTime(), 0);
    assertEquals(a.getAnimatedShape("Rect1").getDeletionTime(), -1);
    assertEquals(a.getAnimatedShape("Rect1").getID(), "Rect1");
    assertEquals(a.getAnimatedShape("Rect1").getMotions(), new ArrayList<Motion>());
    assertEquals(a.getAnimatedShape("Rect2").getCreationTime(), 0);
    assertEquals(a.getAnimatedShape("Rect2").getDeletionTime(), -1);
    assertEquals(a.getAnimatedShape("Rect2").getID(), "Rect2");
    assertEquals(a.getAnimatedShape("Rect2").getMotions(), new ArrayList<Motion>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionBadShapeID() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect600", s1dest, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionDifferentTypeShape() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect1", s1destBad, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionTimeLessThanZero() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect1", s1dest, -1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionTimesOutOfOrder() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect1", s1dest, 10, 1);
  }

  @Test
  public void addMotion() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    assertEquals(a.getAnimatedShape("Rect1").getCreationTime(), 0);
    assertEquals(a.getAnimatedShape("Rect1").getDeletionTime(), -1);
    assertEquals(a.getAnimatedShape("Rect1").getID(), "Rect1");
    assertEquals(a.getAnimatedShape("Rect1").getMotions(), new ArrayList<Motion>());

    a.addMotion("Rect1", s1dest, 0, 10);

    Shape s5 = a.getShapeAtTime("Rect1", 5);
    assertEquals(s5.getPosition().getX(), 15, 0.01);
    assertEquals(s5.getPosition().getY(), 20, 0.01);
    assertEquals(s5.getWidth(), 30, 0.01);
    assertEquals(s5.getHeight(), 60, 0.01);
    assertEquals(s5.getColor().getRed(), 30, 0.01);
    assertEquals(s5.getColor().getGreen(), 40, 0.01);
    assertEquals(s5.getColor().getBlue(), 50, 0.01);

    a.addMotion("Rect1", s1, 12, 22);

    Shape s17 = a.getShapeAtTime("Rect1", 17);
    assertEquals(s17.getPosition().getX(), 15, 0.01);
    assertEquals(s17.getPosition().getY(), 20, 0.01);
    assertEquals(s17.getWidth(), 30, 0.01);
    assertEquals(s17.getHeight(), 60, 0.01);
    assertEquals(s17.getColor().getRed(), 30, 0.01);
    assertEquals(s17.getColor().getGreen(), 40, 0.01);
    assertEquals(s17.getColor().getBlue(), 50, 0.01);
  }

  @Test
  public void removeMotion() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    assertEquals(a.getAnimatedShape("Rect1").getCreationTime(), 0);
    assertEquals(a.getAnimatedShape("Rect1").getDeletionTime(), -1);
    assertEquals(a.getAnimatedShape("Rect1").getID(), "Rect1");
    assertEquals(a.getAnimatedShape("Rect1").getMotions(), new ArrayList<Motion>());

    a.addMotion("Rect1", s1dest, 0, 10);

    Shape s5 = a.getShapeAtTime("Rect1", 5);
    assertEquals(s5.getPosition().getX(), 15, 0.01);
    assertEquals(s5.getPosition().getY(), 20, 0.01);
    assertEquals(s5.getWidth(), 30, 0.01);
    assertEquals(s5.getHeight(), 60, 0.01);
    assertEquals(s5.getColor().getRed(), 30, 0.01);
    assertEquals(s5.getColor().getGreen(), 40, 0.01);
    assertEquals(s5.getColor().getBlue(), 50, 0.01);

    a.removeMotion("Rect1", 3);

    Shape s52 = a.getShapeAtTime("Rect1", 5);
    assertEquals(s52.getPosition().getX(), 10, 0.01);
    assertEquals(s52.getPosition().getY(), 10, 0.01);
    assertEquals(s52.getWidth(), 10, 0.01);
    assertEquals(s52.getHeight(), 20, 0.01);
    assertEquals(s52.getColor().getRed(), 30, 0.01);
    assertEquals(s52.getColor().getGreen(), 40, 0.01);
    assertEquals(s52.getColor().getBlue(), 50, 0.01);
  }

  @Test
  public void setStartShape() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect1", s1dest, 0, 10);
    a.setStartShape("Rect1", s2);

    Shape s5 = a.getShapeAtTime("Rect1", 5);
    assertEquals(s5.getPosition().getX(), 15, 0.01);
    assertEquals(s5.getPosition().getY(), 20, 0.01);
    assertEquals(s5.getWidth(), 75, 0.01);
    assertEquals(s5.getHeight(), 150, 0.01);
    assertEquals(s5.getColor().getRed(), 30, 0.01);
    assertEquals(s5.getColor().getGreen(), 40, 0.01);
    assertEquals(s5.getColor().getBlue(), 50, 0.01);
  }

  @Test
  public void setCreationTime() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect1", s1dest, 10, 20);
    a.setCreationTime("Rect1", 10);

    Shape s5 = a.getShapeAtTime("Rect1", 15);
    assertEquals(s5.getPosition().getX(), 15, 0.01);
    assertEquals(s5.getPosition().getY(), 20, 0.01);
    assertEquals(s5.getWidth(), 30, 0.01);
    assertEquals(s5.getHeight(), 60, 0.01);
    assertEquals(s5.getColor().getRed(), 30, 0.01);
    assertEquals(s5.getColor().getGreen(), 40, 0.01);
    assertEquals(s5.getColor().getBlue(), 50, 0.01);
  }

  @Test
  public void setDeletionTime() {
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect1", s1dest, 10, 20);
    a.setDeletionTime("Rect1", 20);
    assertEquals(a.getAnimatedShape("Rect1").getDeletionTime(), 20);
    Shape s5 = a.getShapeAtTime("Rect1", 15);
    assertEquals(s5.getPosition().getX(), 15, 0.01);
    assertEquals(s5.getPosition().getY(), 20, 0.01);
    assertEquals(s5.getWidth(), 30, 0.01);
    assertEquals(s5.getHeight(), 60, 0.01);
    assertEquals(s5.getColor().getRed(), 30, 0.01);
    assertEquals(s5.getColor().getGreen(), 40, 0.01);
    assertEquals(s5.getColor().getBlue(), 50, 0.01);
  }
}
