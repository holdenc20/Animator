package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import model.Animator;
import view.AnimatorView;
import view.CompositeView;
import view.CompositeViewImpl;

/**
 * Runner class to hold a main method.
 */
public class AnimationRunner {

  /**
   * Creates an animation based on the given arguments.
   * @param args The arguments are in the form of
   *             -in INPUT FILE LOCATION
   *             -out OUTPUT FILE LOCATION
   *             -view VIEW TYPE
   *             -speed TICKRATE
   */
  public static void main(String[] args) {
    int arg = 0;
    String out = "System.out";
    String in = "";
    AnimatorView view = null;
    double tickScale = 1;
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
    if(view.getClass().equals(CompositeViewImpl.class)){
      AnimationController ctr = new AnimationController((CompositeView) view, tickScale);
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

}
