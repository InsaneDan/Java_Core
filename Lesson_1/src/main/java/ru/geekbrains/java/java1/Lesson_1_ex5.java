package ru.geekbrains.java.java1;

import java.util.Scanner;

public class Lesson_1_ex5 {

/* 5. Написать метод, которому в качестве параметра передается целое число,
      метод должен напечатать в консоль положительное ли число передали, или отрицательное;
      Замечание: ноль считаем положительным числом.*/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите целое число (от -2147483648 до 2147483647): ");
        int a = scan.nextInt();
        Ex6_Positive(a);
    }

    public static void Ex6_Positive(int a) {
        String res;
        if (a < 0) {res = "отрицательное";} else {res = "положительное";}
        System.out.println("Это число " + res);
    }

}
