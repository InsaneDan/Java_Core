package Lesson2.homework;

public class Lesson2Homework {

    static int arrSize = 4;
    public static void main(String[] args) {
        // массив 4*4 с числами в строковом представлении
        String[][] array0 = {
                {"01", "02", "03", "04"},
                {"05", "06", "07", "08"},
                {"09", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        // без одной строки (нарушена 1 размерность)
        String[][] array1 = {
                {"01", "02", "03", "04"},
                {"05", "06", "07", "08"},
                {"09", "10", "11", "12"}
        };
        // с дополнительными столбцами в одной из строк (нарушена 2 размерность)
        String[][] array2 = {
                {"01", "02", "03", "04"},
                {"05", "06", "07", "08", "08", "08"},
                {"09", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        // содержит неконвертируемые в числа значения
        String[][] array3 = {
                {"01", "02", "03", "04"},
                {"05", "06", "07", "08"},
                {"AA", "BB", "CC", "DD"},
                {"13", "14", "15", "16"}
        };

        System.out.println("Сумма всех чисел в массиве: " + checkArray(array0));
    }

    static int checkArray(String[][] arrayChecked) throws MyArrayDataException, MySizeArrayException {
        int result = 0;
        // проверка, что массив не пустой
        if (arrayChecked == null)
            throw new MySizeArrayException("Массив не заполнен");

        // проверка количества строк
        if (arrayChecked.length != arrSize)
            throw new MySizeArrayException("Количество строк в массиве не соответствует условию");

        // проход в цикле по строкам
        for (int i = 0, arrayCheckedLength = arrayChecked.length; i < arrayCheckedLength; i++) {

            // проверка столбцов в каждой строке:
            // проверка, что в первой строке не null
            if (arrayChecked[i] == null)
                throw new MySizeArrayException("Значения в " + (i + 1) + " строке не заполнены");
            // проверка количества столбцов
            if (arrayChecked[i].length != arrSize)
                throw new MySizeArrayException("Количество столбцов в " + (i + 1) + " строке не соответствует условию");

            // преобразуем и суммируем значения
            for (int j = 0; j < arrSize; j++) {
                try {
                    result += Integer.parseInt(arrayChecked[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException("Указано нечисловое значение: строка " + (i + 1) + ", столбец " + (j + 1));
                }
            }
        }
        return result;
    }
}

class MySizeArrayException extends ArrayIndexOutOfBoundsException {
    public MySizeArrayException(String msg) {
        super(msg);
    }
}

class MyArrayDataException extends NumberFormatException {
    public MyArrayDataException(String msg) {
        super(msg);
    }
}