import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the Color class.
 */
public class TestColor {
  Color c;
  Color badColor;

  /**
   * Initializes color variables to use in test cases.
   */
  public TestColor(){
    c = new Color(10,11,12);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalColor(){
    badColor = new Color(10000, 1, 1);
  }

  @Test
  public void testGetRed(){
    assertEquals(c.getRed(), 10);
  }

  @Test
  public void testGetGreen(){
    assertEquals(c.getGreen(), 11);
  }

  @Test
  public void testGetBlue(){
    assertEquals(c.getBlue(), 12);
  }
}
