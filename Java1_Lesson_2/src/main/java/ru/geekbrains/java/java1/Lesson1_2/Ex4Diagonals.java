package ru.geekbrains.java.java1.Lesson1_2;

import java.util.Arrays;
import java.util.Scanner;

public class Ex4Diagonals {

/* 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    и с помощью цикла(-ов) заполнить его диагональные элементы единицами;*/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Укажите количество столбцов в массиве: ");
        int nCount = scan.nextInt();
        int[][] arr = new int[nCount][nCount]; // объявленный массив int заполнен нулями

        System.out.println("Способ 1:"); //вложенные циклы
        diagonals1(arr);
        System.out.println("Способ 2:"); // цикл с двумя переменными
        diagonals2(arr);
        scan.close();
    }

    // вложенные циклы с проверкой условий
    public static void diagonals1(int[][] arr) {
        for (int n = 0; n < arr.length; n++) {
            for (int m = 0; m < arr.length; m++) {
                // условие для "прямой" диагонали 1,1 .. n,n (левый верхний - правый нижний угол): n == m ИЛИ
                // условие для обратной диагонали 1,n .. n,1 (правый верхний - левый нижний угол): n + m == arr.length - 1
                if (n == m | n + m == arr.length - 1) {arr[n][m] = 1;}
            }
            System.out.println(Arrays.toString(arr[n])); // вывод массива
        }
    }

    // учитывая, что это двумерный регулярный (квадратный) массив!
    // цикл с двумя управляющими переменными переменными, без проверки условий, сразу присваиваем значения ячейкам,
    public static void diagonals2(int[][] arr) {
        for (int n = 0, m = arr.length - 1; n < arr.length; n++, m--) {
            arr[n][n] = 1; // ячейки диагонали левый верхний - правый нижний угол
            arr[n][m] = 1; // ячейки диагонали правый верхний - левый нижний угол
            System.out.println(Arrays.toString(arr[n])); // вывод массива
        }
    }
}