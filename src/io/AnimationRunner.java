package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import controller.AnimationController;
import model.Animator;
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
    new AnimationController(args);
  }

}
