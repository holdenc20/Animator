import java.awt.*;

import javax.swing.*;

public class AnimationPanel extends JPanel {

  private final Animator animator;
  private int tick;

  public AnimationPanel(Animator animator) {
    this.animator = animator;
    tick = 0;
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    for(String id : animator.getShapeIDs()){
      animator.getShapeAtTime(id, tick);
    }
  }
}
