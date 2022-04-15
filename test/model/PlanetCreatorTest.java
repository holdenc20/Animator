package model;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Tester class for the PlanetCreator Animation.
 */
public class PlanetCreatorTest {

  private List<String> lines;

  /**
   * Creates and sets initial values for the testing.
   */
  public PlanetCreatorTest() {
    new PlanetCreator();
    Scanner reader = null;
    try {
      reader = new Scanner(new File("resources/planets.txt"));
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
  public void testNumLines() {
    assertEquals(1+1+8+8*499, lines.size());
    //1 canvas, 1 sun, 8 init planets, 499 moves per planet
  }

  @Test
  public void testProperCanvas() {
    String[] firstLine = lines.get(0).split(" ");
    assertEquals("600", firstLine[1]);
    assertEquals("600", firstLine[2]);
  }

  @Test
  public void testMoves() {
    int start = 10;
    for (int j = 0; j < 499; j++) {
      for (int i = 0; i < 8; i++) {
        String[] line = lines.get(start+8*j+i).split(" ");
        if (i < 2) {
          assertEquals("move", line[0]);
          assertEquals("name", line[1]);
          assertEquals("P"+i, line[2]);
          assertEquals("moveto", line[3]);
          assertEquals("from", line[8]);
          assertEquals("to", line[10]);
          assertEquals(""+j, line[9]);
          assertEquals(""+(j+1), line[11]);
        } else {

        }
      }
    }
  }

  @Test
  public void testInits() {
    int start = 1;
    for (int i = 0; i < 8; i++) {
      String[] line = lines.get(start+i).split(" ");
      assertEquals("oval", line[0]);
      assertEquals("name", line[1]);
      assertEquals("P"+i, line[2]);
      assertEquals("center-x", line[3]);
      assertEquals("center-y", line[5]);
      assertEquals("x-radius", line[7]);
      assertEquals("y-radius", line[9]);
      assertEquals("color", line[11]);
      assertEquals("from", line[15]);
      assertEquals("0", line[16]);
      assertEquals("to", line[17]);
      assertEquals("500", line[18]);
    }
    assertEquals("oval name SUN center-x 300 center-y 300 x-radius 50 y-radius 50 color " +
            "1 1 0 from 0 to 500", lines.get(9));
  }

}