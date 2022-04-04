import org.junit.Test;

import model.AnimatedShape;
import model.Color;
import model.Ellipse;
import model.Motion;
import model.Position;
import model.Rectangle;
import model.Shape;
import model.SimpleAnimatedShape;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the Animated Shape interface. Tests on the SimpleAnimatedShape implementation.
 */
public class TestAnimatedShape {
  AnimatedShape as;
  AnimatedShape fail;
  Position p = new Position(10, 10);
  Color c = new Color(30, 40, 50);
  Shape s1 = new Rectangle(p, 10, 20, c);

  /**
   * Initializes the AnimatedShapes that will be used in the test cases.
   */
  public TestAnimatedShape() {
    as = new SimpleAnimatedShape("Rect1", s1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInitializingInvalidAnimatedShape1() {
    fail = new SimpleAnimatedShape("", s1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInitializingInvalidAnimatedShape2() {
    fail = new SimpleAnimatedShape(null, s1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInitializingInvalidAnimatedShape3() {
    fail = new SimpleAnimatedShape("Something", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInitializingCopyConstructor() {
    fail = new SimpleAnimatedShape(null);
  }

  @Test
  public void testGetID() {
    assertEquals(as.getID(), "Rect1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeAtTimeInvalidTime() {
    as.getShapeAtTime(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeAtTimeInvalidTime1() {
    as.setDeletionTime(20);
    as.getShapeAtTime(21);
  }

  @Test
  public void testGetShapeAtTime() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(1, 11, endShape1);
    as.addMotion(temp);
    Position p1 = new Position(20, 30);
    Shape endShape2 = new Rectangle(p1, 100, 200, c);
    Motion temp2 = new Motion(12, 22, endShape2);
    as.addMotion(temp2);
    assertEquals(as.getShapeAtTime(2).getPosition().getX(), p.getX(), 0.01);
    assertEquals(as.getShapeAtTime(2).getPosition().getY(), p.getY(), 0.01);
    assertEquals(as.getShapeAtTime(2).getWidth(), 19, 0.01);
    assertEquals(as.getShapeAtTime(2).getHeight(), 38, 0.01);
    assertEquals(as.getShapeAtTime(2).getColor().getRed(), c.getRed(), 0.01);
    assertEquals(as.getShapeAtTime(2).getColor().getBlue(), c.getBlue(), 0.01);
    assertEquals(as.getShapeAtTime(2).getColor().getGreen(), c.getGreen(), 0.01);

    assertEquals(as.getShapeAtTime(20).getPosition().getX(), 18, 0.01);
    assertEquals(as.getShapeAtTime(20).getPosition().getY(), 26, 0.01);
    assertEquals(as.getShapeAtTime(20).getWidth(), 100, 0.01);
    assertEquals(as.getShapeAtTime(20).getHeight(), 200, 0.01);
    assertEquals(as.getShapeAtTime(20).getColor().getRed(), c.getRed(), 0.01);
    assertEquals(as.getShapeAtTime(20).getColor().getBlue(), c.getBlue(), 0.01);
    assertEquals(as.getShapeAtTime(20).getColor().getGreen(), c.getGreen(), 0.01);
  }

  @Test
  public void testGetMotions() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(1, 11, endShape1);
    as.addMotion(temp);
    Position p1 = new Position(20, 30);
    Shape endShape2 = new Rectangle(p1, 100, 200, c);
    Motion temp2 = new Motion(12, 22, endShape2);
    as.addMotion(temp2);
    assertEquals(as.getMotions().get(0), temp);
    assertEquals(as.getMotions().get(1), temp2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadDeletionTime() {
    as.setDeletionTime(-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadDeletionTime2() {
    as.setCreationTime(10);
    as.setDeletionTime(5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadCreationTime() {
    as.setDeletionTime(10);
    as.setCreationTime(20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadCreationTime2() {
    as.setCreationTime(-10);
  }

  @Test
  public void testCreationAndDeletionTime() {
    assertEquals(as.getCreationTime(), 0);
    assertEquals(as.getDeletionTime(), -1);

    as.setCreationTime(20);
    as.setDeletionTime(22);

    assertEquals(as.getCreationTime(), 20);
    assertEquals(as.getDeletionTime(), 22);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddBadMotion() {
    as.addMotion(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddBadMotion2() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(5, 26, endShape1);
    as.setDeletionTime(20);
    as.addMotion(temp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddBadMotion3() {
    Shape endShape1 = new Ellipse(p, 100, 200, c);
    Motion temp = new Motion(5, 26, endShape1);
    as.addMotion(temp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddBadMotion4() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(5, 26, endShape1);
    as.addMotion(temp);

    Shape endShape2 = new Rectangle(p, 100, 200, c);
    Motion temp2 = new Motion(4, 6, endShape2);
    as.addMotion(temp2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddBadMotion5() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(5, 26, endShape1);
    as.addMotion(temp);

    Shape endShape2 = new Rectangle(p, 100, 200, c);
    Motion temp2 = new Motion(25, 30, endShape2);
    as.addMotion(temp2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddBadMotion6() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(5, 26, endShape1);
    as.addMotion(temp);

    Shape endShape2 = new Rectangle(p, 100, 200, c);
    Motion temp2 = new Motion(7, 8, endShape2);
    as.addMotion(temp2);
  }

  @Test
  public void testAddAndRemoveMotion() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(5, 26, endShape1);
    as.addMotion(temp);

    assertEquals(as.getMotions().get(0).getStartTime(), 5);
    assertEquals(as.getMotions().get(0).getEndTime(), 26);
    assertEquals(as.getMotions().get(0).getEndShape().getPosition().getX(), p.getX(), 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getPosition().getY(), p.getY(), 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getWidth(), 100, 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getHeight(), 200, 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getColor().getRed(), c.getRed(), 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getColor().getGreen(), c.getGreen(), 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getColor().getBlue(), c.getBlue(), 0.01);

    Shape endShape2 = new Rectangle(p, 100, 200, c);
    Motion temp2 = new Motion(28, 40, endShape2);
    as.addMotion(temp2);

    as.removeMotion(6);
    assertEquals(as.getMotions().get(0).getStartTime(), 28, 0.01);
    assertEquals(as.getMotions().get(0).getEndTime(), 40, 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getPosition().getX(), p.getX(), 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getPosition().getY(), p.getY(), 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getWidth(), 100, 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getHeight(), 200, 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getColor().getRed(), c.getRed(), 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getColor().getGreen(), c.getGreen(), 0.01);
    assertEquals(as.getMotions().get(0).getEndShape().getColor().getBlue(), c.getBlue(), 0.01);
  }

  @Test
  public void testGetStartShape() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(5, 26, endShape1);
    as.addMotion(temp);

    as.getStartShape();
    assertEquals(as.getStartShape().getPosition().getX(), p.getX(), 0.01);
    assertEquals(as.getStartShape().getPosition().getY(), p.getY(), 0.01);
    assertEquals(as.getStartShape().getWidth(), 10, 0.01);
    assertEquals(as.getStartShape().getHeight(), 20, 0.01);
    assertEquals(as.getStartShape().getColor().getRed(), c.getRed(), 0.01);
    assertEquals(as.getStartShape().getColor().getGreen(), c.getGreen(), 0.01);
    assertEquals(as.getStartShape().getColor().getBlue(), c.getBlue(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBadStartShape() {
    as.setStartShape(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBadStartShape2() {
    Shape startShape2 = new Ellipse(p, 100, 200, c);
    as.setStartShape(startShape2);
  }

  @Test
  public void testSetStartShape() {
    Shape endShape1 = new Rectangle(p, 100, 200, c);
    Motion temp = new Motion(5, 26, endShape1);
    as.addMotion(temp);

    as.getStartShape();
    assertEquals(as.getStartShape().getPosition().getX(), p.getX(), 0.01);
    assertEquals(as.getStartShape().getPosition().getY(), p.getY(), 0.01);
    assertEquals(as.getStartShape().getWidth(), 10, 0.01);
    assertEquals(as.getStartShape().getHeight(), 20, 0.01);
    assertEquals(as.getStartShape().getColor().getRed(), c.getRed(), 0.01);
    assertEquals(as.getStartShape().getColor().getGreen(), c.getGreen(), 0.01);
    assertEquals(as.getStartShape().getColor().getBlue(), c.getBlue(), 0.01);

    Shape startShape2 = new Rectangle(p, 200, 300, c);
    as.setStartShape(startShape2);
    assertEquals(as.getStartShape().getPosition().getX(), p.getX(), 0.01);
    assertEquals(as.getStartShape().getPosition().getY(), p.getY(), 0.01);
    assertEquals(as.getStartShape().getWidth(), 200, 0.01);
    assertEquals(as.getStartShape().getHeight(), 300, 0.01);
    assertEquals(as.getStartShape().getColor().getRed(), c.getRed(), 0.01);
    assertEquals(as.getStartShape().getColor().getGreen(), c.getGreen(), 0.01);
    assertEquals(as.getStartShape().getColor().getBlue(), c.getBlue(), 0.01);
  }
}
