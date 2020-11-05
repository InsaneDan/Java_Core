package ru.geekbrains.java.java1;

public class LessonSession {

    public static void main(String[] args) {
        System.out.printf("Слово: %20s, дробное число с запятой: %.10f, целое число: %5d, символ: %c\n", "ТЕКСТ", 2.534523, 79, 'c');
        System.out.printf("Слово: %20s, дробное число с запятой: %.10f, целое число: %5d, символ: %c\n", "строка подлиннее", 2.3, 79000, 'c');
        System.out.println(summArray());
    }

    public static int summArray() {
        int[][] arr = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int sum = 0;
        for (int[] o1 : arr) {
            for (int o2 : o1) {
                sum += o2;
            }
        }
        return sum;
    }
}