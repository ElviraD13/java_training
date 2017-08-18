package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance1() {
    Point d1 = new Point(13, 24);
    Point d2 = new Point(62, 87);
    Assert.assertEquals(d1.distance(d2), 79.81227975693966);
  }

  @Test
  public void testDistance2() {
    Point d1 = new Point(-3, 24);
    Point d2 = new Point(27, -7);
    Assert.assertEquals(d1.distance(d2), 43.139309220245984);
  }

  @Test
  public void testDistance3() {
    Point d1 = new Point(15, 25);
    Point d2 = new Point(14, 24);
   Assert.assertTrue(d1.distance(d2)!= 0, "дистанция = 0, то есть это не отрезок, а точка");
  }
}
