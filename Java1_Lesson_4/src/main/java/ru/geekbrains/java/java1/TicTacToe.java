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
        initMap();  // инициализация поля
        printMap(); // вывод поля с координатами

        /* количество ходов не может быть больше количества клеток. Используем обычынй цикл for с инкрементом.
         * В этом случае массив может быть полным только на последней итерации. */
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (player == DOT_X) {
                xTurn(DOT_X);
                printMap();
                if (checkWin(DOT_X)) {
                    winner = DOT_X;
                    break;
                }
                player = DOT_O;
            } else {
                oTurn(DOT_O);
                printMap();
                if (checkWin(DOT_O)) {
                    winner = DOT_O;
                    break;
                }
                player = DOT_X;
            }
        }
        switch (winner) {
            case DOT_EMPTY -> System.out.println("Игра закончена: ничья");
            case DOT_X -> System.out.println("Игра закончена: победили X");
            case DOT_O -> System.out.println("Игра закончена: победили O");
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
    /** првоерка победы по горизонтали в указанном ряду */
    public static boolean checkRow(char symb, int row) {
        int counter = 0;
        for (int i = 0; i < map[row].length; i++) {
            if (counter == 0 && map[row].length - i < DOTS_TO_WIN) return false; // для выигрыша в этой строке уже не хватит ячеек => не победа (false)
            counter = ((map[row][i] == symb) ? counter + 1 : 0); // если встретился другой знак или пусто, то обнулить счетчик
            if (counter == DOTS_TO_WIN) return true; // если набрали нужное количество символов в ряд => победа (true)
        }
        return false;
    }
    /** проверка победы вниз по вертикали в заданном ряду */
    public static boolean checkColumn(char symb, int row) {
        if (map.length - row < DOTS_TO_WIN) return false; // если вертикаль меньше нужного количества символов - дальше не проверяем
        int counter = 0;
        for (int i = 0; i < map[row].length; i++) {
            for (int j = row; j < row + DOTS_TO_WIN; j++) {
                if (map[j][i] == symb) {
                    counter++;
                } else {
                    counter = 0;
                    break;
                }

            }
            if (counter == DOTS_TO_WIN) return true;
        }
        return false;
    }
    /** проверка победы по нисходящей горизонтали в указанном ряду */
    public static boolean checkDiagonalDown(char symb, int row) {
        int counter = 0;
        for (int i = 0; i < map[row].length - DOTS_TO_WIN + 1; i++) {
            // если вертикаль не вмещает нужное количество символов или
            // при нулевом счетчике горизонталь не содержит нужное количество символов - дальше не проверяем
            if (map.length - row < DOTS_TO_WIN || (counter == 0 && map[row].length - i < DOTS_TO_WIN)) return false;
            for (int j = row, k = i; j < row + DOTS_TO_WIN; j++, k++) {
                if (map[j][k] == symb) {
                    counter++;
                } else {
                    counter = 0;
                    break;
                }
            }
            if (counter == DOTS_TO_WIN) return true;
        }
        return false;
    }
    /** проверка победы по восходящей горизонтали в указанном ряду */
    public static boolean checkDiagonalUp(char symb, int row) {
        if (row < DOTS_TO_WIN) return false; // если нужное количество символов в диагонали не поместится - дальше не проверяем
        int counter = 0;
        for (int i = 0; i < map[row].length - DOTS_TO_WIN + 1; i++) {
            if (counter == 0 && map[row].length - i < DOTS_TO_WIN) return false;
            for (int j = row, k = i; j > row - DOTS_TO_WIN; j--, k++) {
                if (map[j][k] == symb) {
                    counter++;
                } else {
                    counter = 0;
                    break;
                }
            }
            if (counter == DOTS_TO_WIN) return true;
        }
        return false;
    }

    public static boolean checkWin(char symb) {
        for (int i = 0; i < map.length; i++) {
            if (checkRow(symb, i)) return true; // проверка горизонтали (слева направо)
            if (checkColumn(symb, i)) return true; // проверка вертикали (сверху вниз)
                                                // матрица равносторонняя!, второй цикл для столбцов не создаем, проходим в том же цикле
            if (checkDiagonalDown(symb, i)) return true;// проверка нисходящей диагонали (\)
            if (checkDiagonalUp(symb, i)) return true;// проверка восходящей диагонали (/)
        }
        return false;
    }

    public static void oTurn(char dot) {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (isCellValid(x, y) != 3);
        System.out.println("Ход " + dot + " в клетку: " + (x + 1) + " " + (y + 1));
        map[y][x] = dot;
    }

/*
      1     2     3
   ┌─────┬─────┬─────┐
1  │     │     │  0  │
   ├─────┼─────┼─────┤
2  │     │  X  │     │
   ├─────┼─────┼─────┤
3  │     │     │     │
   └─────┴─────┴─────┘
 рамки псевдографики на больших полях выглядит некрасиво */
}