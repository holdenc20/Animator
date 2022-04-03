import org.junit.Test;

import model.Position;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the model.Position class.
 */
public class TestPosition {
  Position p;

  /**
   * Initializes position variables to use in test cases.
   */
  public TestPosition() {
    p = new Position(10, 11);
  }

  @Test
  public void testGetX() {
    assertEquals(p.getX(), 10);
  }

  @Test
  public void testGetY() {
    assertEquals(p.getY(), 11);
  }

  @Test
  public void testToString() {
    assertEquals("(10, 11)", p.toString());
  }
}
