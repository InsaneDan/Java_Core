package Lesson1;

import Lesson1.homework.contestants.Cat;
import Lesson1.homework.tasks.Track;

public class TestActions {

    public static void main(String[] args) {
        Track track = new Track(1);
        Cat cat = new Cat("Cat", 200, 2);
        track.performTask(cat);
    }
}
