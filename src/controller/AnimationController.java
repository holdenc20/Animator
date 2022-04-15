package controller;

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
import view.CompositeViewImpl;

/**
 * Controller for an Animation that takes arguments on construction in order to
 * customizable how you want to implement the Animation.
 */
public class AnimationController implements ActionListener, ChangeListener {

  private AnimatorView view;
  private double tickScale;

  /**
   * Creates an animation based on the given arguments.
   * @param args The arguments are in the form of
   *             -in INPUT FILE LOCATION
   *             -out OUTPUT FILE LOCATION
   *             -view VIEW TYPE
   *             -speed TICKRATE
   */
  public AnimationController(String[] args) {
    handleArgs(args);
  }

  private void handleArgs(String[] args) {
    int arg = 0;
    String out = "System.out";
    String in = "";

    while (args.length > arg) {
      switch (args[arg]) {
        case "-in":
          in = args[arg + 1];
          break;
        case "-out":
          out = args[arg + 1];
          break;
        case "-view":
          view = ViewFactory.makeView(args[arg + 1]);
          break;
        case "-speed":
          tickScale = Double.parseDouble(args[arg + 1]);
          break;
        default:
          throw new IllegalArgumentException("Invalid Command Line Arguments");
      }
      arg += 2;
    }
    if(view.getClass().equals(new CompositeViewImpl("").getClass())){
      ((CompositeViewImpl) view).addActionListener(this);
      ((CompositeViewImpl) view).addChangeListener(this);
    }

    view.setTickRate(tickScale);
    AnimationFileReader reader = new AnimationFileReader();
    Animator animator = null;
    try {
      animator = reader.readFile(in, new TweenBuilderImpl());
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }
    view.setModelState(animator);
    FileWriter writer = null;
    if (!out.equals("System.out")) {
      try {
        writer = new FileWriter(new File(out));
        view.setOutput(writer);
      } catch (IOException e) {
        System.out.println("IOException");
        e.printStackTrace();
      }
    }
    try {
      view.renderAnimation();
      if (writer != null) {
        writer.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("IOException");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource().getClass().equals(new JButton().getClass())) {
      String action = ((JButton) e.getSource()).getText();
      switch (action.toLowerCase()){
        case "start":
          ((CompositeViewImpl) view).start();
          break;
        case "resume":
          ((CompositeViewImpl) view).resume();
          break;
        case "pause":
          ((CompositeViewImpl) view).pause();
          break;
        case "on/off looping":
          ((CompositeViewImpl) view).toggleLooping();
          break;
      }
    }
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    if(e.getSource().getClass().equals(new JSlider().getClass())) {
      int value = ((JSlider) e.getSource()).getValue();
      view.setTickRate(value * tickScale);
    }
  }
}
