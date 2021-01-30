package Lesson1.homework.tasks;

import Lesson1.homework.contestants.Contestant;

public class Wall implements Task {

    private final double height;

    public Wall(double height) {
        this.height = height;
    }

    // public double getHeight() { return height; }

    @Override
    public boolean performTask(Contestant contestant) {
        System.out.printf("Высота стенки %.1f м. ", height);
        boolean result = contestant.jump() > height;
        System.out.println("Участник " + (result ? "успешно " : "не ") + Actions.JUMP.getRusWord() + " препятствие.");
        return result;
    }
}
