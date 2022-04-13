package view;

import java.io.IOException;

import javax.swing.JFrame;

import model.AnimatorState;

/**
 * Java Swing implementation of an AnimatorView that displays an animation in a JFrame window.
 */
public class SwingView extends JFrame implements AnimatorView {

  protected SwingPanel panel;

  /**
   * Constructor for the SwingView that takes in a title.
   *
   * @param windowTitle The title of the window
   */
  public SwingView(String windowTitle) {
    super(windowTitle);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new SwingPanel(1);
    this.add(panel);
  }

  @Override
  public void renderAnimation() throws IOException {
    this.setVisible(true);
    panel.startTimer();
    this.repaint();
  }

  @Override
  public void setModelState(AnimatorState state) {
    panel.setState(state);
    this.pack();
    setLocationRelativeTo(null);
  }

  @Override
  public void setTickRate(double tickRate) {
    panel.setTickRate(tickRate);
  }

  @Override
  public void setOutput(Appendable app) {
    //This view's only output is the JFrame window.
  }
}
