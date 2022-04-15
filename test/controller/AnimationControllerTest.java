package controller;

import org.junit.Test;

import javax.swing.*;

import io.AnimationController;

import static org.junit.Assert.*;

/**
 * Tester for the AnimationController.
 */
public class AnimationControllerTest {

  private StringBuilder output;
  private MockView view;
  private AnimationController ctr;

  /**
   * Constructor to initialize values.
   */
  public AnimationControllerTest() {
    output = new StringBuilder();
    view = new MockView(output);
    ctr = new AnimationController(view, 1);
  }

  @Test
  public void testInit() {
    String[] lines = output.toString().split("\n");
    assertEquals("addActionListener", lines[0]);
    assertEquals("addChangeListener", lines[1]);
  }

  @Test
  public void testButtons() {
    JButton button = new JButton("Start");
    JButton button2 = new JButton("Pause");
    JButton button3 = new JButton("Resume");
    JButton button4 = new JButton("on/off looping");
    button.addActionListener(ctr);
    button2.addActionListener(ctr);
    button3.addActionListener(ctr);
    button4.addActionListener(ctr);
    button.doClick();
    button2.doClick();
    button3.doClick();
    button4.doClick();
    String[] lines = output.toString().split("\n");
    assertEquals("start", lines[2]);
    assertEquals("pause", lines[3]);
    assertEquals("resume", lines[4]);
    assertEquals("toggleLooping", lines[5]);
  }

  @Test
  public void testSlider() {
    JSlider slider = new JSlider(0, 10, 2);
    slider.addChangeListener(ctr);
    slider.setValue(5);
    String[] lines = output.toString().split("\n");
    assertEquals("setTickRate", lines[2]);
  }

}