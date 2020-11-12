package ru.geekbrains.java.java1.Lesson1_2;

import java.util.Arrays;

public class ExerciseFiveFindMinMax {

// 5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);

    /* как запомнить индексы, если мин или макс значений несколько?
    *  кроме как заполнить в String ничего не придумалось... */

    private static int nMin, nMax; // минимальное и максимальное значение
    private static String iMin, iMax; // индексы мин. и макс. элементов, строкой (проще) или динамическим массивом?
    private static int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

    public static void main(String[] args) {
        findMinMax(arr);
        System.out.println("Массив: " + Arrays.toString(arr));
        System.out.println("Минимальный элемент = " + nMin + " (индекс(ы) в массиве: " + iMin + ")");
        System.out.println("Максимальный элемент = " + nMax + " (индекс(ы) в массиве: " + iMax + ")");
    }

    private static void findMinMax(int ...arr) {
        nMin = arr[0]; nMax = arr[0];
        iMin = "0"; iMax = "0";
        for (int i = 1; i < arr.length; i++) {
            // минимальный(ые) элемент(ы) + их индексы
            if (arr[i] < nMin) {
                nMin = arr[i];
                iMin = "" + i; // при изменении nMin очистить предыдущие и сохранить текущий индекс
            } else if (arr[i] == nMin) {
                iMin = iMin + ", " + i; // есть такое же значение, добавляем индекс
            }
            // максимальный(ые) элемент(ы) + их индексы
            if (arr[i] > nMax) {
                nMax = arr[i];
                iMax = "" + i; // при изменении переменной nMax очистить предыдущие и сохранить текущий индекс
            } else if (arr[i] == nMax) {
                iMax = iMax + ", " + i;
            }
        }
    }
}