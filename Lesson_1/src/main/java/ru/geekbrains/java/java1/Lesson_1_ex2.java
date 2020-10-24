package ru.geekbrains.java.java1;

public class Lesson_1_ex2 {

// 2. Создать переменные всех пройденных типов данных, и инициализировать их значения;

    public static void main(String[] args) {
//      примитивные типы данных
//      - целочисленные:
        byte aByte = -128, zByte = 127; // 1 байт
        short aShort = -32768, zShort = 32767; // 2 байта
        int aInt = -2147483648, bInt = 2147483647; // 4 байта
        long aLong = -9_223_372_036_854_775_807L, bLong = 9223*10^15; // 8 байт
//      - вещественные числа (дробные):
        double xDouble = -4.90000000000000000000001, yDouble = 1.80000000000000000000001; // 8 байт от ~4,9*10^(-324) до ~1,8*10^308
        float x = -3.4F, y = 3.4F; // 4 байта ±3.4*10^38
//      - логические:
        boolean bl0 = false, bl1 = true;
//      - символы - номер (целочисленный) символа в таблице символов Unicode (UTF-16) от 0 до 65535:
        char aChar = '0', bChar = 30, cChar = '\u0030'; // 2 байта

//      + разные системы счисления:
        int hInt16 = 0x10, oInt8 = 020, bInt2 = 10000; // = 16
//      + разные методы инициализации
        int num;
        num = 16;

//      ссылочные типы данных, в том числе - String:
        String sSomeText1 = new String("Здесь должен быть какой-то текст");
        String sSomeText2 = "Здесь должен быть какой-то текст"; // только для String, другие классы не поддерживают
    }

}
