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

    private static final int taskAmount = 5; // int count = 5; из метода main

    public static void main(String[] args) {

        Random random = new Random();

        // массив участников
        Contestant[] contestants = {
                new Cat("Котяра", 200 + random.nextInt(300), 2 + random.nextDouble()),
                new Human("Иван", 400 + random.nextInt(600), .7 + random.nextDouble()),
                new Robot("R2B2", 800 + random.nextInt(200), 3 + random.nextDouble()),
        };

        // массив препятствий: (псевдо) случайный выбор препятствия + небольшая вариабельность значений
        // int count = 5; // OKrylov: лучше вынести в static final поле класса - константу. Говорящие название констант лучше, чем просто цифры по коду (в этом примере в целом и так ок)
        Task[] tasks = new Task[taskAmount];
        for (int i = 0; i < taskAmount; i++) {
            if (random.nextInt(100) % 2 == 0) {
                tasks[i] = new Track(random.nextInt(1000));
            } else {
                tasks[i] = new Wall(0.5 + random.nextDouble() * 2);
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
        // String str = ""; // OKrylov: лучше переименовать в winnersInfo
        // OKrylov: луше переименовать в winnerCount
        // int j = 0;
        StringBuilder sb = new StringBuilder();
        int winnerCount = 0;
        // for (int i = 0; i < contestants.length; i++) { // заменила на for-each
        for (Contestant contestant : contestants) {
            if (contestant != null) {
                sb.append(", ").append(contestant.getInfo());
                // contestants[i] = null; // OKrylov: здесь уже очищать элемент массива необязательно, дальнейшая логика метода это не учитывает
                winnerCount++;
            }
        }
        String winnersInfo = sb.toString();

        // финальное сообщение
        System.out.println();
        if (winnerCount != 0) {
            winnersInfo = winnersInfo.substring(2); // OKrylov: лучше вынести в приватный метод removeCommaPrefix, 5 минут гадал, что мы тут делаем)
            System.out.printf("Осталось участников: %d \n" + winnersInfo, winnerCount);
        } else {
            System.out.println("Все участники выбыли из состязаний");
        }
        System.out.println();
    }
}

/* КОНСОЛЬ

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=59898:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\GeekBrains\JAVA\JAVA 2\target\classes" Lesson1.homework.Contest

Прохождение препятствий

Этап 1
Длина беговой дорожки 113 м. Кот Котяра может пробежать 256 м. Участник успешно пробежал дистанцию.
Длина беговой дорожки 113 м. Человек Иван может пробежать 931 м. Участник успешно пробежал дистанцию.
Длина беговой дорожки 113 м. Робот R2B2 может пробежать 978 м. Участник успешно пробежал дистанцию.
Этап 2
Высота стенки 1,2 м. Кот Котяра может прыгнуть на 2,3 м. Участник успешно перепрыгнул препятствие.
Высота стенки 1,2 м. Человек Иван может прыгнуть на 1,6 м. Участник успешно перепрыгнул препятствие.
Высота стенки 1,2 м. Робот R2B2 может прыгнуть на 3,0 м. Участник успешно перепрыгнул препятствие.
Этап 3
Высота стенки 0,6 м. Кот Котяра может прыгнуть на 2,3 м. Участник успешно перепрыгнул препятствие.
Высота стенки 0,6 м. Человек Иван может прыгнуть на 1,6 м. Участник успешно перепрыгнул препятствие.
Высота стенки 0,6 м. Робот R2B2 может прыгнуть на 3,0 м. Участник успешно перепрыгнул препятствие.
Этап 4
Высота стенки 1,0 м. Кот Котяра может прыгнуть на 2,3 м. Участник успешно перепрыгнул препятствие.
Высота стенки 1,0 м. Человек Иван может прыгнуть на 1,6 м. Участник успешно перепрыгнул препятствие.
Высота стенки 1,0 м. Робот R2B2 может прыгнуть на 3,0 м. Участник успешно перепрыгнул препятствие.
Этап 5
Высота стенки 2,3 м. Кот Котяра может прыгнуть на 2,3 м. Участник успешно перепрыгнул препятствие.
Высота стенки 2,3 м. Человек Иван может прыгнуть на 1,6 м. Участник не перепрыгнул препятствие.
Участник выбывает из состязаний!
Высота стенки 2,3 м. Робот R2B2 может прыгнуть на 3,0 м. Участник успешно перепрыгнул препятствие.

Осталось участников: 2
Кот Котяра, Робот R2B2

Process finished with exit code 0

 */