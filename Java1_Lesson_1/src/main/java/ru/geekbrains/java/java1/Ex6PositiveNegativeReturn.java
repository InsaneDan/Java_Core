package ru.geekbrains.java.java1;

import java.util.Scanner;

public class Ex6PositiveNegativeReturn {

/* 6. Написать метод, которому в качестве параметра передается целое число,
      метод должен вернуть true, если число отрицательное;*/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите целое число (от -2147483648 до 2147483647): ");
        int a = scan.nextInt();
        positiveNegativeReturn(a);
    }

    public static void positiveNegativeReturn(int a) {
        boolean res;
        if (a < 0) {res = true;} else {res = false;}
        System.out.println("Это число отрицательное: " + res);
    }

}
