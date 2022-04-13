package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import io.AnimationFileReader;
import io.TweenBuilderImpl;
import io.ViewFactory;
import model.Animator;
import view.AnimatorView;

/**
 * TODO: document and constructor
 */
public class AnimationController implements ActionListener, ChangeListener {

  public AnimationController(String[] args) {
    handleArgs(args);
  }

  private void handleArgs(String[] args) {
    int arg = 0;
    String out = "System.out";
    String in = "";
    AnimatorView view = null;
    double tickRate = 1;
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
          tickRate = Double.parseDouble(args[arg + 1]);
          break;
        default:
          throw new IllegalArgumentException("Invalid Command Line Arguments");
      }
      arg += 2;
    }
    view.setTickRate(tickRate);
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

  }

  @Override
  public void stateChanged(ChangeEvent e) {

  }
}
