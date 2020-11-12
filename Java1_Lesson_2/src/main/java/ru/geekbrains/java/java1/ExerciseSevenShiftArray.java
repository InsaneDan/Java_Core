package ru.geekbrains.java.java1.Lesson1_2;

import javax.naming.PartialResultException;
import java.util.Arrays;
import java.util.Scanner;

public class ExerciseSevenShiftArray {

/* 7. Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
    или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
    Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
    [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
    При каком n в какую сторону сдвиг можете выбирать сами.*/

    public static void main(String[] args) {
        int[] arr = new int[16];
        for (int i = 0; i < 16; i++) {
            arr[i] = i + 1;
        }
        int shift = inputShift(arr); // определить сдвиг
        shiftArrayDumb(arr, shift); // сдвиг простым перебором
        shiftArray(arr, shift); // сдвиг по замкнутым циклам
    }

    public static int inputShift (int ...arr) {
        System.out.print("На какое количество позиций сдвинуть массив? ");
        Scanner scanner = new Scanner(System.in);
        int shift = scanner.nextInt();
        if (Math.abs(shift) > arr.length) {
            shift = (shift % arr.length); // если сдвиг больше длины массива, преобразовать к короткому сдвигу
        }
        if (shift < 0) {
            shift = arr.length + shift; // если указан сдвиг влево, преобразовать к сдвигу вправо
        }
        return shift;
    }

    /* решение простым перебором в циклах со сдвигом на одну позицию */
    public static void shiftArrayDumb(int[] arr, int shift) {
        int[] a = Arrays.copyOf(arr, arr.length);
        int b; // промежуточная переменная
        for (int j = 0; j < shift; j++) {
            b = a[a.length - 1]; // запоминаем значение последней ячейки
            // сдвигаем все значения по очереди на 1 шаг вправо
            for (int i = a.length-1; i > 0; i--) {
                a[i] = a[i-1];
            }
            a[0] = b; // переставляем последнюю ячейку в начало
        }
        System.out.println(Arrays.toString(a));
    }

    public static void shiftArray(int[] arr, int shift) {
        int[] a = Arrays.copyOf(arr, arr.length);
        int iPrev; // первый индекс, откуда забираем значение
        int iNext; // следующий индекс, с которым знначения нужно поменять местами
        int temp; // промежуточная переменная
        for (int iCycle = 0; iCycle < cyclesAmount(arr.length, shift); iCycle++) {
            iPrev = iCycle; // iCycle - начальный индекс с которого начинается цикл
            do {
                iNext = iPrev - shift; // следующий индекс
                if (iNext < 0) {
                    iNext = iNext + a.length; // если вышел за нижнюю границу массива, отсчитать с конца
                }
                if (iNext != iCycle) {
                    temp = a[iPrev]; // запомнить 1 значение
                    a[iPrev] = a[iNext]; // заменить 1-е значение 2-м
                    a[iNext] = temp; // заменить 2-е значение 1-м из промежуточной переменной
                }
                iPrev = iNext; // перейти к следующему индексу
                // System.out.println(Arrays.toString(a)); // промежуточные значения массива
            } while (iNext != iCycle);
        }
        System.out.println(Arrays.toString(a));

    }
    /* количество закмнутых циклов, чтобы обойти все ячейки массива; */
    public static int cyclesAmount(int a, int b) {
        return a % b == 0 ? b : cyclesAmount(b, a % b); // рекурсивно
        /* или в цикле:
        int c = b;
        while (a % b != 0) {
            c = a;
            a = b;
            b = c % b;
        }
        return b; */
    }
}
