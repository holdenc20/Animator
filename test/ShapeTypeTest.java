import org.junit.Test;

import model.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the ShapeType enumeration.
 */
public class ShapeTypeTest {

  @Test
  public void testToString() {
    ShapeType rect = ShapeType.RECTANGLE;
    ShapeType circ = ShapeType.ELLIPSE;
    assertEquals(rect.toString(), "Rectangle");
    assertEquals(circ.toString(), "Ellipse");
  }
}