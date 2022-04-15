package model;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Tester class for the BubbleSortCreator.
 */
public class BubbleSortCreatorTest {

  private List<String> lines;

  /**
   * Constructor for the tester that sets initial values.
   */
  public BubbleSortCreatorTest() {
    new BubbleSortCreator();
    Scanner reader = null;
    try {
      reader = new Scanner(new File("resources/bubblesort.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }
    lines = new ArrayList<>();
    while (reader.hasNextLine()) {
      lines.add(reader.nextLine());
    }
  }

  @Test
  public void testSize() {
    assertTrue(lines.size() >= 11);
    assertEquals(0, (lines.size() - 11) % 6); //11 initial lines, 6 lines per move.
  }

  @Test
  public void testCanvas() {
    assertEquals("canvas 500 500", lines.get(0));
  }

  @Test
  public void testInit() {
    for (int i = 1; i < 11; i++) {
      assertEquals("rectangle", lines.get(i).split(" ")[0]);
    }
  }

  @Test
  public void testMoves() {
    int start = 11;
    for (int i = 0; i < 5; i++) { //This test will fail if less than 5 swaps in random array.
      for (int j = 0; j < 4; j++) {
        String[] line = lines.get(start+i*6+j).split(" ");
        assertEquals("change-color", line[0]);
        assertEquals("name", line[1]);
        assertEquals("colorto", line[3]);
        assertEquals("from", line[10]);
        assertEquals("to", line[12]);
      }
    }
  }

}