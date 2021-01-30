package lesson5;

import java.util.concurrent.BrokenBarrierException;
// import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
//    public static final CountDownLatch START = new CountDownLatch(CARS_COUNT + 1); // участники стартуют после этапа подготовки
//    public static final CountDownLatch FINISH = new CountDownLatch(CARS_COUNT + 1); // счетчик для финиша
    public static final Semaphore TUNNEL = new Semaphore(CARS_COUNT / 2, true); // сохранить очередь: кто первый подъехал к туннелю, тот и поедет в него первым

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        CyclicBarrier cb = new CyclicBarrier(MainClass.CARS_COUNT + 1);

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb);
            new Thread(cars[i]).start();
        }

        try {
            cb.await(); // ждем готовности всех участников
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            cb.await(); // ждем, когда стартуют все участники
            cb.await(); // ждем, пока все участники финишируют
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

}

/* РЕЗУЛЬТАТ (консоль):

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=50067:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\GeekBrains\JAVA\JAVA 3\target\classes;C:\Users\IDA\.m2\repository\org\openjfx\javafx-controls\14.0.1\javafx-controls-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-controls\14.0.1\javafx-controls-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-graphics\14.0.1\javafx-graphics-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-graphics\14.0.1\javafx-graphics-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-base\14.0.1\javafx-base-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-base\14.0.1\javafx-base-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-fxml\14.0.1\javafx-fxml-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-fxml\14.0.1\javafx-fxml-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\xerial\sqlite-jdbc\3.32.3.2\sqlite-jdbc-3.32.3.2.jar" lesson5.MainClass
ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!
Участник #2 готовится
Участник #1 готовится
Участник #3 готовится
Участник #4 готовится
Участник #3 готов, скорость: 28
Участник #1 готов, скорость: 22
Участник #2 готов, скорость: 28
Участник #4 готов, скорость: 27
ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!
Участник #3 начал этап: Дорога 60 метров
Участник #1 начал этап: Дорога 60 метров
Участник #2 начал этап: Дорога 60 метров
Участник #4 начал этап: Дорога 60 метров
Участник #2 закончил этап: Дорога 60 метров
Участник #1 закончил этап: Дорога 60 метров
Участник #1 готовится к этапу (ждет): Тоннель 80 метров
Участник #1 начал этап: Тоннель 80 метров
Участник #2 готовится к этапу (ждет): Тоннель 80 метров
Участник #2 начал этап: Тоннель 80 метров
Участник #3 закончил этап: Дорога 60 метров
Участник #3 готовится к этапу (ждет): Тоннель 80 метров
Участник #4 закончил этап: Дорога 60 метров
Участник #4 готовится к этапу (ждет): Тоннель 80 метров
Участник #2 закончил этап: Тоннель 80 метров
Участник #3 начал этап: Тоннель 80 метров
Участник #2 начал этап: Дорога 40 метров
Участник #1 закончил этап: Тоннель 80 метров
Участник #1 начал этап: Дорога 40 метров
Участник #4 начал этап: Тоннель 80 метров
Участник #2 закончил этап: Дорога 40 метров
Участник #2 ### ФИНИШИРОВАЛ!!!
Участник #2 - WIN
Участник #1 закончил этап: Дорога 40 метров
Участник #1 ### ФИНИШИРОВАЛ!!!
Участник #3 закончил этап: Тоннель 80 метров
Участник #3 начал этап: Дорога 40 метров
Участник #4 закончил этап: Тоннель 80 метров
Участник #4 начал этап: Дорога 40 метров
Участник #3 закончил этап: Дорога 40 метров
Участник #3 ### ФИНИШИРОВАЛ!!!
Участник #4 закончил этап: Дорога 40 метров
Участник #4 ### ФИНИШИРОВАЛ!!!
ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!

Process finished with exit code 0

*/
