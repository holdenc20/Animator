import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the Motion class.
 */
public class TestMotion {
  Shape s = new Rectangle(new Position(10,10), 10, 20, new Color(10,10,10));
  Motion m1;
  Motion badMotion1;
  Motion badMotion2;

  /**
   * Initializes motion variables to use in test cases.
   */
  public TestMotion(){
    m1 = new Motion(1, 10, s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalMotion1(){
    badMotion1 = new Motion(-1, 10, s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateIllegalMotion2(){
    badMotion2 = new Motion(1, 10, null);
  }

  @Test
  public void testGetStartTime(){
    assertEquals(m1.getStartTime(), 1);
  }

  @Test
  public void testGetEndTime(){
    assertEquals(m1.getEndTime(), 10);
  }

  @Test
  public void testGetEndShape(){
    assertEquals(m1.getEndShape(), s);
  }
}
