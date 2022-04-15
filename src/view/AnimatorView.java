package view;

import java.io.IOException;

import model.AnimatorState;

/**
 * Interface to represent the view of a given animation.
 */
public interface AnimatorView {

  /**
   * Renders the Animator to a given destination.
   *
   * @throws IOException if an error appending occurs.
   */
  void renderAnimation() throws IOException;

  /**
   * Sets the model state of the view.
   *
   * @param state The state
   */
  void setModelState(AnimatorState state);

  /**
   * Sets the tick rate of the animation.
   *
   * @param tickRate The tick rate
   */
  void setTickRate(double tickRate);

  /**
   * Sets the output of the animation.
   *
   * @param app The output appendable.
   */
  void setOutput(Appendable app);
}
