import org.junit.Test;

import model.Animator;
import io.TweenBuilderImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tester class for the io.TweenModelBuilder interface.
 */
public class TweenBuilderTest {
  private TweenBuilderImpl tweenBuilder;

  /**
   * Initializing variables to be used in the test methods.
   */
  public TweenBuilderTest() {
    tweenBuilder = new TweenBuilderImpl();
  }

  @Test
  public void setBounds() {
    Animator a = tweenBuilder.setBounds(10,11).build();
    assertEquals(a.getHeight(), 11);
    assertEquals(a.getWidth(), 10);
  }

  @Test
  public void addOval() {
    Animator a = tweenBuilder.addOval("Oval1", 10, 20, 10, 20, 30, 40, 50, 1, 11)
            .build();
    a.setCreationTime("Oval1", 0);
    a.setDeletionTime("Oval1", 20);
    assertEquals(a.getShapeIDs().get(0), "Oval1");
    assertEquals(a.getAnimatedShape("Oval1").getShapeAtTime(0).getWidth(), 20, 0.01);
  }

  @Test
  public void addRectangle() {
    Animator a = tweenBuilder.setBounds(100,100)
            .addRectangle("Rect1", 10, 20, 10, 20, 30, 40, 50, 2, 22)
            .build();
    a.setCreationTime("Rect1", 0);
    a.setDeletionTime("Rect1", 25);
    assertEquals(a.getShapeIDs().get(0), "Rect1");
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(0).getWidth(), 10, 0.01);
  }

  @Test
  public void addMove() {
    Animator a = tweenBuilder.setBounds(100,100)
            .addRectangle("Rect1", 10, 20, 10, 20, 30, 40, 50, 0, 30)
            .addMove("Rect1", 10, 20, 20, 30, 10, 20)
            .addMove("Rect1", 20,30, 100, 100, 21, 23).build();
    a.setCreationTime("Rect1", 0);
    a.setDeletionTime("Rect1", 25);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(15).getPosition().getX(), 15, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(15).getPosition().getY(), 25, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(22).getPosition().getX(), 60, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(22).getPosition().getY(), 65, 0.1);
  }

  @Test
  public void addColorChange() {
    Animator a = tweenBuilder.setBounds(100,100)
            .addRectangle("Rect1", 10, 20, 10, 20, 30, 40, 50, 0, 30)
            .addColorChange("Rect1", 30, 40, 50, 100, 100, 100, 5, 15)
            .addColorChange("Rect1", 100, 100, 100, 30, 40, 50, 15, 25)
            .build();
    a.setCreationTime("Rect1", 0);
    a.setDeletionTime("Rect1", 30);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(10).getColor().getRed(), 65, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(10).getColor().getGreen(), 70, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(10).getColor().getBlue(), 75, 0.1);

    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getColor().getRed(), 65, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getColor().getGreen(), 70, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getColor().getBlue(), 75, 0.1);
  }

  @Test
  public void addScaleToChange() {
    Animator a = tweenBuilder.setBounds(100,100)
            .addRectangle("Rect1", 10, 20, 10, 20, 30, 40, 50, 0, 30)
            .addScaleToChange("Rect1", 10, 20, 40, 100, 3, 13)
            .addScaleToChange("Rect1", 40, 100, 2, 2, 15, 25)
            .build();
    a.setCreationTime("Rect1", 0);
    a.setDeletionTime("Rect1", 30);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(8).getWidth(), 25, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(8).getHeight(), 60, 0.1);

    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(15).getWidth(), 40, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(15).getHeight(), 100, 0.1);

    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getWidth(), 21, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getHeight(), 51, 0.1);
  }

  @Test
  public void build() {
    Animator a = tweenBuilder.setBounds(100,100)
            .addRectangle("Rect1", 10, 20, 10, 20, 30, 40, 50, 0, 30)
            .addScaleToChange("Rect1", 10, 20, 40, 100, 3, 13)
            .addScaleToChange("Rect1", 40, 100, 2, 2, 15, 25)
            .addColorChange("Rect1", 30, 40, 50, 100, 100, 100, 5, 15)
            .addColorChange("Rect1", 100, 100, 100, 30, 40, 50, 15, 25)
            .addMove("Rect1", 10, 20, 20, 30, 10, 20)
            .addMove("Rect1", 20,30, 100, 100, 21, 23)
            .build();
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(22).getPosition().getX(), 60, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(22).getPosition().getY(), 65, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getColor().getRed(), 65, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getColor().getGreen(), 70, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getColor().getBlue(), 75, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getWidth(), 21, 0.1);
    assertEquals(a.getAnimatedShape("Rect1").getShapeAtTime(20).getHeight(), 51, 0.1);

  }
}