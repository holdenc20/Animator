import java.io.IOException;

/**
 * Interface to represent the view of a given animation.
 */
public interface AnimatorView {

  /**
   * Renders the Animator to a given destination.
   * @throws IOException if an error appending occurs.
   */
  void renderAnimation() throws IOException;

}
