package ru.geekbrains.java.java1.Lesson1_2;

import java.util.Arrays;

public class ExerciseOneInvertArray {

/* 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
   С помощью цикла и условия заменить 0 на 1, 1 на 0;

   Метод 1: switch = ОПЕРАТОР: с использованием break, чтобы не выполнялись последующие кейсы
            при объявлении массива [] - после ТИПА ДАННЫХ: int[] a
   Метод 2: switch = ВЫРАЖЕНИЕ: без использования break, выполняется только одна строка с условием (или блок scope)
            при объявлении массива [] - после ИМЕНИ ПЕРЕМЕННОЙ: int a[]
            Также это метод с параметром (массивом) переменной длины = необязательный параметр.

    При простом присваивании переменные будут ссылаться на один и тот же массив. Поэтому после выполнения метода 1 массив
    инвертируется, а метод 2 получает не исходный, а измененый массив и вернет его в первоначальное состояние.
    Чтобы использовать исходный массив, в методах используем копии (Arrays.copyOf(arrName, arrName.length))
 */

    private static int[] arr = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };

    public static void main(String[] args) {
        System.out.println("Исходный массив:   " + Arrays.toString(arr));
        method1(arr);
        method2(arr);
    }

    public static void method1(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case 0: a[i] = 1; break;
                case 1: a[i] = 0; break;
                default: // если не 0 и не 1, то число не меняется
            }
        }
        System.out.println("Инвертированный 1: " + Arrays.toString(a));
    }

    public static void method2(int ...arr) {
        int a[] = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case 0 -> a[i] = 1;
                case 1 -> a[i] = 0;
                // если не 0 и не 1, то число не меняется
            }
        }
        System.out.println("Инвертированный 2: " + Arrays.toString(a));
    }
}