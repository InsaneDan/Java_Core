package ru.geekbrains.java.java1;

import java.util.Scanner;

public class Ex8LeapYear {

/* 8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
      Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.*/

    public static void main(String[] args) {
        int y = 1; // чтобы зайти в цикл
        Scanner scan = new Scanner(System.in);
        // бесконечный цикл, выход - 0
        while (true) {
            System.out.print("Введите год (для выхода - 0): ");
            y = scan.nextInt();
            if (y == 0) {break;}
            leapYear(y);
        }
        scan.close();
    }

    public static void leapYear(int y) {
        boolean blLeapYear;
//      проверяем условия
        if (y % 400 == 0) {
            blLeapYear = true;
        } else if (y % 100 != 0 && y % 4 == 0) {
            blLeapYear = true;
        } else {
            blLeapYear = false;
        }
//      вывод в консоль
        if (blLeapYear) {
            System.out.println("Год високосный!");
        } else {
            System.out.println("Год НЕ високосный!");
        }
    }
}
