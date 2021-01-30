package Lesson1;

import java.util.Scanner;

public class Ex7HelloSmbd {

/* 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
      метод должен вывести в консоль сообщение «Привет, указанное_имя!»;*/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Представтесь, пожалуйста: ");
        String s = scan.next();
        helloDude(s);
    }

    public static void helloDude(String s) {
        System.out.println("Привет, " + s + "!");
    }

}
