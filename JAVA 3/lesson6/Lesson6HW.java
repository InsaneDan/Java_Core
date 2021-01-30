package lesson6;

import java.util.Arrays;

public class Lesson6HW {

    public static void main(String[] args) {
        Lesson6HW hw = new  Lesson6HW();
        System.out.print("Задание 1: ");
        System.out.println(Arrays.toString(hw.trimArrayOfNumbers(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, 4)));
        System.out.print("Задание 2: ");
        System.out.println(hw.isArrayContainsNumbers(new int[]{1, 4, 1, 1, 4, 4, 4, 1, 4, 4, 1, 1}, 4, 1));
    }

    public int[] trimArrayOfNumbers(int[] arr, int Number) throws RuntimeException {
        for (int i = arr.length - 1; i > 0 ; i--) {
            if (arr[i] == Number) {
                int[] resArray = Arrays.copyOfRange(arr, i + 1, arr.length);
                return resArray;
            }
        }
        throw new RuntimeException("В массиве нет числа " + Number);
    }

    public boolean isArrayContainsNumbers(int[] arr, int num1, int num2) {
        boolean res1 = false;
        boolean res2 = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num1)
                res1 = true;
            else if(arr[i] == num2)
                res2 = true;
            else
                return false;
        }
        return res1 & res2;
    }



}
