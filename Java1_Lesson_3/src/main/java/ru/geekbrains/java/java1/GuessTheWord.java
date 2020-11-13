package ru.geekbrains.java.java1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessTheWord {

/* Создать массив из слов
String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь.
Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
apple – загаданное
apricot - ответ игрока
ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
Для сравнения двух слов посимвольно можно пользоваться:
String str = "apple";
char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
Играем до тех пор, пока игрок не отгадает слово.
Используем только маленькие буквы.
*/

    public static Scanner scanner = new Scanner(System.in);
    private static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    public static void main(String[] args) {
        do {
            Random rand = new Random();
            int index = rand.nextInt(words.length); // "случайное" число - индекс в массиве
            System.out.println("Try to guess a fruit."); // заголовок - задание
            playGame(index); // пытаемся угадать слово
        } while (repeatGame()); // повторить игру?
    }

    private static void playGame (int index) {
        String sVisible;
        for (int i = 0; i < words[index].length(); i++) {
            // первые символы загаданного слова в зависимости от количества попыток
            sVisible = words[index].substring(0, i);
            // маска фиксированной длины (15 символов), уменьшается при каждой итерации за счет открываемых символов
            // если делать по длине слова, то words[index].length - i
            System.out.println("Try to guess: " + sVisible + repeatChar('*', 15 - i)); // words[index].length() - i));
            System.out.print("Your " + (i + 1) + " try:  ");
            String answer = scanner.next().toLowerCase(); // преобразовать в строчные
            if (answer.equals(words[index])) {
                System.out.println("You win!");
                return;
            }
        }
        System.out.println("You loose. The word was: " + words[index]);
    }

    /* строка заданной длинны из одинаковых символов (для маски загаданного слова) */
    public static String repeatChar(char ch, int count) {
        char[] chars = new char[count];
        Arrays.fill(chars, ch);
        return new String(chars);
    }

    /* запрос повторной игры */
    public static boolean repeatGame() {
        do {
            System.out.print("Play again? (1 - yes, 0 - no): ");
            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 0: return false;
                case 1: return true;
                default: System.out.println("Wrong answer. 1 or 0 keys could be pressed only.");
            }
        } while (true);
    }
}
