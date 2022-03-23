import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the Shape interface. Includes testing for each of the Shape implementations.
 */
public class TestShape {
  Shape rect;
  Shape circ;
  Shape badShape1;
  Shape badShape2;
  Position p = new Position(10, 11);
  Color c = new Color(100, 150, 200);

  /**
   * Initializes Shape objects.
   */
  public TestShape() {
    rect = new Rectangle(p, 20, 30, c);
    circ = new Ellipse(p, 20, 20, c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalShape1() {
    badShape1 = new Rectangle(p, -10, 10, c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalShape2() {
    badShape2 = new Rectangle(p, 10, -10, c);
  }

  @Test
  public void testGetWidth() {
    assertEquals(rect.getWidth(), 20);
  }

  @Test
  public void testGetHeight() {
    assertEquals(rect.getHeight(), 30);
  }

  @Test
  public void testGetColor() {
    assertEquals(rect.getColor(), c);
  }

  @Test
  public void testGetPosition() {
    assertEquals(rect.getPosition(), p);
  }

  @Test
  public void testGetShapeType() {
    assertEquals(ShapeType.RECTANGLE, rect.getShapeType());
    assertEquals(ShapeType.ELLIPSE, circ.getShapeType());
  }

  @Test
  public void testToString() {
    assertEquals("(10, 11) 20 30 (100, 150, 200)", rect.toString());
    assertEquals("(10, 11) 20 20 (100, 150, 200)", circ.toString());
  }
}
