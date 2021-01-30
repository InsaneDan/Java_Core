package Lesson8;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

enum Values {
    // MINE_HIDDEN и MINE_MARKED - производные переменные (для совместимости при установке и снятии флажка)
    EMPTY(0), MINE(9), UNTOUCHED(10), MARKED(11), MISTAKE(12),
    MINE_HIDDEN(Values.MINE.getValue() + Values.UNTOUCHED.getValue()),
    MINE_MARKED(Values.MINE_HIDDEN.getValue() + Values.MARKED.getValue());

    private int value;

    Values(int value) {
        this.value = value;
    }

    public int getValue() {return value;}
}

public class Board extends JPanel {

    private final int IMAGES_COUNT = 13;    // количество картинок для массива (если вдруг захотим что-нибудь добавить/убавить)
    private final int CELL_SIZE = 25;       // размер картинки для расчета размера игрового поля

    // интерфейс
    // TODO: 20.11.2020 меню для выбора уровня сложности (размер поля / количество мин)
    private final int MINES_TOTAL = 20;     // количество мин на поле
    private final int NUM_COLUMNS = 16;     // количество столбцов на поле
    private final int NUM_ROWS = 16;        // количество строк на поле
    // абсолютные размеры игрового поля
    private final int BOARD_WIDTH = NUM_COLUMNS * CELL_SIZE + 1;
    private final int BOARD_HEIGHT = NUM_ROWS * CELL_SIZE + 1;

    private int[] arrBoardVisible; // массив игрового поля, который видит игрок
    private Image[] image;      // массив картинок
    private int minesLeft;      // счетчик оставшихся мин
    private boolean isPlaying;  // процесс игры

    private int allCells;       //

    private final JLabel statusbar;

    public Board(JLabel statusbar) {
        this.statusbar = statusbar;
        initFrame();
    }

    // готовим игровое поле: заполняем массив значениями неоткрытых ячеек, расставляем мины и числа в ячейках
    private void setBoard() {
        var random = new Random();
        isPlaying = true;                               // игра начата и еще в процессе, не закончена

        arrBoardVisible = new int[NUM_ROWS * NUM_COLUMNS]; // одномерный массив игрового поля

        System.out.println(Values.UNTOUCHED.getValue());

        for (int i = 0; i < arrBoardVisible.length; i++) {            // заполняем значениями неоткрытых ячеек (UNTOUCHED = 10)
            arrBoardVisible[i] = Values.UNTOUCHED.getValue();
        }

        minesLeft = MINES_TOTAL;                        // счетчик оставшихся мин
        statusbar.setText("Осталось флажков: " + Integer.toString(minesLeft)); // выводим в статус-бар

        // заполняем поле - крутим цикл, пока не кончатся все мины;
        // ставим мину, затем увеличиваем значение в соседних ячейках, если в них нет мин
        int i = 0;
        while (i < MINES_TOTAL) {

            int position = (int) (allCells * random.nextDouble()); // рандомная позиция мины в одномерном массиве
            // если эта позиция не занята, то вносим значение в массив, иначе уходим в следующую итерацию
            if (arrBoardVisible[position] != Values.MINE_HIDDEN.getValue()) {
                i++; // мина установлена - увеличиваем счетчик
                arrBoardVisible[position] = Values.MINE_HIDDEN.getValue();
                setNumbers (position);
            }
        }
    }

    /**
     * Проверка ячеек вокруг мины. Если в соседней ячейке не мина, а число, то увеличиваем его значение на 1.
     * Note. При добавлении следующей мины, она заменит число, вместо него будет мина, а соседние - снова увеличатся.
     * @param position - позиция, в которую установлена мина
     */
    private void setNumbers (int position) {
        allCells = NUM_ROWS * NUM_COLUMNS; //количество клеток на поле
        int cell; // порядковый номер ячейки в одномерном массиве
        int column = position % NUM_COLUMNS; // номер столбца на поле

        // клетка над миной (сдвиг влево на один ряд ±0) - с проверкой, что не вышли за верхнюю границу поля
        cell = position - NUM_COLUMNS;
        if (cell >= 0) { cellIncreaseByOne(cell); }

        // клетка под миной (сдвиг вправо на один ряд ±0) - с проверкой, что не вышли за нижнюю границу поля
        cell = position + NUM_COLUMNS;
        if (cell < allCells) { cellIncreaseByOne(cell); }

        // три клетки слева от мины (-1) - сначала проверить, что не вышли за левую границу поля, потом - верхняя и нижняя границы
        if (column > 0) {
            cell = position - NUM_COLUMNS - 1;              // левая верхняя ячейка
            if (cell >= 0) { cellIncreaseByOne(cell); }
            cell = position - 1;                            // левая средняя
            if (cell >= 0) { cellIncreaseByOne(cell); }
            cell = position + NUM_COLUMNS - 1;              // левая нижняя
            if (cell < allCells) { cellIncreaseByOne(cell); }
        }

        // три клетки справа от мины (+1) - проверить, что не вышли за правую границу поля, потом - верхняя и нижняя
        if (column < (NUM_COLUMNS - 1)) {
            cell = position - NUM_COLUMNS + 1;              // правая верхняя ячейка
            if (cell >= 0) { cellIncreaseByOne(cell); }
            cell = position + NUM_COLUMNS + 1;              // правая средняя ячейка
            if (cell < allCells) { cellIncreaseByOne(cell); }
            cell = position + 1;                            // правая нижняя ячейка
            if (cell < allCells) { cellIncreaseByOne(cell); }
        }
    }

