import org.junit.Test;

import model.Utility;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the model.Utility class methods.
 */
public class UtilityTest {

  @Test
  public void weightedAverage() {
    assertEquals(Utility.weightedAverage(10, 20, 0, 10, 4), 14);
  }

  @Test
  public void clamp() {
    assertEquals(Utility.clamp(10, 20, 15), true);
    assertEquals(Utility.clamp(10, 20, 25), false);
  }
}