import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * SwingPanel class that controls the JPanel of the animation.
 */
public class SwingPanel extends JPanel implements ActionListener {

  private AnimatorState state;
  int currentTick;

  /**
   * Constructor for a SwingPanel.
   *
   * @param state    The state of the model
   * @param tickRate the ticks per second of the animation
   */
  public SwingPanel(AnimatorState state, double tickRate) {
    this.state = state;
    this.setPreferredSize(new Dimension(state.getWidth(), state.getHeight()));
    Timer timer = new Timer((int) (1000 / tickRate), this);
    currentTick = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (String name : state.getShapeIDs()) {
      drawShape(state.getShapeAtTime(name, currentTick), g);
    }
  }

  private void drawShape(Shape s, Graphics g) {
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
  }
}