    //проверяем, что в ячейке не мина и увеличиваем значение этой ячейки на единицу
    private void cellIncreaseByOne(int cell) {
        if (arrBoardVisible[cell] != Values.MINE_HIDDEN.getValue()) {
            arrBoardVisible[cell]++;
        }
    }

    /**
     * Проверка ячеек вокруг заданной клетки (методу setNumbers).
     * Если ячейка пустая (содержит 0) - рекурсивный вызов.
     * Т.о. будут открыты все пустые ячейки, и ближайший к ним ряд с цифрами.
     * @param position - позиция, в которой проверяем пустое значение.
     */
    private void openEmptyCells(int position) {
        allCells = NUM_ROWS * NUM_COLUMNS; //количество клеток на поле
        int cell; // порядковый номер ячейки в одномерном массиве
        int column = position % NUM_COLUMNS; // номер столбца на поле

        // клетка сверху
        cell = position - NUM_COLUMNS;
        if (cell >= 0) {
            if (cellOpenRepeat(cell)) { openEmptyCells(cell); }
        }

        // клетка снизу
        cell = position + NUM_COLUMNS;
        if (cell < allCells) {
            if (cellOpenRepeat(cell)) { openEmptyCells(cell); }
        }

        // три клетки слева
        if (column > 0) {
            cell = position - NUM_COLUMNS - 1;              // левая верхняя
            if (cell >= 0) {
                if (cellOpenRepeat(cell)) { openEmptyCells(cell); }
            }
            cell = position - 1;                            // левая средняя
            if (cell >= 0) {
                if (cellOpenRepeat(cell)) { openEmptyCells(cell); }
            }
            cell = position + NUM_COLUMNS - 1;              // левая нижняя
            if (cell < allCells) {
                if (cellOpenRepeat(cell)) { openEmptyCells(cell); }
            }
        }

        // три клетки справа
        if (column < (NUM_COLUMNS - 1)) {
            cell = position - NUM_COLUMNS + 1;              // правая верхняя
            if (cell >= 0) {
                if (cellOpenRepeat(cell)) { openEmptyCells(cell); }
            }
            cell = position + NUM_COLUMNS + 1;              // правая средняя
            if (cell < allCells) {
                if (cellOpenRepeat(cell)) { openEmptyCells(cell); }
            }
            cell = position + 1;                            // правая нижняя
            if (cell < allCells) {
                if (cellOpenRepeat(cell)) { openEmptyCells(cell); }
            }
        }
    }

    //"открыть" ячейку (вычесть значение UNTOUCHED)
    private void cellOpen(int cell) {
        if (arrBoardVisible[cell] >= Values.UNTOUCHED.getValue()) {
            arrBoardVisible[cell] -= Values.UNTOUCHED.getValue();
        }
    }

    // после того, как открыли ячейку, определяем - нужна рекурсия или нет
    private boolean cellOpenRepeat(int cell) {
        if (arrBoardVisible[cell] >= Values.UNTOUCHED.getValue()) {
            cellOpen(cell);
            if (arrBoardVisible[cell] == Values.EMPTY.getValue()) return true;
        }
        return false;
    }

