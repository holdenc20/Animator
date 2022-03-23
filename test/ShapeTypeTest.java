import org.junit.Test;

import static org.junit.Assert.*;

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