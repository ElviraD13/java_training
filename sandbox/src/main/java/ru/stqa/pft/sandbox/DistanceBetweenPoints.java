package ru.stqa.pft.sandbox;

public class DistanceBetweenPoints {

  public static void main(String[] args) {
    Point p1 = new Point(1,6);
    Point p2 = new Point(4,15);
    System.out.println("Точка A с координатами" + " x = " + p1.x + " y = " + p1.y);
    System.out.println("Точка B с координатами" + " x = " + p2.x + " y = " + p2.y);
    System.out.println("Расстояние между точками A и B: " + p1.distance(p2.x,p2.y));
    }

}