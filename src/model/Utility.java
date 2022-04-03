package model;

/**
 * A utility class used for mathematical functions that are used in various locations.
 */
public class Utility {

  /**
   * Determines the weighted average between two integer values over a given time.
   *
   * @param start    the starting value.
   * @param end      the ending value.
   * @param tStart   the starting time.
   * @param tEnd     the ending time.
   * @param currTime the time of the average.
   * @return The calculated weighted average.
   */
  public static float weightedAverage(float start, float end, int tStart, int tEnd, int currTime) {
    return (end - start) * (currTime - tStart) / (tEnd - tStart) + start;
  }

  /**
   * Determines if the given value is between the lo and hi bounds.
   *
   * @param lo    the lower bound for comparison.
   * @param hi    the upper bound for comparison.
   * @param value the value to determine if it is in the bounds.
   * @return
   */
  public static boolean clamp(int lo, int hi, int value) {
    return lo < value && value < hi;
  }

}
