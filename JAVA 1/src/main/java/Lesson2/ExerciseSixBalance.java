package Lesson2;

import java.util.Arrays;

public class ExerciseSixBalance {

/* 6. Написать метод, в который передается не пустой одномерный целочисленный массив,
метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
граница показана символами ||, эти символы в массив не входят.*/

    private static int[] arr = {11, 9, 8, 6, 2, 5, 1, 1, 6, 4, 1, 2};
    private static int nSum;

    public static void main(String[] args) {
        System.out.println(checkBalance(arr));
    }

    public static boolean checkBalance(int[] arr) {
        nSum = summArray(arr);
        if (nSum % 2 != 0) {
            System.out.println("Cумма всех элементов массива нечетная, двух равных частей быть не может.");
            return false;
        } else {
            int iIndex = indexInArray(arr);
            if (iIndex != -1) {
                System.out.println("Суммы элементов этих частей равны:");
                System.out.println(Arrays.toString(Arrays.copyOf(arr, iIndex +1)) + Arrays.toString(Arrays.copyOfRange(arr, iIndex + 1, arr.length)));
                return true;
            } else {
                System.out.println("В массиве нет места, в котором суммы элементов двух частей равны.");
                return false;
            }
        }
    }

    private static int summArray(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        return res;
    }

    public static int indexInArray(int[] arr) {
        int pos = -1;
        int half = 0;
        for (int i = 0; i < arr.length; i++) {
            half += arr[i];
            if (half == nSum / 2) {pos = i; break;}
        }
        return pos;
    }
}
