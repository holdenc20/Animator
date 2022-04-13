package io;

import view.AnimatorTextView;
import view.AnimatorView;
import view.CompositeViewImpl;
import view.SVGView;
import view.SwingView;

/**
 * View Factory class with a makeView method that returns an AnimatorView of the specified type.
 */
public class ViewFactory {

  /**
   * Method to create an AnimatorView of a given type.
   *
   * @param type The type of the animation (text=AnimatorTextView, visual=SwingView, svg=SVGView)
   * @return The proper AnimatorView.
   * @throws IllegalArgumentException if the type is invalid.
   */
  public static AnimatorView makeView(String type) {
    switch (type) {
      case "text":
        return new AnimatorTextView();
      case "visual":
        return new SwingView("Animation");
      case "svg":
        return new SVGView();
      case "interactive":
        return new CompositeViewImpl("Animaiton");
      default:
        throw new IllegalArgumentException("Invalid type");
    }
  }

}