    // инициализация фрейма
    private void initFrame() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT)); // размер
        image = new Image[IMAGES_COUNT]; // массив картинок, индекс соответствует имени
        for (int i = 0; i < IMAGES_COUNT; i++) { // заполнение массива
            String path = "src/main/resources/Lesson8/" + i + ".png";
            image[i] = (new ImageIcon(path)).getImage();
        }
        addMouseListener(new MinerMouseAdapter()); // "подключение" мыши
        setBoard(); // заполняем поле
    }

    /* Рисуем элементы поля в зависимости от значений ячеек массива,
     * ячейки перебираем последовательно в двух вложенных циклах.
     */
    @Override
    public void paintComponent(Graphics g) {

        int uncover = 0;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {

                int cell = arrBoardVisible[(i * NUM_COLUMNS) + j];

                if (isPlaying && cell == Values.MINE.getValue()) {
                    isPlaying = false; // мина, игра закончилась
                }

                if (!isPlaying) {
                    // если игра завершилась, то "открываем" поле - оставляем минимальные значения без UNTOUCHED
                    if (cell == Values.MINE_HIDDEN.getValue()) {
                        cell = Values.MINE.getValue();
                    } else if (cell == Values.MINE_MARKED.getValue()) {
                        cell = Values.MARKED.getValue();
                    } else if (cell > Values.MINE_HIDDEN.getValue()) {
                        cell = Values.MISTAKE.getValue();
                    } else if (cell > Values.MINE.getValue()) {
                        cell = Values.UNTOUCHED.getValue();
                    }

                } else {
                    // если игра еще в процессе, то временно переоределяем текущее значение ячейки из массива и
                    // выбираем картинку для отмеченных (MARKED) и неоткрытых (UNTOUCHED) клеток
                    if (cell > Values.MINE_HIDDEN.getValue()) {
                        cell = Values.MARKED.getValue();
                    } else if (cell > Values.MINE.getValue()) {
                        cell = Values.UNTOUCHED.getValue();
                        uncover++; // считаем неоткрытые клетки, если их нет - игра закончилась
                    }
                }

                // рисуем картинку
                g.drawImage(image[cell], (j * CELL_SIZE), (i * CELL_SIZE), this);
            }
        }

        // все клетки открыты и игра еще в процессе - завершаем игру, выводим результат в статус-бар
        if (uncover == 0 && isPlaying) {
            isPlaying = false; // меняем метку, что игра закончилась
            statusbar.setText("ПОБЕДА. Ты настоящий сапёр.");
        } else if (!isPlaying) {
            statusbar.setText("НЕ ПОВЕЗЛО. Сапёры могут ошибиться лишь один раз в жизни.");
        }
    }

    // Отслеживаем нажатие мыши
    private class MinerMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            // координаты
            int x = e.getX();
            int y = e.getY();
            // пересчитываем в координаты ячеек поля
            int nColumn = x / CELL_SIZE;
            int nRow = y / CELL_SIZE;

            boolean repaint = false; // true - был клик на поле и надо перерисовать
            // проверка, что координаты в пределах игрового поля
            if ((x < NUM_COLUMNS * CELL_SIZE) && (y < NUM_ROWS * CELL_SIZE)) {
                // левый клик - проверяем значения в ячейках
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // клик на флажок - нет действия
                    if (arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] > Values.MINE_HIDDEN.getValue()) {
                        return;
                    }
                    // клик на закрытую ячейку: значения от 10 до 18 - "открываем", ставим отметку о перерисовке
                    if ((arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] > Values.MINE.getValue()) && (arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] < Values.MINE_MARKED.getValue())) {
                        cellOpen((nRow * NUM_COLUMNS) + nColumn);
                        repaint = true; // обновить поле
                        // если открыли мину - флаг о завершении игры
                        if (arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] == Values.MINE.getValue()) {
                            isPlaying = false;
                        }
                        // если открыли пустую клетку - рекурсивное открытие всех пустых
                        if (arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] == Values.EMPTY.getValue()) {
                            openEmptyCells((nRow * NUM_COLUMNS) + nColumn);
                        }
                    }
                // правый клик - добавить/снять флажок
                } else  if (e.getButton() == MouseEvent.BUTTON3) {
                    // проверяем, что ячейка не открыта
                    if (arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] > Values.MINE.getValue()) {
                        repaint = true; // обновить поле
                        // в ячейке нет флажка - устанавливаем
                        if (arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] <= Values.MINE_HIDDEN.getValue()) {
                            // проверить, что мины еще не закончились
                            if (minesLeft > 0) {
                                arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] += Values.MARKED.getValue(); //добавить значение в массив
                                minesLeft--; // уменьшить счетчик
                                String msg = "Осталось флажков: " + Integer.toString(minesLeft); // обновить статус-бар
                                statusbar.setText(msg);
                            } else {
                                statusbar.setText("Флажки закончились");
                            }
                        // в ячейке есть флажок - снимаем
                        } else {
                            arrBoardVisible[(nRow * NUM_COLUMNS) + nColumn] -= Values.MARKED.getValue(); // меняем значение в массиве
                            minesLeft++; // увеличиваем счетчик мин
                            String msg = "Осталось флажков: " + Integer.toString(minesLeft); // обновляем статус-бар
                            statusbar.setText(msg);
                        }
                    }
                }
                // обновление поля
                if (repaint) { repaint(); }
            }
        }
    }
}
