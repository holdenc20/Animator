import org.junit.Test;

import model.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tester class for the Color class.
 */
public class TestColor {
  private Color c;
  private Color badColor;

  /**
   * Initializes color variables to use in test cases.
   */
  public TestColor() {
    c = new Color(10, 11, 12);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalColor() {
    badColor = new Color(10000, 1, 1);
  }

  private boolean doesFailCreation(int r, int g, int b) {
    try {
      Color testColor = new Color(r, g, b);
      return false;
    } catch (IllegalArgumentException e) {
      return true;
    }
  }

  @Test
  public void testCreation() {
    assertTrue(doesFailCreation(-1, 0, 0));
    assertTrue(doesFailCreation(0, -1, 0));
    assertTrue(doesFailCreation(0, 0, -1));
    assertTrue(doesFailCreation(0, 0, 256));
    assertTrue(doesFailCreation(0, 256, 0));
    assertTrue(doesFailCreation(256, 0, 0));

    assertFalse(doesFailCreation(0, 0, 0));
    assertFalse(doesFailCreation(255, 255, 255));
    assertFalse(doesFailCreation(207, 84, 103));
  }

  @Test
  public void testGetRed() {
    assertEquals(c.getRed(), 10, 0.01);
  }

  @Test
  public void testGetGreen() {
    assertEquals(c.getGreen(), 11, 0.01);
  }

  @Test
  public void testGetBlue() {
    assertEquals(c.getBlue(), 12, 0.01);
  }

  @Test
  public void testToString() {
    assertEquals("(10.0, 11.0, 12.0)", c.toString());
  }
}
