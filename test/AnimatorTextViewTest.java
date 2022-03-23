import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AnimatorTextViewTest {

  Appendable app;
  Animator state;
  AnimatorTextView atv;

  public AnimatorTextViewTest(){
    app = new StringBuffer();
    state = new ShapeAnimator();
    atv = new AnimatorTextView(state, app);
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
  public void testToString() {
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
            "1 (10, 10) 10 20 (30, 40, 50) -> 25 (100, 40) 30 40 (30, 40, 50)\n\n");

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
            "1 (10, 10) 10 20 (30, 40, 50) -> 25 (100, 40) 30 40 (30, 40, 50)\n" +
            "40 (100, 40) 30 40 (30, 40, 50) -> 50 (1, 1) 30 40 (30, 4, 5)\n\n");

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
            "Ellipse Ellipse1:\n" +
            "40 (100, 40) 10 20 (30, 40, 50) -> 50 (2, 2) 130 140 (60, 14, 15)\n" +
            "\n" +
            "Rectangle Rect1:\n" +
            "1 (10, 10) 10 20 (30, 40, 50) -> 25 (100, 40) 30 40 (30, 40, 50)\n" +
            "40 (100, 40) 30 40 (30, 40, 50) -> 50 (1, 1) 30 40 (30, 4, 5)\n\n");
  }
}