import java.io.IOException;

import javax.swing.*;

public class AnimatorFrame extends JFrame implements AnimatorView {

  public AnimatorFrame(String animatorTitle, Animator animator){
    super(animatorTitle);

    setSize(animator.getWidth(), animator.getHeight());
    setLocation(200,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    AnimationPanel panel = new AnimationPanel(animator);
    this.add(panel);
  }

  @Override
  public void renderAnimation() throws IOException {

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
