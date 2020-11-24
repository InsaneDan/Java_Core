package Lesson2;

import java.util.Arrays;

public class ExerciseThreeMultiplySmallNumbers {

/* 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2 */

    private static int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

    public static void main(String[] args) {
        System.out.println("Исходный массив:   " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] = arr[i] * 2;
        }
        System.out.println("Измененный массив: " + Arrays.toString(arr));
    }
}