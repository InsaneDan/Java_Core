package Lesson3.homework;

import java.util.*;

public class UniqueWords {

    static String[] arr = {"один", "ДВА", "два", "три", "четыре", "пять", "пяТЬ", "ПЯть", "шесть", "семь", "восемь", "девять", "Десять", "десять"};

    public static void main(String[] args) {
        Set<String> uniques = new HashSet<>();
        Map<String, Integer> replicas = new HashMap<>();

        for (String a : arr) {
            a = a.toLowerCase(); // приводим к нижнему регистру, чтобы упростить сравнение: ДВА = два = дВа и т.д.
            if (!uniques.add(a)) {
                if (!replicas.containsKey(a)) {
                    // если ключа нет в коллекции - добавляем и инициируем значение счетчика = 2;
                    // т.к. первое встреченное слово в массиве сначала попадает в коллекцию uniques и не добавляется в replicas,
                    // то это - первый дубликат, но второе (повторяющееся) слово в массиве
                    replicas.put(a, 2);
                } else {
                    // такой ключ есть в коллекции - увеличиваем счетчик
                    replicas.put(a, replicas.get(a) + 1);
                }
            }
        }

        System.out.println("Уникальные слова в массиве: " + uniques);
        System.out.println("Из них повторяются:");
        for (Map.Entry<String, Integer> entry : replicas.entrySet()) {
            System.out.println("Слово '" + entry.getKey() + "', количество повторов = " + entry.getValue());
        }
    }
}

/* КОНСОЛЬ
"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=63970:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\GeekBrains\JAVA\JAVA 2\target\classes" Lesson3.homework.UniqueWords
Уникальные слова в массиве: [шесть, два, пять, один, девять, три, семь, восемь, десять, четыре]
Из них повторяются:
Слово 'два', количество повторов = 2
Слово 'пять', количество повторов = 3
Слово 'десять', количество повторов = 2

Process finished with exit code 0
 */