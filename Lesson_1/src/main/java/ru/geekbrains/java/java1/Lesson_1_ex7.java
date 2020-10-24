package ru.geekbrains.java.java1;

import java.util.Scanner;

public class Lesson_1_ex7 {

/* 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
      метод должен вывести в консоль сообщение «Привет, указанное_имя!»;*/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Представтесь, пожалуйста: ");
        String s = scan.next();
        Ex7_HelloDude(s);
    }

    public static void Ex7_HelloDude(String s) {
        System.out.println("Привет, " + s + "!");
    }

}
