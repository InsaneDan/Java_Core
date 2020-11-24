package Lesson1;

import java.util.Scanner;

public class Ex3CalculateExpression {

// 3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,где a, b, c, d – входные параметры этого метода;

    public static void main(String[] args) {
        console();
    }

    public static long сalculation(int a, int b , int c, int d) {
        long res = a * (b + (c / d));
        return res;
    }

    // ввод данных для задания 3 и вывод результата
    public static void console() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите четыре числа:");
        System.out.print("a = ");
        int a = scan.nextInt();
        System.out.print("b = ");
        int b = scan.nextInt();
        System.out.print("c = ");
        int c = scan.nextInt();
        System.out.print("d = ");
        int d = scan.nextInt();
//      вычисление
        long result = сalculation(a, b, c, d);
//      вывод результата
        System.out.println("Результат вычисления: " + a + " * (" + b + " + (" + c + " / " + d + ")) = " + result);
    }
}
