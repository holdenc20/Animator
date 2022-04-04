import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import io.AnimationRunner;
import model.Animator;
import model.Color;
import model.Ellipse;
import model.Position;
import model.Rectangle;
import model.Shape;
import model.ShapeAnimator;
import view.AnimatorTextView;
import view.AnimatorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tester for the AnimatorTextView class.
 */
public class AnimatorTextViewTest {

  Appendable app;
  Animator state;
  AnimatorView atv;

  /**
   * Constructor to initialize text values.
   */
  public AnimatorTextViewTest() {
    app = new StringBuffer();
    state = new ShapeAnimator(300, 300);
    atv = new AnimatorTextView();
    atv.setModelState(state);
    atv.setOutput(app);
  }

  @Test
  public void renderAnimation() {
    Position p = new Position(10, 10);
    Color c = new Color(30, 40, 50);
    Shape s1 = new Rectangle(p, 10, 20, c);
    state.makeAnimatedShape("Rect1", s1);
    try {
      atv.renderAnimation();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(app.toString(), "t == time\n" +
            "p == position as (x, y)\n" +
            "w == width\n" +
            "h == height\n" +
            "c == color as (r, g, b)\n" +
            "t pos w h color -> t pos w h color\n" +
            "----------------------------------\n" +
            "Rectangle Rect1:\n\n");
  }

  @Test
  public void testProperOutput() {
    String in = "-in resources/smalldemo.txt -out resources/output.txt -speed 2 -view text";
    String[] args = in.split(" ");
    AnimationRunner.main(args);
    try {
      Scanner s = new Scanner(new File("resources/output.txt"));
      int numLines = 0;
      while (s.hasNextLine()) {
        numLines++;
        String trash = s.nextLine();
      }
      s.close();
      assertEquals(20, numLines); //There should be 20 lines out output
    } catch (FileNotFoundException e) {
      fail("No exception expected");
    }
  }

  @Test
  public void testToString() {
    assertEquals(atv.toString(), "t == time\n" +
            "p == position as (x, y)\n" +
            "w == width\n" +
            "h == height\n" +
            "c == color as (r, g, b)\n" +
            "t pos w h color -> t pos w h color\n" +
            "----------------------------------\n");

    Position p = new Position(10, 10);
    Color c = new Color(30, 40, 50);
    Shape s1 = new Rectangle(p, 10, 20, c);
    state.makeAnimatedShape("Rect1", s1);
    assertEquals(atv.toString(), "t == time\n" +
            "p == position as (x, y)\n" +
            "w == width\n" +
            "h == height\n" +
            "c == color as (r, g, b)\n" +
            "t pos w h color -> t pos w h color\n" +
            "----------------------------------\n" +
            "Rectangle Rect1:\n\n");

    Position p1 = new Position(100, 40);
    Color c1 = new Color(30, 40, 50);
    Shape s2 = new Rectangle(p1, 30, 40, c1);
    state.addMotion("Rect1", s2, 1, 25);
    assertEquals(atv.toString(), "t == time\n" +
            "p == position as (x, y)\n" +
            "w == width\n" +
            "h == height\n" +
            "c == color as (r, g, b)\n" +
            "t pos w h color -> t pos w h color\n" +
            "----------------------------------\n" +
            "Rectangle Rect1:\n" +
            "1.0 (10.0, 10.0) 10.0 20.0 (30.0, 40.0, 50.0) -> " +
            "25.0 (100.0, 40.0) 30.0 40.0 (30.0, 40.0, 50.0)\n\n");

    Position p2 = new Position(1, 1);
    Color c2 = new Color(30, 4, 5);
    Shape s3 = new Rectangle(p2, 30, 40, c2);
    state.addMotion("Rect1", s3, 40, 50);
    assertEquals(atv.toString(), "t == time\n" +
            "p == position as (x, y)\n" +
            "w == width\n" +
            "h == height\n" +
            "c == color as (r, g, b)\n" +
            "t pos w h color -> t pos w h color\n" +
            "----------------------------------\n" +
            "Rectangle Rect1:\n" +
            "1.0 (10.0, 10.0) 10.0 20.0 (30.0, 40.0, 50.0) -> " +
            "25.0 (100.0, 40.0) 30.0 40.0 (30.0, 40.0, 50.0)\n" +
            "40.0 (100.0, 40.0) 30.0 40.0 (30.0, 40.0, 50.0) -> " +
            "50.0 (1.0, 1.0) 30.0 40.0 (30.0, 4.0, 5.0)\n\n");

    Shape s5 = new Ellipse(p1, 10, 20, c1);
    Position p4 = new Position(2, 2);
    Color c4 = new Color(60, 14, 15);
    Shape s4 = new Ellipse(p4, 130, 140, c4);
    state.makeAnimatedShape("Ellipse1", s5);
    state.addMotion("Ellipse1", s4, 40, 50);
    assertEquals(atv.toString(), "t == time\n" +
            "p == position as (x, y)\n" +
            "w == width\n" +
            "h == height\n" +
            "c == color as (r, g, b)\n" +
            "t pos w h color -> t pos w h color\n" +
            "----------------------------------\n" +
            "Rectangle Rect1:\n" +
            "1.0 (10.0, 10.0) 10.0 20.0 (30.0, 40.0, 50.0) -> " +
            "25.0 (100.0, 40.0) 30.0 40.0 (30.0, 40.0, 50.0)\n" +
            "40.0 (100.0, 40.0) 30.0 40.0 (30.0, 40.0, 50.0) -> " +
            "50.0 (1.0, 1.0) 30.0 40.0 (30.0, 4.0, 5.0)\n\n" +
            "Ellipse Ellipse1:\n" +
            "40.0 (100.0, 40.0) 10.0 20.0 (30.0, 40.0, 50.0) -> " +
            "50.0 (2.0, 2.0) 130.0 140.0 (60.0, 14.0, 15.0)\n" +
            "\n");


    atv.setTickRate(2);
    assertEquals(atv.toString(), "t == time\n" +
            "p == position as (x, y)\n" +
            "w == width\n" +
            "h == height\n" +
            "c == color as (r, g, b)\n" +
            "t pos w h color -> t pos w h color\n" +
            "----------------------------------\n" +
            "Rectangle Rect1:\n" +
            "0.5 (10.0, 10.0) 10.0 20.0 (30.0, 40.0, 50.0) -> " +
            "12.5 (100.0, 40.0) 30.0 40.0 (30.0, 40.0, 50.0)\n" +
            "20.0 (100.0, 40.0) 30.0 40.0 (30.0, 40.0, 50.0) -> " +
            "25.0 (1.0, 1.0) 30.0 40.0 (30.0, 4.0, 5.0)\n\n" +
            "Ellipse Ellipse1:\n" +
            "20.0 (100.0, 40.0) 10.0 20.0 (30.0, 40.0, 50.0) -> " +
            "25.0 (2.0, 2.0) 130.0 140.0 (60.0, 14.0, 15.0)\n" +
            "\n");


  }
}