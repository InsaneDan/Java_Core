package ru.geekbrains.java.java1.Lesson1_2;

import java.util.Arrays;

public class ExerciseTwoFillArray {

/* 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;*/

    public static void main(String[] args) {
            int[] arr = new int[8];
            for (int i = 0; i < arr.length; i++) {arr[i] = i * 3;}
            System.out.println("Заполненный массив: " + Arrays.toString(arr));
    }
/* вариант: явно указать значение первого элемента arr[0] = 0,
   значения остальных элементов = +3 к значению предыдущего элемента
    public static void main(String[] args) {
        int[] arr = new int[8];
        arr[0] = 0;
        for (int i = 1; i < arr.length; i++) {arr[i] = arr[i-1] + 3;}
        System.out.println("Заполненный массив: " + Arrays.toString(arr));
    } */
}