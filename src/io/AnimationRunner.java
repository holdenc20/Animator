package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import model.Animator;
import view.AnimatorView;
import view.ViewFactory;

/**
 * Runner class to hold a main method.
 */
public class AnimationRunner {

  /**
   * The main method of the program.
   *
   * @param args the arguments of the program.
   */
  public static void main(String[] args) {
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
    } catch (IOException e){
      e.printStackTrace();
    }
  }

}
