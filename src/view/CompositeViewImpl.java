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
    this.pack();
  }

  private void initButtons() {
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    JPanel pbuttons = new JPanel();
    pbuttons.setLayout(new BoxLayout(pbuttons, BoxLayout.Y_AXIS));

    c.gridwidth = 1;
    c.gridheight = 1;

    startButton = new JButton("Start");
    c.fill = GridBagConstraints.VERTICAL;
    c.gridx = 0;
    c.gridy = 0;
    gridbag.setConstraints(startButton, c);
    pbuttons.add(startButton);

    resumeButton = new JButton("Resume");
    c.gridy = 1;
    gridbag.setConstraints(resumeButton, c);
    pbuttons.add(resumeButton);

    pauseButton = new JButton("Pause");
    c.gridy = 2;
    gridbag.setConstraints(pauseButton, c);
    pbuttons.add(pauseButton);

    toggleLoopButton = new JButton("on/OFF looping");
    c.gridy = 3;
    gridbag.setConstraints(toggleLoopButton, c);
    pbuttons.add(toggleLoopButton);

    speedSlider = new JSlider(1, 10, 1);
    c.gridy = 4;
    gridbag.setConstraints(toggleLoopButton, c);
    pbuttons.add(speedSlider);

    this.add(pbuttons);

    setResizable(false);
  }

  @Override
  public void start() {
    panel.restartTimer();
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
    panel.toggleLooping();
    if(toggleLoopButton.getText().contains("OFF")){
      toggleLoopButton.setText("ON/off looping");
    }
    else{
      toggleLoopButton.setText("on/OFF looping");
    }
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

    this.setLayout(new GridBagLayout());

    this.pack();
    setLocationRelativeTo(null);
  }
}
