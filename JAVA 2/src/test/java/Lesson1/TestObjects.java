package Lesson1;

import Lesson1.homework.contestants.Cat;
import Lesson1.homework.contestants.Human;
import Lesson1.homework.contestants.Robot;

public class TestObjects {


    public static void main(String[] args) {
        Cat cat = new Cat("Cat", 200, 2);
        System.out.println(cat.run());
        System.out.println(cat.jump());
        Human human = new Human("Human", 100, .5);
        System.out.println(human.run());
        System.out.println(human.jump());
        Robot robot = new Robot("Robot", 1000, 1);
        System.out.println(robot.run());
        System.out.println(robot.jump());
    }
}
