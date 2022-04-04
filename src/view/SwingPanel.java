package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.AnimatorState;
import model.ShapeType;

/**
 * view.SwingPanel class that controls the JPanel of the animation.
 */
public class SwingPanel extends JPanel implements ActionListener {

  private AnimatorState state;
  int currentTick;
  Timer timer;

  /**
   * Constructor for a view.SwingPanel.
   *
   * @param tickRate the ticks per second of the animation
   */
  public SwingPanel(double tickRate) {
    this.state = null;
    timer = new Timer((int) (1000 / tickRate), this);
    currentTick = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (String name : state.getShapeIDs()) {
      try {
        drawShape(state.getShapeAtTime(name, currentTick), g);
      } catch (IllegalArgumentException e) {
        //Time is out of range
        continue;
      }
    }
  }

  private void drawShape(model.Shape s, Graphics g) {
    g.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue()));
    if (s.getShapeType() == ShapeType.ELLIPSE) {
      g.fillOval((int) s.getPosition().getX(), (int) s.getPosition().getY(), (int) s.getWidth(),
              (int) s.getHeight());
    } else {
      g.fillRect((int) s.getPosition().getX(), (int) s.getPosition().getY(), (int) s.getWidth(),
              (int) s.getHeight());
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    currentTick++;
    repaint();
  }

  /**
   * Starts the timer and begins the animation.
   */
  public void startTimer() {
    timer.start();
  }

  /**
   * Sets the tick rate of the animation and resets the timer.
   *
   * @param tickRate the new tickrate
   */
  public void setTickRate(double tickRate) {
    timer = new Timer((int) (1000 / tickRate), this);
  }

  public void setState(AnimatorState state) {
    this.state = state;
    this.setPreferredSize(new Dimension(state.getWidth(), state.getHeight()));
  }
}
