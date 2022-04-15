package controller;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.event.ChangeListener;

import model.AnimatorState;
import view.CompositeView;

/**
 * Mock composite view for controller testing.
 */
public class MockView implements CompositeView {

  private StringBuilder builder;

  /**
   * Constructor to create a Mock view and initialize the StringBuilder.
   *
   * @param builder the StringBuilder
   */
  public MockView(StringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public void renderAnimation() throws IOException {
    builder.append("renderAnimation\n");
  }

  @Override
  public void setModelState(AnimatorState state) {
    builder.append("setModelState\n");
  }

  @Override
  public void setTickRate(double tickRate) {
    builder.append("setTickRate\n");
  }

  @Override
  public void setOutput(Appendable app) {
    builder.append("setOutput\n");
  }

  @Override
  public void start() {
    builder.append("start\n");
  }

  @Override
  public void pause() {
    builder.append("pause\n");
  }

  @Override
  public void resume() {
    builder.append("resume\n");
  }

  @Override
  public void toggleLooping() {
    builder.append("toggleLooping\n");
  }

  @Override
  public void addActionListener(ActionListener listener) {
    builder.append("addActionListener\n");
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    builder.append("addChangeListener\n");
  }
}
