package io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import io.AnimationFileReader;
import io.TweenBuilderImpl;
import io.ViewFactory;
import model.Animator;
import view.AnimatorView;
import view.CompositeView;
import view.CompositeViewImpl;

/**
 * Controller for an Animation that takes arguments on construction in order to
 * customizable how you want to implement the Animation.
 */
public class AnimationController implements ActionListener, ChangeListener {

  private CompositeView view;
  private double tickScale;

  /**
   * Constructor for an Animation Controller that initializes values.
   *
   * @param view      the CompositeView
   * @param tickScale the tickScale
   */
  public AnimationController(CompositeView view, double tickScale) {
    this.view = view;
    this.tickScale = tickScale;
    view.addActionListener(this);
    view.addChangeListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().getClass().equals(new JButton().getClass())) {
      String action = ((JButton) e.getSource()).getText();
      switch (action.toLowerCase()) {
        case "start":
          view.start();
          break;
        case "resume":
          view.resume();
          break;
        case "pause":
          view.pause();
          break;
        case "on/off looping":
          view.toggleLooping();
          break;
      }
    }
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    if (e.getSource().getClass().equals(new JSlider().getClass())) {
      int value = ((JSlider) e.getSource()).getValue();
      view.setTickRate(value * tickScale / 100);
    }
  }
}
