package view;

import java.io.IOException;

import javax.swing.*;

import model.AnimatorState;
import view.AnimatorView;
import view.SwingPanel;

/**
 * Java Swing implementation of an view.AnimatorView that displays an animation in a JFrame window.
 * TODO: implement scroll bars
 * TODO: package everything
 */
public class SwingView extends JFrame implements AnimatorView {

  /**
   * Constructor for the view.SwingView that takes in a title.
   *
   * @param windowTitle The title of the window
   */
  public SwingView(String windowTitle) {
    super(windowTitle);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SwingPanel panel = new SwingPanel(null, 1); //TODO: panel tickrate
    this.add(panel);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void renderAnimation() throws IOException {
    this.repaint();
  }

  @Override
  public void setModelState(AnimatorState state) {

  }

  @Override
  public void setTickRate(double tickRate) {

  }

  @Override
  public void setOutput(Appendable app) {

  }
}
