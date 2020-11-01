package ru.geekbrains.java.java1;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static int SIZE = 10;
    public static int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {

        char player = DOT_X; // чей ход, начинают X
        char winner = DOT_EMPTY; // победитель
        int moves = 0; // количество ходов
        initMap();  // инициализация поля
        printMap(); // вывод поля с координатами

        /* количество ходов не может быть больше количества клеток. Используем обычынй цикл for с инкрементом.
         * В этом случае массив может быть полным только на последней итерации.
         * Или досрочный выход из цикла, если есть победитель. */
        for (int i = 0; i < SIZE * SIZE; i++) {
            System.out.println("Ход N" + (i + 1));
            // ход игрока ИЛИ рандом
            switch (player) {
                case DOT_X -> xTurn(DOT_X); //oTurn(DOT_X); // Comp vs comp
                case DOT_O -> oTurn(DOT_O);
            }
            printMap(); // вывести поле
            // если есть победитель - запомнить и выйти из цикла досрочно
            if (checkWin(player)) {
                winner = player;
                moves = i + 1; // счетчик ходов для вывода в консоль после выхода из цикла
                break;
            }
            player = (player == DOT_X) ? DOT_O : DOT_X;
        }
        //вывести в консоль, кто победил
        System.out.print("Игра закончена. ");
        switch (winner) {
            case DOT_EMPTY -> System.out.println("Победителя нет - ничья."); // победитель не определился, исходное значение
            case DOT_X -> System.out.println("Победили X за " + (moves / 2 + moves % 2) + " ходов");
            case DOT_O -> System.out.println("Победили O за " + (moves / 2) + " ходов");
        }
    }

    /** создать массив и заполнить его символами "пустых" ячеек */
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /** вывести массив с координатами в консоль через printf,
     *  чтобы сетка "не съезжала" при увеличении разряда в числах координат > 10, > 100, и т.д. */
    public static void printMap() {
        int numWidth = decimalPlaces(SIZE);
        for (int i = 0; i <= SIZE; i++) {
            System.out.printf("%" + numWidth + "d ", i); // первый ряд (координаты)
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%" + numWidth + "d ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%" + numWidth + "s ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /** подсчет максимального разряда чисел в координатной сетке */
    public static int decimalPlaces(int num) {
        int res = 0;
        while (num / 10 > 0) {
            res++;
            num /= 10;
        }
        return ++res;
    }

    /** автоматических ход по случайным координатам (компьютер) */
    public static void oTurn(char dot) {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (isCellValid(x, y) != 3);
        System.out.println("Ход " + dot + " в клетку: " + (x + 1) + " " + (y + 1));
        map[y][x] = dot;
    }

    /** ход по координатам, введенным вручную (человек) */
    public static void xTurn(char dot) {
        int x, y;
        do {
            System.out.print("Введите координаты в формате X Y: ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            switch (isCellValid(x, y)) {
                case 1 -> System.out.println("Неверно указаны координаты, попробуйте еще раз.");
                case 2 -> System.out.println("Эта клетка уже занята, попробуйте еще раз.");
            }
        } while (isCellValid(x, y) != 3);
        map[y][x] = dot;
    }

    /** проверка возможности сделать ход в эту клетку */
    public static int isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return 1; // выходит за пределы массива
        if (map[y][x] == DOT_EMPTY) return 3; // клетка не занята
        return 2; // в остальных случаях - клетка занята
    }

    /** проверка последовательности символов в линии
     *
     * @param X, Y - координаты начала линии
     * @param deltaX, deltaY - направление сдвига по осям X и Y; значения: 0 - нет сдвига, 1 - сдвиг на увеличение, -1 - на уменьшение
     * @param symbol - проверяемый символ, значения: DOT_X | DOT_O
     * (DOTS_TO_WIN - константа, количество подряд идущих символов, уменьшить на 1, т.к. индекс 1 элемента = 0)
     * @return true - соблюдена последовательность указанных символов нужной длины
     */
    private static boolean checkLine(int X, int Y, int deltaX, int deltaY, char symbol) {
        int resX = X + deltaX * (DOTS_TO_WIN - 1);
        int resY = Y + deltaY * (DOTS_TO_WIN - 1);
        if (resX > SIZE || resX < 0) return false; // линия закончится за пределами поля по горизонтали - false
        if (resY > SIZE || resY < 0) return false; // линия закончится за пределами поля по вертикали - false
        for (int i = 0; i < DOTS_TO_WIN; i++) { // перебор символов в ряду и проверка на соответствие
            if (map[Y + i * deltaY][X + i * deltaX] != symbol) return false; // если не совпадает - false
        }
        return true; // после перебора все символы в ряду совпали - true
    }

    /** проверка победителя */
    private static boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++) { // проход по оси Y
            for (int j = 0; j < SIZE; j++) { //проход по оси X
                if (checkLine(j, i, 1, 0, symbol)) return true;   // проверка горизонтали (слева направо)
                if (checkLine(j, i, 0, 1, symbol)) return true;   // проверка вертикали (сверху вниз)
                if (checkLine(j, i, 1, 1, symbol)) return true;   // проверка восходящей диагонали (/)
                if (checkLine(j, i, 1, -1, symbol)) return true;  // проверка нисходящей диагонали (\)
            }
        }
        return false; // нет возвращаемого результата - не победа
    }
}