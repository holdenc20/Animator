import org.junit.Test;

import model.Color;
import model.Motion;
import model.Position;
import model.Rectangle;
import model.Shape;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the model.Motion class.
 */
public class TestMotion {
  private Shape s = new Rectangle(new Position(10, 10), 10, 20, new Color(10, 10, 10));
  private Motion m1;
  private Motion badMotion1;
  private Motion badMotion2;

  /**
   * Initializes motion variables to use in test cases.
   */
  public TestMotion() {
    m1 = new Motion(1, 10, s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalMotion1() {
    badMotion1 = new Motion(-1, 10, s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalMotion2() {
    badMotion2 = new Motion(1, 10, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalMotion3() {
    badMotion2 = new Motion(10, 9, s);
  }

  @Test
  public void testGetStartTime() {
    assertEquals(m1.getStartTime(), 1);
  }

  @Test
  public void testGetEndTime() {
    assertEquals(m1.getEndTime(), 10);
  }

  @Test
  public void testGetEndShape() {
    assertEquals(m1.getEndShape(), s);
  }
}
