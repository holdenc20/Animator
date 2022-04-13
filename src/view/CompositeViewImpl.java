package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import model.AnimatorState;

public class CompositeViewImpl extends SwingView implements CompositeView {

  private JButton startButton;
  private JButton resumeButton;
  private JButton pauseButton;
  private JButton toggleLoopButton;
  private JSlider speedSlider;

  /**
   * Constructor for the CompositeView that takes in a title.
   *
   * @param windowTitle The title of the window
   */
  public CompositeViewImpl(String windowTitle) {
    super(windowTitle);
    initButtons();
  }

  private void initButtons() {
    startButton = new JButton("Start");
    resumeButton = new JButton("Resume");
    pauseButton = new JButton("Pause");
    toggleLoopButton = new JButton("Toggle Looping");
    speedSlider = new JSlider(0, 10, 1);
    add(startButton);
    add(resumeButton);
    add(pauseButton);
    add(toggleLoopButton);
    //add(speedSlider);
    setResizable(false);
  }

  @Override
  public void pause() {
    panel.pauseTimer();
  }

  @Override
  public void resume() {
    panel.startTimer();
  }

  @Override
  public void toggleLooping() {

  }

  @Override
  public void addActionListener(ActionListener listener) {
    startButton.addActionListener(listener);
    pauseButton.addActionListener(listener);
    resumeButton.addActionListener(listener);
    toggleLoopButton.addActionListener(listener);
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    speedSlider.addChangeListener(listener);
  }

  @Override
  public void setModelState(AnimatorState state) {
    panel.setState(state);
    setLayout(state);

    this.pack();
    setLocationRelativeTo(null);
  }

  private void setLayout(AnimatorState state) {
    int headerHeight = 50;
    startButton.setBounds(0, 0, state.getWidth()/4, headerHeight);
    startButton.setPreferredSize(new Dimension(state.getWidth()/4, headerHeight));
    pauseButton.setBounds(state.getWidth()/4, 0, state.getWidth()/4, headerHeight);
    resumeButton.setBounds(state.getWidth()/2, 0, state.getWidth()/4, headerHeight);
    toggleLoopButton.setBounds(state.getWidth()/4, 0, state.getWidth()/4, headerHeight);
    panel.setPreferredSize(new Dimension(state.getWidth(), state.getHeight()));
    add(panel);
  }

}
