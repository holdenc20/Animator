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
 * SwingPanel class that controls the JPanel of the animation.
 */
public class SwingPanel extends JPanel implements ActionListener {

  private AnimatorState state;
  private int currentTick;
  private Timer timer;
  private int maxEndTime;

  /**
   * Constructor for a SwingPanel.
   *
   * @param tickRate the ticks per second of the animation
   */
  public SwingPanel(double tickRate) {
    this.state = null;
    timer = new Timer((int) (1000 / tickRate), this);
    currentTick = 0;
    maxEndTime = -1;
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
    if(maxEndTime != -1 && currentTick > maxEndTime){
      restartTimer();
    }
    repaint();
  }

  /**
   * Starts the timer and begins the animation.
   */
  public void startTimer() {
    timer.start();
  }

  /**
   * Pauses the timer.
   */
  public void pauseTimer() {
    timer.stop();
  }

  /**
   * Restarts the timer to 0 and restarts the timer.
   */
  public void restartTimer() {
    currentTick = 0;
    startTimer();
  }

  /**
   * Toggles between not doing anything when the animation ends
   * and restarting the animation when the animation ends.
   */
  public void toggleLooping() {
    int max = 0;
    for(String s : state.getShapeIDs()){
      if(state.getAnimatedShape(s).getDeletionTime() > max){
        max = state.getAnimatedShape(s).getDeletionTime();
      }
    }
    if(maxEndTime == -1){
      maxEndTime = max;
    }
    else{
      maxEndTime = -1;
    }
  }

  /**
   * Sets the tick rate of the animation and resets the timer.
   *
   * @param tickRate the new tickrate
   */
  public void setTickRate(double tickRate) {
    timer.setDelay((int) (1000 / tickRate));
  }

  /**
   * Sets the Animator State for the current animation.
   * @param state The given animator state to display to the panel.
   */
  public void setState(AnimatorState state) {
    this.state = state;
    this.setPreferredSize(new Dimension(state.getWidth(), state.getHeight()));
  }
}
