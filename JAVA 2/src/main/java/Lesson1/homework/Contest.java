package Lesson1.homework;

import Lesson1.homework.contestants.Cat;
import Lesson1.homework.contestants.Contestant;
import Lesson1.homework.contestants.Human;
import Lesson1.homework.contestants.Robot;
import Lesson1.homework.tasks.Task;
import Lesson1.homework.tasks.Track;
import Lesson1.homework.tasks.Wall;

import java.util.Random;

public class Contest {

    public static void main(String[] args) {

        Random random = new Random();

        // массив участников
        Contestant[] contestants = {
                new Cat("Котяра", 200 + random.nextInt(300), 2 + random.nextInt(10) / 10),
                new Human("Иван", 400 + random.nextInt(600), 1 + random.nextInt(5) / 10),
                new Robot("R2B2", 800 + random.nextInt(200), 1 + random.nextInt(10) / 10),
        };

        // массив препятствий: (псевдо) случайный выбор препятствия + небольшая вариабельность значений
        int count = 5;
        Task[] tasks = new Task[count];
        for (int i = 0; i < count; i++) {
            if (random.nextInt(100) % 2 == 0) {
                tasks[i] = new Track(random.nextInt(1000));
            } else {
                tasks[i] = new Wall(0.5 + random.nextInt(15)/10);
            }
        }

        System.out.println("\nПрохождение препятствий\n");
        // перебор во вложенных циклах, препятствия (этапы) проходят все участники по очереди
        // если препятствие не пройдено - удаляем объект из массива (null), вывод в консоль
        // перед прохождением препятствия сначала проверяем элемент массива на null
        for (int i = 0; i < tasks.length; i++) {
            System.out.printf("Этап %d \n", i + 1);
            for (int j = 0; j < contestants.length; j++) {
                if (contestants[j] != null && !tasks[i].performTask(contestants[j])) {
                    System.out.println("Участник выбывает из состязаний!");
                    contestants[j] = null;
                }
            }
        }

        // подводим итоги - количество непустых ячеек массива и формируем строку из имен оставшихся участников
        String str = "";
        int j = 0;
        for (int i = 0; i < contestants.length; i++) {
            if (contestants[i] != null) {
                str = str + ", " + contestants[i].getInfo();
                contestants[i] = null;
                j++;
            }
        }

        // финальное сообщение
        System.out.println();
        if (j != 0) {
            str = str.substring(3);
            System.out.printf("Осталось участников - %d \n" + str, j);
        } else {
            System.out.printf("Все участники выбыли из состязаний");
        }
        System.out.println();
    }
}

/* КОНСОЛЬ

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=65433:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\GeekBrains\JAVA\JAVA 2\target\classes" Lesson1.homework.Contest

Прохождение препятствий

Этап 1
Высота стенки 0,5 м. Кот Котяра может прыгнуть на 2,0 м. Участник успешно перепрыгнул препятствие.
Высота стенки 0,5 м. Человек Иван может прыгнуть на 1,0 м. Участник успешно перепрыгнул препятствие.
Высота стенки 0,5 м. Робот R2B2 может прыгнуть на 1,0 м. Участник успешно перепрыгнул препятствие.
Этап 2
Высота стенки 0,5 м. Кот Котяра может прыгнуть на 2,0 м. Участник успешно перепрыгнул препятствие.
Высота стенки 0,5 м. Человек Иван может прыгнуть на 1,0 м. Участник успешно перепрыгнул препятствие.
Высота стенки 0,5 м. Робот R2B2 может прыгнуть на 1,0 м. Участник успешно перепрыгнул препятствие.
Этап 3
Высота стенки 0,5 м. Кот Котяра может прыгнуть на 2,0 м. Участник успешно перепрыгнул препятствие.
Высота стенки 0,5 м. Человек Иван может прыгнуть на 1,0 м. Участник успешно перепрыгнул препятствие.
Высота стенки 0,5 м. Робот R2B2 может прыгнуть на 1,0 м. Участник успешно перепрыгнул препятствие.
Этап 4
Длина беговой дорожки 936 м. Кот Котяра может пробежать 437 м. Участник не пробежал дистанцию.
Участник выбывает из состязаний!
Длина беговой дорожки 936 м. Человек Иван может пробежать 680 м. Участник не пробежал дистанцию.
Участник выбывает из состязаний!
Длина беговой дорожки 936 м. Робот R2B2 может пробежать 914 м. Участник не пробежал дистанцию.
Участник выбывает из состязаний!
Этап 5

Все участники выбыли из состязаний

Process finished with exit code 0
 */