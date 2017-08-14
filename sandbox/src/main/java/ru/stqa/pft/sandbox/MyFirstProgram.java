package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("World");
    hello("user");
    int l = 5;
    System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));
    int a = 6;
    int b = 7;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a,b));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double len){
    return len * len;
      }

  public static double area(double a, double b){
    return a * b;
  }
}