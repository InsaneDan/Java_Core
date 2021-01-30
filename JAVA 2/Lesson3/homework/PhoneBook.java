package Lesson3.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class PhoneBookMap {

    Map<String, String> phoneBook;

    // создание новой коллекции при создании объекта класса
    PhoneBookMap() {
        phoneBook = new HashMap<>();
    }

    // добавить имя-телефон
    public void addRecord(String name, String phone) {
        phoneBook.put(phone, name);
    }

    // получить список телефонов по имени
    public ArrayList<String> getPhone(String name) {
        ArrayList<String> list = new ArrayList<>();                     // инициализировать переменную ArrayList
        for (Map.Entry<String, String> entry : phoneBook.entrySet())    // пройти по всем элементам коллекции,
            if (name.equals(entry.getValue()))                          // если значение (name) равны,
                list.add(entry.getKey());                               // то добавить в список поле ключа (phone);
        return list;                                                    // вернуть список номеров телефонов
    }
}

public class PhoneBook {

    public static void main(String[] args) {
        PhoneBookMap phoneBookExample = new PhoneBookMap();
        phoneBookExample.addRecord ("Иванов", "+7 999 111 123456");
        phoneBookExample.addRecord ("Петров", "+7 999 222 123456");
        phoneBookExample.addRecord ("Петров", "+7 999 333 123456");
        phoneBookExample.addRecord ("Сидоров", "+7 999 444 123456");
        phoneBookExample.addRecord ("Сидоров", "+7 999 555 123456");
        phoneBookExample.addRecord ("Сидоров", "+7 999 666 123456");
        phoneBookExample.addRecord ("Сидоров", "+7 999 777 123456");

        String name = "Иванов";
        System.out.println(name + ": " + phoneBookExample.getPhone(name));
        name = "Петров";
        System.out.println(name + ": " + phoneBookExample.getPhone(name));
        name = "Сидоров";
        System.out.println(name + ": " + phoneBookExample.getPhone(name));
    }
}

/* КОНСОЛЬ
"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=63964:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\GeekBrains\JAVA\JAVA 2\target\classes" Lesson3.homework.PhoneBook
Иванов: [+7 999 111 123456]
Петров: [+7 999 222 123456, +7 999 333 123456]
Сидоров: [+7 999 444 123456, +7 999 555 123456, +7 999 666 123456, +7 999 777 123456]

Process finished with exit code 0
 */