package ru.geekbrains.java.java1;
/*
1. Создать классы Собака и Кот с наследованием от класса Животное.
2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу
   передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.;
   плавание: кот не умеет плавать, собака 10 м.).
4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль.
   (Например, dog1.run(150); -> результат: run: true)
5.*Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
*/

enum Actions {RUN, SWIM, JUMP;}; // набор доступных команд
enum Creatures {dog, cat};

public class Animals {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Мурзик", 200, 2.0);
        Cat catFat = new Cat("Толстяк", 222, 1.5);
        Dog dogOrdinary = new Dog("Бобик", 500, 10.0, 0.5);
        Dog dogCyber = new Dog("Кибердог", 50000, 1000, 2.5);
        Dog dogVeryOld = new Dog("Старик", 50, 1, 0.2);

        // вместо cat1.getAnimalCounter() можно использовать любой из созданных объектов, они все могут использовать
        // метод суперкласса getAnimalCounter(); соответственно и для вывода значения счетчика котов или собак можно
        // использовать любой из объектов Cat и Dog соответственно.
        System.out.println();
        System.out.println("В нашем зоопарке сейчас " + cat1.getAnimalCounter() + " животных. Из них собак - " + dogOrdinary.getDogCounter() + ", котов - " + cat1.getCatCounter());
        System.out.println("\nПриступим к тренировке...");

        System.out.println("\nБЕГ на дистанцию");
        dogVeryOld.run(50.5);
        dogCyber.run(1000);
        dogOrdinary.run(400);
        cat1.run(200);
        catFat.run(500);

        System.out.println("\nЗАПЛЫВ на дистанцию");
        dogVeryOld.swim(5);
        dogCyber.swim(10);
        dogOrdinary.swim(10);
        cat1.swim(10);
        catFat.swim(0);

        System.out.println("\nПРЫЖКИ в высоту");
        dogVeryOld.jump(.1);
        dogCyber.jump(2);
        dogOrdinary.jump(.5);
        cat1.jump(2);
        catFat.jump(2);
    }
}

// абстрактный класс Животное
abstract class Animal {

    protected Creatures kind;
    protected double maxDistanceRun;            // максимальная дистанция, которую может пробежать
    protected double maxDistanceSwim;           //                                       проплыть
    protected double maxDistanceJump;           // максимальная высота, на которую может подпрыгнуть
    // счетчики объектов должны быть static, иначе при создании нового объекта будет создаваться и новый счетчик
    private static int animalCounter;


    Animal (Creatures kind, double maxDistanceRun, double maxDistanceSwim, double maxDistanceJump) {
        this.kind = kind;
        this.maxDistanceRun = maxDistanceRun;
        this.maxDistanceSwim = maxDistanceSwim;
        this.maxDistanceJump = maxDistanceJump;
        animalCounter++;
    }

    abstract void run(double distanceRun);   // команда "бежать"
    abstract void swim(double distanceSwim); // команда "плыть"
    /* Команду swim можно не включать в абстракт; тогда после создания объекта Кот, у которого этот метод не должен
       давать результат, не будет появляться "подсказка" при вводе после точки в списке доступных методов.
       Т.е. у кота вообще не будет метода swim. В этом случае метод придется переопределить. */
    abstract void jump(double distanceJump); // команда "прыгать"

    // результат выполнения команды выводим в консоль
    // вид животного является полем, но совпадает с названием класса, поэтому его можно получить через методы
    // класса Object (при преобразовании в строку - enum все буквы строчные, название класса - с первой заглавной буквы).
    protected void printResult(Object obj, Actions action, String name, double distance, double maxDistance) {

        String strObj = obj.getClass().getName(); // получаем название класса объекта (содержит название пакета + имя класса)
        String kindOutOfObject = strObj.substring(strObj.lastIndexOf(".") + 1); // получаем из строки слово после последней точки

        String result = kindOutOfObject + "(" + distance + "):" + (distance <= maxDistance);
        String detales =
                switch (kind) {
                    case cat -> "Кот ";
                    case dog -> "Пес ";
                } +
                name +
                ((distance <= maxDistance) ? " " : " не ") +
                switch (action) {
                    case RUN -> "пробежал ";
                    case SWIM -> "проплыл ";
                    case JUMP -> "подпрыгнул на ";
                } + distance + " м.";

        System.out.printf("%-20s\t%s\n", result, detales);
    }

    public int getAnimalCounter() {
        return animalCounter;
    }
}

class Dog extends Animal {
    private String name;
    private static int dogCounter;
    Dog (String name, double maxDistanceRun, double maxDistanceSwim, double maxDistanceJump) {
        super(Creatures.dog, maxDistanceRun, maxDistanceSwim, maxDistanceJump);
        this.name = name;
        dogCounter++;
    }

    @Override
    void run(double distance) {
        printResult(this, Actions.RUN, name, distance, maxDistanceRun);
    }

    @Override
    void swim(double distance) {
        printResult(this, Actions.SWIM, name, distance, maxDistanceSwim);
    }

    @Override
    void jump(double distance) {
        printResult(this, Actions.JUMP, name, distance, maxDistanceJump);
    }

    public int getDogCounter() {
        return dogCounter;
    }
}


class Cat extends Animal {
    private String name;

    private static int catCounter;

    Cat(String name, double maxDistanceRun, double maxDistanceJump) {
        super(Creatures.cat, maxDistanceRun, 0, maxDistanceJump);
        this.name = name;
        catCounter++;
    }
    @Override
    void run(double distance) {
        printResult(this, Actions.RUN, name, distance, maxDistanceRun);
    }

    @Override
    void swim(double distance) {
        System.out.println("Зачем же так жестоко? Коты не умеют плавать! " + name + " чуть не утонул.");
    }
    @Override
    void jump(double distance) {
        printResult(this, Actions.JUMP, name, distance, maxDistanceJump);
    }

    public int getCatCounter() {
        return catCounter;
    }
}