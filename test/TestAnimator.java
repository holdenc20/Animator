import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestAnimator {
  Animator a;
  Position p = new Position(10,10);
  Position p2 = new Position(20,30);
  Color c = new Color(30,40,50);
  Shape s1 = new Rectangle(p, 10, 20, c);
  Shape s1dest = new Rectangle(p2, 50, 100, c);
  Shape s2 = new Rectangle(p, 100, 200, c);
  Shape s1destBad = new Ellipse(p2, 50, 100, c);

  public TestAnimator(){
    a = new ShapeAnimator();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeInvalidShape(){
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
  public void testAddMotionBadShapeID(){
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect600", s1dest, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionDifferentTypeShape(){
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect1", s1destBad, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionTimeLessThanZero(){
    a.makeAnimatedShape("Rect1", s1);
    a.makeAnimatedShape("Rect2", s2);
    a.addMotion("Rect1", s1dest, -1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionTimesOutOfOrder(){
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
    assertEquals(s5.getPosition().getX(), 15);
    assertEquals(s5.getPosition().getY(), 20);
    assertEquals(s5.getWidth(), 30);
    assertEquals(s5.getHeight(), 60);
    assertEquals(s5.getColor().getRed(), 30);
    assertEquals(s5.getColor().getGreen(), 40);
    assertEquals(s5.getColor().getBlue(), 50);

    a.addMotion("Rect1", s1, 12, 22);

    Shape s17 = a.getShapeAtTime("Rect1", 17);
    assertEquals(s17.getPosition().getX(), 15);
    assertEquals(s17.getPosition().getY(), 20);
    assertEquals(s17.getWidth(), 30);
    assertEquals(s17.getHeight(), 60);
    assertEquals(s17.getColor().getRed(), 30);
    assertEquals(s17.getColor().getGreen(), 40);
    assertEquals(s17.getColor().getBlue(), 50);
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
    assertEquals(s5.getPosition().getX(), 15);
    assertEquals(s5.getPosition().getY(), 20);
    assertEquals(s5.getWidth(), 30);
    assertEquals(s5.getHeight(), 60);
    assertEquals(s5.getColor().getRed(), 30);
    assertEquals(s5.getColor().getGreen(), 40);
    assertEquals(s5.getColor().getBlue(), 50);

    a.removeMotion("Rect1", 3);

    Shape s52 = a.getShapeAtTime("Rect1", 5);
    assertEquals(s52.getPosition().getX(), 10);
    assertEquals(s52.getPosition().getY(), 10);
    assertEquals(s52.getWidth(), 10);
    assertEquals(s52.getHeight(), 20);
    assertEquals(s52.getColor().getRed(), 30);
    assertEquals(s52.getColor().getGreen(), 40);
    assertEquals(s52.getColor().getBlue(), 50);
  }

  @Test
  public void getShapeAtTime() {
  }
}
