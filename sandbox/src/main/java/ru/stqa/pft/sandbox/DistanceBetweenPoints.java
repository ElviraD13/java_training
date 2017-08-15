package ru.stqa.pft.sandbox;

public class DistanceBetweenPoints {

  public static void main(String[] args) {
    Point p1 = new Point(1,2);
    Point p2 = new Point(3,4);
    System.out.println("Точка A с координатами" + " x = " + p1.x + " y = " + p1.y);
    System.out.println("Точка B с координатами" + " x = " + p2.x + " y = " + p2.y);
    System.out.println("Расстояние между точками A и B: " + distance(p1 , p2));
    }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p2.x - p1.x,2) + Math.pow(p2.y - p1.y,2));
    }
}