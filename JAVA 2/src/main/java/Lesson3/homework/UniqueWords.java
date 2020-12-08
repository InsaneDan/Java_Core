package Lesson3.homework;

import java.util.HashMap;

public class UniqueWords {

    static String[] arr = {"один", "ДВА", "два", "три", "четыре", "пять", "пяТЬ", "ПЯть", "шесть", "семь", "восемь", "девять", "Десять", "десять"};

    public static void main(String[] args) {
        HashMap<String, Integer> words = new HashMap<>();
        for (String element : arr) {
            element = element.toLowerCase();
            words.merge(element, 1, Integer::sum);
        }
        words.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

/* КОНСОЛЬ
"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=59564:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\GeekBrains\JAVA\JAVA 2\target\classes" Lesson3.homework.UniqueWords
шесть: 1
два: 2
пять: 3
один: 1
девять: 1
три: 1
восемь: 1
семь: 1
десять: 2
четыре: 1

Process finished with exit code 0
 */