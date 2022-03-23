import java.io.IOException;

/**
 * Implementation of the AnimatorView in text that appends the toString method to a given
 * appendable upon call of the renderAnimation() method.
 */
public class AnimatorTextView implements AnimatorView {

  private Appendable app;
  private AnimatorState state;

  /**
   * Constructor for an AnimatorTextView with a given state and appendable.
   *
   * @param state the state of the animation.
   * @param app the output of rendering.
   */
  public AnimatorTextView(AnimatorState state, Appendable app) {
    this.app = app;
    this.state = state;
  }

  /**
   * Constructor for an AnimatorTextView that uses the console as output.
   *
   * @param state the state of the animation.
   */
  public AnimatorTextView(AnimatorState state) {
    this.state = state;
    this.app = System.out;
  }

  @Override
  public void renderAnimation() throws IOException {
    app.append(state.toString());
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    addIntro(builder);
    for (String s : state.getShapeIDs()) {
      builder.append(state.getAnimatedShape(s).toString());
      builder.append("\n");
    }
    return builder.toString();
  }

  private void addIntro(StringBuilder builder) {
    builder.append("t == time\n");
    builder.append("p == position as (x, y)\n");
    builder.append("w == width\n");
    builder.append("h == height\n");
    builder.append("c == color as (r, g, b)\n");
    builder.append("t pos w h color -> t pos w h color\n");
    builder.append("----------------------------------\n");
  }
}
