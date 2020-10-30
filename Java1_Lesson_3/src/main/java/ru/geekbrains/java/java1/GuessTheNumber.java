package ru.geekbrains.java.java1;

//import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int range = 9;
        do {
            int number = (int) (Math.random() * (range + 1)); // "случайное" число в рамках диапазона (random не достигает 1)
            // или Random number = new Random(range + 1);
            System.out.println("Угадайте число от 0 до " + range + "."); // заголовок - задание
            playGame(number); // пытаемся угадать число
        } while (repeatGame()); // повторить игру?
        scanner.close();
    }

    public static boolean repeatGame() {
        do {
            System.out.print("Повторить игру еще раз? (1 - да, 0 - нет): ");
            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 0: return false;
                case 1: return true;
                default: System.out.println("Такой ответ не предусмотрен, нужно ввести 1 или 0.");
            }
        } while (true);
    }

   private static void playGame (int number) {
       int attempts = 3;
       for (int i = 0; i < attempts; i++) {
           System.out.print("Осталось попыток: " + (attempts - i) + ". Ваш ответ: ");
           int answer = scanner.nextInt();
           if (answer > number) {
               System.out.println("Загаданное число меньше.");
           } else if (answer < number) {
                System.out.println("Загаданное число больше.");
           } else { // answer == number
                System.out.println("Именно это число и было загадано!");
                return;
           }
       }
       System.out.println("Вы проиграли. Было загадано число - " + number);
   }
}
