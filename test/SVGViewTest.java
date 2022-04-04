import org.junit.Test;

import java.io.IOException;

import model.Animator;
import model.Color;
import model.Ellipse;
import model.Position;
import model.Rectangle;
import model.Shape;
import model.ShapeAnimator;
import view.AnimatorView;
import view.SVGView;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the view.SVGView class.
 */
public class SVGViewTest {

  Appendable app;
  Animator state;
  AnimatorView svg;

  /**
   * Initializes variables used in tests.
   */
  public SVGViewTest() {
    app = new StringBuffer();
    state = new ShapeAnimator(300, 300);
    svg = new SVGView();
    svg.setOutput(app);
    svg.setModelState(state);
  }

  @Test
  public void renderAnimation() {
    Position p = new Position(10, 10);
    Color c = new Color(30, 40, 50);
    Shape s1 = new Rectangle(p, 10, 20, c);
    state.makeAnimatedShape("Rect1", s1);
    try {
      svg.renderAnimation();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(app.toString(), "<svg width=\"300\" height=\"300\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"Rect1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"" +
            "rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"" +
            "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>");
  }

  @Test
  public void testToString() {
    assertEquals(svg.toString(), "<svg width=\"300\" height=\"300\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "</svg>");

    Position p = new Position(10, 10);
    Color c = new Color(30, 40, 50);
    Shape s1 = new Rectangle(p, 10, 20, c);
    state.makeAnimatedShape("Rect1", s1);
    assertEquals(svg.toString(), "<svg width=\"300\" height=\"300\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"Rect1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"" +
            "rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"" +
            "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>");

    Position p1 = new Position(100, 40);
    Color c1 = new Color(30, 40, 50);
    Shape s2 = new Rectangle(p1, 30, 40, c1);
    state.addMotion("Rect1", s2, 1, 25);
    assertEquals(svg.toString(), "<svg width=\"300\" height=\"300\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"Rect1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"" +
            "rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"v" +
            "isibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" attributeName=\"x\" " +
            "from=\"10\" to=\"100\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" attributeName=\"y\" " +
            "from=\"10\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"width\" from=\"10\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"height\" from=\"20\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,40,50)\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>");

    Position p2 = new Position(1, 1);
    Color c2 = new Color(30, 4, 5);
    Shape s3 = new Rectangle(p2, 30, 40, c2);
    state.addMotion("Rect1", s3, 40, 50);
    assertEquals(svg.toString(), "<svg width=\"300\" height=\"300\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"Rect1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"" +
            "rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"" +
            "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"x\" from=\"10\" to=\"100\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"y\" from=\"10\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"width\" from=\"10\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"height\" from=\"20\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,40,50)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"y\" from=\"40\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"width\" from=\"30\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"height\" from=\"40\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,4,5)\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>");

    Shape s5 = new Ellipse(p1, 10, 20, c1);
    Position p4 = new Position(2, 2);
    Color c4 = new Color(60, 14, 15);
    Shape s4 = new Ellipse(p4, 130, 140, c4);
    state.makeAnimatedShape("Ellipse1", s5);
    state.setCreationTime("Ellipse1", 35);
    state.addMotion("Ellipse1", s4, 40, 50);
    assertEquals(svg.toString(), "<svg width=\"300\" height=\"300\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"Rect1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" " +
            "fill=\"rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"" +
            "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"x\" from=\"10\" to=\"100\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"y\" from=\"10\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\"" +
            " attributeName=\"width\" from=\"10\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"height\" from=\"20\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"24000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,40,50)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"y\" from=\"40\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"width\" from=\"30\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"height\" from=\"40\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,4,5)\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "<ellipse id=\"Ellipse1\" cx=\"105\" cy=\"50\" rx=\"5\" ry=\"10\" " +
            "fill=\"rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"35000ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"cx\" from=\"105\" to=\"67\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"cy\" from=\"50\" to=\"72\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"rx\" from=\"5\" to=\"65\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"ry\" from=\"10\" to=\"70\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"40000ms\" dur=\"10000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(60,14,15)\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>");

    svg.setTickRate(2);
    assertEquals(svg.toString(), "<svg width=\"300\" height=\"300\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"Rect1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" fill=\"" +
            "rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" attributeName=\"v" +
            "isibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"x\" from=\"10\" to=\"100\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" a" +
            "ttributeName=\"y\" from=\"10\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"width\" from=\"10\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"height\" from=\"20\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,40,50)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"y\" from=\"40\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"width\" from=\"30\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"height\" from=\"40\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,4,5)\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "<ellipse id=\"Ellipse1\" cx=\"105\" cy=\"50\" rx=\"5\" ry=\"10\"" +
            " fill=\"rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"17500ms\" a" +
            "ttributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\"" +
            " attributeName=\"cx\" from=\"105\" to=\"67\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"cy\" from=\"50\" to=\"72\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"rx\" from=\"5\" to=\"65\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"ry\" from=\"10\" to=\"70\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\"" +
            " attributeName=\"fill\" from=\"(30,40,50)\" to=\"(60,14,15)\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>");

    state.setDeletionTime("Rect1", 50);
    assertEquals(svg.toString(), "<svg width=\"300\" height=\"300\"" +
            " version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"Rect1\" x=\"10\" y=\"10\" width=\"10\" height=\"20\" " +
            "fill=\"rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"0ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"x\" from=\"10\" to=\"100\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"y\" from=\"10\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"width\" from=\"10\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"height\" from=\"20\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"12000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,40,50)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\"" +
            " attributeName=\"x\" from=\"100\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"y\" from=\"40\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"width\" from=\"30\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"height\" from=\"40\" to=\"40\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\"" +
            " attributeName=\"fill\" from=\"(30,40,50)\" to=\"(30,4,5)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"500ms\" " +
            "attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "<ellipse id=\"Ellipse1\" cx=\"105\" cy=\"50\" rx=\"5\" ry=\"10\" " +
            "fill=\"rgb(30,40,50)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"17500ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"cx\" from=\"105\" to=\"67\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"cy\" from=\"50\" to=\"72\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"rx\" from=\"5\" to=\"65\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"ry\" from=\"10\" to=\"70\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"20000ms\" dur=\"5000ms\" " +
            "attributeName=\"fill\" from=\"(30,40,50)\" to=\"(60,14,15)\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>");
  }
}