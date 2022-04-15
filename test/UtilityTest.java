import org.junit.Test;

import model.Utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tester class for the model.Utility class methods.
 */
public class UtilityTest {

  @Test
  public void weightedAverage() {
    assertEquals(Utility.weightedAverage(10, 20, 0, 10, 4), 14, 0.01);
  }

  @Test
  public void clamp() {
    assertTrue(Utility.clamp(10, 20, 15));
    assertFalse(Utility.clamp(10, 20, 25));
  }
}