package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class to create a BubbleSort animation.
 */
public class BubbleSortCreator {

  /**
   * Main method to create the Bubblesort animation and add it to the proper file.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    new BubbleSortCreator();
  }

  private int numBars = 10;
  private int width = 500;
  private int height = 500;

  private List<int[]> swaps;
  private int[] startArray;

  private int swapTime = 4;

  private String fileOutputPath = "resources/bubblesort.txt";

  /**
   * Creates a BubbleSortAnimation and writes it to the file.
   */
  public BubbleSortCreator() {
    swaps = new ArrayList<>();
    initArray();
    getSwaps();
    writeAnimation();
  }

  private void writeAnimation() {
    try {
      FileWriter writer = new FileWriter(fileOutputPath);
      writer.write(toString());
      writer.flush();
      writer.close();
    } catch (IOException e) {
      System.out.println("IOException");
      e.printStackTrace();
    }
  }

  private void initArray() {
    startArray = new int[numBars];
    List<Integer> l = new ArrayList<>();
    for (int i = 1; i <= numBars; i++) {
      l.add(i);
    }
    Collections.shuffle(l);
    for (int i = 0; i < numBars; i++) {
      startArray[i] = l.get(i);
    }
  }

  private void getSwaps() {
    int[] arr = copyArr();
    for (int i = numBars - 2; i >= 0; i--) {
      for (int j = 0; j <= i; j++) {
        if (arr[j] > arr[j + 1]) {
          int[] a = {j, j + 1};
          swaps.add(a);
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }

  private int[] copyArr() {
    int[] cpy = new int[numBars];
    for (int i = 0; i < numBars; i++) {
      cpy[i] = startArray[i];
    }
    return cpy;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    createShapes(builder);
    addSwaps(builder);
    return builder.toString();
  }

  private void createShapes(StringBuilder builder) {
    builder.append(String.format("canvas %d %d\n", width, height));
    for (int i = 0; i < numBars; i++) {
      builder.append(String.format("rectangle name R%d min-x %d min-y %d width %d height " +
                      "%d color 0 1 0 from 0 to %d\n", startArray[i], i * width / numBars, 0,
              width / numBars,
              startArray[i] * (height / numBars), swaps.size() * swapTime + 4 * swapTime));
    }
  }

  private void addSwaps(StringBuilder builder) {
    int[] arr = copyArr();
    for (int i = 0; i < swaps.size(); i++) {
      //Change color
      int[] curr = swaps.get(i);
      builder.append(String.format("change-color name R%d colorto 0 1 0 1 0 0 from %d to %d\n",
              arr[curr[0]], swapTime * i, swapTime * i + 1));
      builder.append(String.format("change-color name R%d colorto 0 1 0 1 0 0 from %d to %d\n",
              arr[curr[1]], swapTime * i, swapTime * i + 1));
      builder.append(String.format("change-color name R%d colorto 1 0 0 0 1 0 from %d to %d\n",
              arr[curr[0]], swapTime * i + swapTime - 1, swapTime * i + swapTime));
      builder.append(String.format("change-color name R%d colorto 1 0 0 0 1 0 from %d to %d\n",
              arr[curr[1]], swapTime * i + swapTime - 1, swapTime * i + swapTime));
      //Swap positions
      builder.append(String.format("move name R%d moveto %d 0 %d 0 from %d to %d\n",
              arr[curr[0]], curr[0] * width / numBars, curr[1] * width / numBars, swapTime * i,
              swapTime * i + swapTime));
      builder.append(String.format("move name R%d moveto %d 0 %d 0 from %d to %d\n",
              arr[curr[1]], curr[1] * width / numBars, curr[0] * width / numBars, swapTime * i,
              swapTime * i + swapTime));
      int temp = arr[curr[0]];
      arr[curr[0]] = arr[curr[1]];
      arr[curr[1]] = temp;
    }
  }
}
