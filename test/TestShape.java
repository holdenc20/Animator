import org.junit.Test;

import model.Color;
import model.Ellipse;
import model.Position;
import model.Rectangle;
import model.Shape;
import model.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the Shape interface. Includes testing for each of the Shape implementations.
 */
public class TestShape {
  private Shape rect;
  private Shape circ;
  private Position p = new Position(10, 11);
  private Color c = new Color(100, 150, 200);

  /**
   * Initializes model.Shape objects.
   */
  public TestShape() {
    rect = new Rectangle(p, 20, 30, c);
    circ = new Ellipse(p, 20, 20, c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalShape1() {
    new Rectangle(p, -10, 10, c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalShape2() {
    new Rectangle(p, 10, -10, c);
  }

  @Test
  public void testGetWidth() {
    assertEquals(rect.getWidth(), 20, 0.01);
  }

  @Test
  public void testGetHeight() {
    assertEquals(rect.getHeight(), 30, 0.01);
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
    assertEquals("(10.0, 11.0) 20.0 30.0 (100.0, 150.0, 200.0)", rect.toString());
    assertEquals("(10.0, 11.0) 20.0 20.0 (100.0, 150.0, 200.0)", circ.toString());
  }
}
