package Lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    /* Комментарий: алгоритм - brute force перебор (рекурсивно) всех возможных вариантов поля на заданную глубину с
    определением результата по исходу игры. Использовал минимакс и "подобие" альфа-бета отсечения (в одномерном
    массиве). Подсчет проводился на выходе из рекурсии, т.е. не учитывает результаты других позиций, полученные на
    этом же уровне ветвления. А не зная что произошло в других ветвях, пропустить проверку лишних результатов невозможно
    (поэтому - "подобие" альфа-бета отсечение, отсекается только проверка результатов, но не перебор позиций).
    Как следствие - при увеличении размера поля и глубины проверки появляется и увеличивается задержка между ходами.

    P.S. Попытался реализовать "дерево" в отдельном классе (по материалам предыдущего занятия) - не получилось.
    С использованием ArrayList - также не получилось, запутался в уровнях и проверках.

    Идеи для реализации:
    // TODO: не проверяется, если получено нескольких лучших результатов
    // TODO: не прогнозируется результат, если не дошел до конца ветки (не хватило глубины проверки)
    // TODO: не нужно проверять все поле, достаточно проверить область на длину выигрышной линии вокруг занятых ячеек,
             и в первую очередь - вокруг последнего хода противника
     */

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static int SIZE = 3;             // размер поля и количество в ряд задаем константами
    public static int DOTS_TO_WIN = 3;      // условие победы - N знаков в ряд
    public static int SCORE_LEVEL = 10;     // уровень прогнозирования результата

    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_AI = 'O';  // чем ходит AI

    public static char[][] map = {          // массив для проверки, закомментировать initMap
            {DOT_X, DOT_O, DOT_X},
            {DOT_EMPTY, DOT_EMPTY, DOT_X},
            {DOT_O, DOT_EMPTY, DOT_EMPTY}};

    public static int[] arrBestResult = new int[SIZE * SIZE + 1];
    public static final int MAX_RES = SIZE * SIZE * 1000;
    public static int bestX, bestY, bestStrategy;

    public static void main(String[] args) {
        char player = DOT_O;                                // кто начинает игру
        char winner = DOT_EMPTY;                            // победитель
        char human = swithPlayer(DOT_AI);                   // пользователь играет за противоположный знак

        initMap();  // инициализация поля
        printMap(); // вывод поля с координатами

        /* количество ходов не может быть больше количества клеток. Можно использовать обычный цикл for,
        тогда массив будет заполнен только на последней итерации, а если определится победитель - выйти из цикла.
        Но в этом случае игра должна начинаться с чистого поля. Для проверки удобнее использовать while */
        int i = 0;
        while (true) {
            System.out.println("Ход N" + (i++ + 1));
            // ход игрока или программный
            if (player == DOT_AI) {
                aTurn(DOT_AI);
            } else {
                hTurn(human);
            }
            printMap(); // вывести поле

            // если есть победитель - запомнить и выйти из цикла
            if (scoreBoard(player) == MAX_RES) {
                winner = player;
                break;
            }
            // если поле заполнено - выйти из цикла
            if (isBoardFull()) break;
            player = swithPlayer(player); // переход хода
        }
        showWinner(winner); // вывести в консоль победителя
    }

    /**
     * переход хода к другому игроку
     */
    private static char swithPlayer(char player) {
        return (player == DOT_X) ? DOT_O : DOT_X; // смена хода
    }

    /**
     * создать "пустое" игровое поле
     */
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /** подсчет максимального разряда числа (для выравнивания в координатной сетке) */
    public static int decimalPlaces(int num) {
        int res = 0;
        while (num / 10 > 0) {
            res++;
            num /= 10;
        }
        return ++res;
    }

    /** вывести массив с координатами в консоль,
        чтобы сетка "не съезжала" при увеличении разряда в числах координат > 10, > 100, и т.д. */
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

    /** вывод в консоль победителя игры */
    private static void showWinner(char winner) {
        System.out.print("Игра закончена. ");
        switch (winner) {
            case DOT_EMPTY -> System.out.println("Победителя нет - ничья."); // победитель не определился, исходное значение
            case DOT_X -> System.out.println("Победили КРЕСТИКИ");
            case DOT_O -> System.out.println("Победили НОЛИКИ");
        }
    }

    /** проверка возможности сделать ход в клетку с указанными координатами */
    public static int isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return 1; // выходит за пределы массива
        if (map[y][x] == DOT_EMPTY) return 3; // клетка не занята
        return 2; // в остальных случаях - клетка занята
    }

    /** проверка, помещается ли на поле строка, начинающаяся с X,Y (с учетом DOTS_TO_WIN) */
    private static boolean isLineValid(int x, int y, int deltaX, int deltaY, char symbol) {
        int lastX = x + (DOTS_TO_WIN - 1) * deltaX; // координата X последней точки в ряду
        int lastY = y + (DOTS_TO_WIN - 1) * deltaY; // координата Y последней точки в ряду
        if (lastX < 0 || lastX >= SIZE || lastY < 0 || lastY >= SIZE) return false; // выходит за пределы массива
        return true;
    }

    /** проверка, есть ли на поле свободные ячейки */
    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    /**
     * проверка последовательности символов в линии
     *
     * @param x, y - координаты начала линии
     * @param deltaX, deltaY - направление сдвига по осям X и Y; значения: 0 - нет сдвига, 1 - сдвиг на увеличение, -1 - на уменьшение
     * @param symbol  - проверяемый символ, значения: DOT_X | DOT_O
     *                DOTS_TO_WIN - количество подряд идущих символов, длина проверяемого ряда
     * @return - количество очков (с учетом коэффициентов)
     */
    private static int scoreLine(int x, int y, int deltaX, int deltaY, char symbol) {
        int resSelf = 0; // количество своих символов в ряду
        int resOpponent = 0; // количество символов противника в ряду
        int resEmpty = 0; // количество пустых символов в ряду
        int multiplier;  // повышающий коэффициент
        char chTemp; // временная переменная для цикла сравнения и подсчета количества в ряду

        if (!isLineValid(x, y, deltaX, deltaY, symbol))
            return 0; // если строка не помещается на поле - 0, дальше не проверяем

        for (int i = 0; i < DOTS_TO_WIN; i++) { // перебор символов в ряду и проверка на соответствие
            int nextX = x + i * deltaX;
            int nextY = y + i * deltaY;

            chTemp = map[nextY][nextX];
            if (chTemp == symbol) {
                resSelf++;
            } else if (chTemp == DOT_EMPTY) {
                resEmpty++;
            } else {
                resOpponent++;
            }
        }
        // определяем коэффициент: если есть "блокирующий" символ противника = 1; если нет блокирующих символов,
        // но есть пропущенные ячейки (пустые) = 5; если все символы в ряду свои = 10
        if (resOpponent > 0) {
            multiplier = 1;
        } else if (resEmpty > 0) {
            multiplier = 5;
        } else {
            multiplier = 10;
        }
        return multiplier * resSelf; // подсчет очков и результат
    }

    /** проверка победителя и подсчет суммы очков
     *  если в ряду найдено нужное количество подряд идущих символов - возвращаем MAX_RES, выходим из метода */
    private static int scoreBoard(char symbol) {
        int sumScore = 0;
        int resHorizontal, resVertical, resDiagonalUp, resDiagonalDown;
        for (int i = 0; i < SIZE; i++) { // проход по оси Y
            for (int j = 0; j < SIZE; j++) { //проход по оси X
                resHorizontal = scoreLine(j, i, 1, 0, symbol);  // проверка горизонтали (слева направо)
                if (resHorizontal == DOTS_TO_WIN * 10) return MAX_RES;
                resVertical = scoreLine(j, i, 0, 1, symbol);    // проверка вертикали (сверху вниз)
                if (resVertical == DOTS_TO_WIN * 10) return MAX_RES;
                resDiagonalUp = scoreLine(j, i, 1, 1, symbol);  // проверка восходящей диагонали (/)
                if (resDiagonalUp == DOTS_TO_WIN * 10) return MAX_RES;
                resDiagonalDown = scoreLine(j, i, 1, -1, symbol); // проверка нисходящей диагонали (\)
                if (resDiagonalDown == DOTS_TO_WIN * 10) return MAX_RES;
                sumScore = sumScore + resHorizontal + resVertical + resDiagonalUp + resDiagonalDown;
            }
        }
        return sumScore;
    }

    /** перебор всех вариантов от текущей позиции на доске на заданное количество ходов и подсчет очков
        0 - нет значения (не проверено после инициализации или очистки массива)
        1 - проиграл
        2 - непонятно (не дошел до конца ветки)
        3 - ничья, заполнилось поле
        4 - выиграл
     */
    public static void scoreMoves(char player, int levelDepth) {
        int currentRes;
        if (levelDepth > SCORE_LEVEL || isBoardFull()) return;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(j, i) == 3) { // можно сделать ход
                    levelDepth++; // увеличить счетчик уровня проверки

                    map[i][j] = player; // делаем ход
                    currentRes = scoreBoard(player); // подсчет очков после хода

                    if (currentRes == MAX_RES) { //есть победитель - дальше не проверяем
                        currentRes = (player == DOT_AI) ? 4 : 1;
                    } else if (isBoardFull()) { // доска заполнилась - ничья
                        currentRes = 3;
                    } else { // неопределенная позиция - проверяем ветку дальше
                        currentRes = 2;
                        player = swithPlayer(player);       // меняем игрока
                        scoreMoves(player, levelDepth);     // рекурсивный вызов следующего хода
                        player = map[i][j]; // восстанавливаем значение игрока, чтобы не сбиться после выхода из "пустой" рекурсии
                    }

                    arrBestResultValue(player, j, i, currentRes, levelDepth); // сравнить результат и сохранить в массив

                    // проверка результатов
                    // System.out.println("Глубина: " + levelDepth + ", результат: " + currentRes + Arrays.toString(arrBestResult) + " bestStrategy: " + bestStrategy);

                    // запоминаем координаты первого хода, который приведет к ничье или победе
                    // если сначала запомнили ход к ничье (очки = 3), то при нахождении выигрышной стратегии координаты обновятся
                    if (levelDepth == 1) {
                        /* если нашли подходящую цепочку (ничья или выигрыш); при этом, если нашли выигрышную цепочку - больше координаты не меняем */
                        if (arrBestResult[1] >= 3 & arrBestResult[1] > bestStrategy) {
                            bestX = j; bestY = i;                        // координаты хода
                            bestStrategy = arrBestResult[1];             // вариант стратегии
                        }
                    }

                    map[i][j] = DOT_EMPTY; // откат изменений поля
                    levelDepth--; // уменьшаем счетчик уровня ветвлений
                }
            }
        }
    }

    // сравниваем значения одного уровня ветвления или передаем их вверх
    private static void arrBestResultValue(char player, int x, int y, int res, int levelDepth) {
        // если неопределенная / промежуточная позиция (2) - подтягиваем значение с предыдущего уровня
        if (res == 2) {
            res = arrBestResult[levelDepth + 1];
        }
        // если значение пустое (точнее 0) - просто сохраняем
        if (arrBestResult[levelDepth] == 0) {
            arrBestResult[levelDepth] = res;
        } else if (player == DOT_AI) { // если мой ход (ищем бОльший результат)
            if (arrBestResult[levelDepth] < res) {
                arrBestResult[levelDepth] = res; // сохраняем значение, которое больше
            }
        } else { // если ход противника (ищем меньший результат)
            if (arrBestResult[levelDepth] > res) {
                arrBestResult[levelDepth] = res; // сохраняем значение, которое меньше
            }
        }
        // очищаем нижележащие уровни
        arrBestClear(levelDepth);
    }

    /** очистка массива с подсчитанными очками (заполнение нулями) начиная с позиции, следующей ПОСЛЕ pos и до конца */
    private static void arrBestClear(int pos) {
        for (int i = ++pos; i < arrBestResult.length; i++) {
            arrBestResult[i] = 0;
        }
    }

    /**программный ход */
    public static void aTurn(char dot) {
        int x, y;

        bestX = -1; bestY = -1; bestStrategy = 0; // очищаем переменные для "выигрышного" хода
        arrBestClear(0);  // очищаем массив для подсчета очков
        scoreMoves(dot, 0); // ищем лучший ход

        if (arrBestResult[1] == 4) {
            System.out.println();
        }

        if (bestStrategy != 0) {                         // стратегия определена
            x = bestX; y = bestY;                        // координаты хода
        } else {                                         // если выигрышный ход не найден - рандомные координаты
            System.out.println("Использованы случайные координаты");
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (isCellValid(x, y) != 3);
        }
        System.out.println("Ход " + dot + " в клетку: " + (x + 1) + " " + (y + 1) + ". bestX " + bestX + " bestY " + bestY + " bestStrategy " + bestStrategy);
        map[y][x] = dot;
    }

    /** ход по координатам, введенным вручную (ход пользователя / "человека") */
    public static void hTurn(char dot) {
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
}