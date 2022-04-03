package view;

import view.AnimatorTextView;
import view.AnimatorView;
import view.SVGView;
import view.SwingView;

/**
 * View Factory class with a makeView method that returns an view.AnimatorView of the specified type.
 */
public class ViewFactory {

  /**
   * Method to create an view.AnimatorView of a given type.
   *
   * @param type The type of the animation (text=view.AnimatorTextView, visual=view.SwingView, svg=view.SVGView)
   * @return The proper view.AnimatorView.
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
