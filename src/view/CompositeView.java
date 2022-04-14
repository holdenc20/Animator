package view;

import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

/**
 * CompositeView extends the AnimatorView interface to add the functionality to pause, resume, and
 * toggle whether an animation loops after completion.
 */
public interface CompositeView extends AnimatorView {

  /**
   * Starts the current animation.
   */
  void start();

  /**
   * Pauses the current animation.
   */
  void pause();

  /**
   * Resumes the current animation.
   *
   * @throws IllegalStateException if the animation has not started yet.
   */
  void resume();

  /**
   * Toggles whether the animation loops or stops after completion.
   */
  void toggleLooping();

  /**
   * Adds the action listener of the composite view.
   *
   * @param listener the listener
   */
  void addActionListener(ActionListener listener);

  /**
   * Adds the change listener of the composite view.
   *
   * @param listener the listener
   */
  void addChangeListener(ChangeListener listener);

}
