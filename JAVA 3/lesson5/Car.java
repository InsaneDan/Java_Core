package lesson5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable{
    private static int CARS_COUNT = 0;
    private static AtomicInteger winner;
    static {
        winner = new AtomicInteger(0);
    }

    private final Race race;
    private final int speed;
    private final String name;
    private CyclicBarrier cb;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов" + ", скорость: " + speed);

            cb.await(); // ждем готовности остальных участников и main потока
            cb.await(); // ждем, когда стартуют остальные участники и main поток

            // прохождение этапов трассы
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            System.out.println(name + " ### ФИНИШИРОВАЛ!!!");

            if (winner.incrementAndGet() == 1) {
                System.out.println(name + " - WIN"); // победитель
            }

            cb.await(); // ждем, когда остальные участники и main поток тоже закончат гонку

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
