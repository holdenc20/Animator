import java.io.IOException;

import javax.swing.*;

/**
 * Java Swing implementation of an AnimatorView that displays an animation in a JFrame window.
 * TODO: implement scroll bars
 */
public class SwingView extends JFrame implements AnimatorView {

  /**
   * Constructor for the SwingView that takes in a title and an animation state.
   *
   * @param windowTitle The title of the window
   * @param state       The state of the animation
   */
  public SwingView(String windowTitle, AnimatorState state, double tickRate) {
    super(windowTitle);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SwingPanel panel = new SwingPanel(state, tickRate);
    this.add(panel);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void renderAnimation() throws IOException {
    this.repaint();
  }
}
