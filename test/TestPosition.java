import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the Position class.
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
  public void testGetX(){
    assertEquals(p.getX(), 10);
  }

  @Test
  public void testGetY(){
    assertEquals(p.getY(), 11);
  }
}