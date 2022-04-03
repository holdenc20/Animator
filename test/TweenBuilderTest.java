import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TweenBuilderTest {

  TweenBuilderImpl tweenBuilder;

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
            .addRectangle("Rect1", 10, 20, 10, 20, 30, 40, 50, 2, 22)
            .addMove("Rect1", 10, 20, 20, 30, 10, 20)
            .addMove("Rect1", 20,30, 100, 100, 21, 23).build();
    a.setCreationTime("Rect1", 0);
    a.setDeletionTime("Rect1", 25);
  }

  @Test
  public void addColorChange() {
  }

  @Test
  public void addScaleToChange() {
  }

  @Test
  public void build() {
  }
}