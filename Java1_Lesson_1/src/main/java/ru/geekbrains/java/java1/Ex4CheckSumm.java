package ru.geekbrains.java.java1;

import java.util.Scanner;

public class Ex4CheckSumm {

/* 4. Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
      если да – вернуть true, в противном случае – false;*/

    public static void main(String[] args) {
        сonsole();
    }

    public static boolean сheckSumm(int a, int b) {
        boolean res;
        int sum = a + b;
        if (sum >= 10 & sum <= 20) {res = true;} else {res = false;}
        return res;
    }
    // ввод данных для задания 4 и вывод результата
    public static void сonsole() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите пару чисел:");
        System.out.print("a = ");
        int a = scan.nextInt();
        System.out.print("b = ");
        int b = scan.nextInt();
        System.out.println("Сумма чисел находится в диапазоне 10..20 (включительно): " + сheckSumm(a, b));
        scan.close();
    }

}
