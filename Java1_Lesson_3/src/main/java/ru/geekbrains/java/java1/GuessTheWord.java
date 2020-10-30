package ru.geekbrains.java.java1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessTheWord {

/* Создать массив из слов
String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь.
Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
apple – загаданное
apricot - ответ игрока
ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
Для сравнения двух слов посимвольно можно пользоваться:
String str = "apple";
char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
Играем до тех пор, пока игрок не отгадает слово. Используем только маленькие буквы.
*/

    public static Scanner scanner = new Scanner(System.in);
    private final static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    /* константы лучше было бы объявить в методе playGame, т.к. только там они и используются,
    * оставил здесь, т.к. удобнее менять значения */
    private final static char MASK_CHAR = '*';
    private final static int MASK_LENGTH = 15; //

    public static void main(String[] args) {
        do {
            Random rand = new Random(); // инициируем ГПСЧ
            // получаем индекс от 0 до длины массива, если изменится количество значений, изменится и верняя граница rand
            int index = rand.nextInt(words.length); // диапазон random не достигает указанного верхнего значения,
                                                    // но индекс последнего элемента меньше длины массива на 1, т.е. все слова могут быть использованы

            playGame(index); // пытаемся угадать слово

            // playGameSimple(index); // упрощенный вариант

        } while (repeatGame()); // повторить игру?
        scanner.close();
    }

    /** Метод выводит в консоль маску из MASK_LENGTH символов и запрашивает ввод слова. При полном совпадении
     * введенного и загаданного слов - победа (выход из метода). При несовпадении - перебор "спрятонного" слова,
     * если буква скрыта (MASK_CHAR), то: 1) в случаях, когда буквы в загаданном и введенном словах в текущей позиции
     * совпадают - открыть символ (сначала проверить длину ответа, чтобы индекс был в пределах строки);
     * 2) после неудачной попытки - открыть первый скрытый символ (после совпадений в предыдущем пункте);
     * 3) если буква уже открыта - не скрываем, оставляем без изменений;
     * 4) остаток до длины маски заполнить символами;
     * 5) подсчет открытых букв, если счетчик равен длине загаданного слова, то игра закончилась - выйти из цикла.
     *
     * @param index - индекс загаданного слова в массиве
     */
    private static void playGame (int index) {
        String sVisible = repeatChar(MASK_CHAR, MASK_LENGTH); // если делать по длине слова, то words[index].length - i
        StringBuilder sTmp;
        int nCharCounter; // счетчик открытых букв
        boolean blOpenChar; //
        // количество попыток = длина выбранного слова - 1, т.к. после показа последней буквы угадывать уже нечего
        for (int i = 0; i < words[index].length() - 1; i++) {
            System.out.println("Try to guess a word: " + sVisible);
            System.out.print("Your " + (i + 1) + " try:  ");
            String answer = scanner.next().toLowerCase(); // преобразовать в строчные
            if (answer.equals(words[index])) {
                System.out.println("You win!");
                return;
            } else {
                sTmp = new StringBuilder(); // буферная переменная
                nCharCounter = 0; // сбросить счетчик
                blOpenChar = true; // установить флаг, что нужно открыть одну букву после неудачной попытки

                for (int j = 0; j < words[index].length(); j++) {
                    nCharCounter++;
                    if (sVisible.charAt(j) == MASK_CHAR) { // буква скрыта
                        if (answer.length() > j && words[index].charAt(j) == answer.charAt(j)) {
                            // длина ответа соответствует номеру итерации, буква загаданного и введенного слов совпадают
                            sTmp.append(answer.charAt(j)); // открыть букву
                        } else if (blOpenChar) {
                            // открыть букву после неуспешной попытки и сбросить флаг на false (один раз за попытку)
                            sTmp.append(words[index].charAt(j));
                            blOpenChar = false;
                        } else {
                            // остальные символы не менять (оставить маску)
                            sTmp.append(MASK_CHAR);
                            nCharCounter--;
                        }
                    } else { // буква уже открыта
                        sTmp.append(words[index].charAt(j));
                    }
                }
                sTmp.append(repeatChar(MASK_CHAR, MASK_LENGTH - sTmp.length())); // заполнить остаток маски
                sVisible = sTmp.toString(); // вернуть значение в переменную для отображения в консоли

                if (nCharCounter == words[index].length()) break; // если счетчик открытых букв равен длине загаданного слова, то выйти из цикла
            }
        }
        // цикл завершен -> все буквы открыты, если не сработал return, значит слово не угадано
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

    /** Упрощенный метод - проверка совпадения введенного и загаданного слова, при несоответствии - открывать по очереди
     * буквы с начала слова
     * @param index - индекс загаданного слова в массиве */
    private static void playGameSimple (int index) {
        String sVisible;
        for (int i = 0; i < words[index].length(); i++) {
            // первые символы загаданного слова в зависимости от количества попыток
            sVisible = words[index].substring(0, i).concat(repeatChar(MASK_CHAR, MASK_LENGTH - i));
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
}
