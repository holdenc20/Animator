package io;

import view.AnimatorTextView;
import view.AnimatorView;
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
   */
  public static AnimatorView makeView(String type) {
    if (type.equals("text")) {
      return new AnimatorTextView();
    } else if (type.equals("visual")) {
      return new SwingView("Animation");
    } else {
      return new SVGView();
    }
  }

}
