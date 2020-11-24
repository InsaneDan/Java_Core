package Lesson1.homework.tasks;

import Lesson1.homework.contestants.Contestant;

public class Track implements Task {

    private final int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public boolean performTask(Contestant contestant) {
        System.out.printf("Длина беговой дорожки %d м. ", length);
        boolean result = contestant.run() > length;
        System.out.println("Участник " + (result ? "успешно " : "не ") + Actions.RUN.toString() + " дистанцию.");
        return result;
    }
}
