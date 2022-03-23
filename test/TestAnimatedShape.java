import org.junit.Test;

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
    fail = new SimpleAnimatedShape("Rect1", s1);
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

  @Test
  public void testGetShapeAtTime() {
    assertEquals(as.getID(), "Rect1");
  }
}
