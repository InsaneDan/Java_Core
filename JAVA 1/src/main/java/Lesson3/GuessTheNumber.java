package Lesson3;

import java.util.Scanner;

public class GuessTheNumber {

    private static Scanner scanner = new Scanner(System.in);
    private static int range = 9;
    private static int attempts = 3;

    public static void main(String[] args) {
        do {
            int number = (int) (Math.random() * range); // "случайное" число
            System.out.println("Угадайте число от 0 до " + range + "."); // заголовок - задание
            playGame(number); // пытаемся угадать число
        } while (repeatGame()); // повторить игру?
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
       for (int i = 0; i < attempts; i++) {
           System.out.print("Осталось попыток: " + (attempts - i) + ". Ваш ответ: ");
           int answer = scanner.nextInt();
           if (answer > number) {
               System.out.println("Загаданное число меньше.");
           } else if (answer < number) {
                System.out.println("Загаданное число больше.");
           } else if (answer == number) {
                System.out.println("Именно это число и было загадано!");
                return;
           }
       }
       System.out.println("Вы проиграли. Было загадано число - " + number);
   }
}
