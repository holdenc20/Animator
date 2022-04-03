package io;

import view.AnimatorView;

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
    String out;
    String in;
    AnimatorView view;
    double tickRate;
    while (args.length > arg) {
      switch (args[arg]) {
        case "-in":
          in = args[arg+1];
        case "-out":

      }
      arg += 2;
    }
  }



}
