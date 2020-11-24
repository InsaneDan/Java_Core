package Lesson6;
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

enum Actions {RUN, SWIM, JUMP} // набор доступных команд
enum Creatures {dog, cat}

public class Animals {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Мурзик", 200, 2.0);
        Cat catFat = new Cat("Толстяк", 222, 1.5);
        Dog dogOrdinary = new Dog("Бобик", 500, 10.0, 0.5);
        Dog dogCyber = new Dog("Кибердог", 50000, 1000, 2.5);
        Dog dogVeryOld = new Dog("Старик", 50, 1, 0.2);


        // метод getAnimalCounter() в классе Animal (а также catCount и dogCount в своих классах) объявлены как static,
        // доступ к нему можем получить через класс-потомок или через объекты:
        // Animal.getAnimalCounter() - обращение к static методу абстрактного класса
        // Dog.getDogCounter() - обращение к static методу private-package класса
        // cat1.getCatCounter() - обращение к static методу private-package класса через объект
        System.out.println();
        System.out.println("В нашем зоопарке сейчас " + Animal.getAnimalCounter() + " животных. Из них собак - " + Dog.getDogCounter() + ", котов - " + cat1.getCatCounter());
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
    // счетчик объектов должен быть static, иначе при создании нового объекта каждый раз будет 
    // создаваться новый счетчик, в конструкторе его значение будет увеличено на 1, и при попытке 
    // получить данные через геттер всегда будет 1.
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
    /* В зависимости от логики программы:
       1) Команду swim можно не включать в абстракт, тогда у объекта Собака этот метод нужно будет создать дополнительно, 
          а у объекта Кот метода не будет. 
       2) Т.к. это абстракт, нужно будет переопределить методы. Для кота создать метод, который будет выводить сообщение,
          что коты не плавают. */

    abstract void jump(double distanceJump); // команда "прыгать"

    // результат выполнения команды выводим в консоль
    // вид животного является полем, но совпадает с названием класса, поэтому его можно получить через методы
    // класса Object (при преобразовании в строку: enum - все буквы строчные, название класса - с заглавной буквы).
    protected void printResult(Object obj, Actions action, String name, double distance, double maxDistance) {
        String strObj = obj.getClass().getName(); // получаем название класса объекта (содержит название пакета + имя класса)
        String kindOutOfObject = strObj.substring(strObj.lastIndexOf(".") + 1); // получаем из строки слово после последней точки
        //результат выполнения команды (краткий вариант)
        String result = kindOutOfObject + "(" + distance + "):" + (distance <= maxDistance);
        //описание выполнения команды (развернутый вариант)
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

        System.out.printf("%-20s\t%s\n", result, detales); // вывод с интервалом (tab) и выравниванием 
    }


    public static int getAnimalCounter() {
        return animalCounter;
    }
}

class Dog extends Animal {
    private final String name; // не предполагается менять имя (нет сеттера, поле private), поэтому final
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


    public static int getDogCounter() {
        return dogCounter;
    }
}


class Cat extends Animal {
    private String name; // не предполагается менять имя (нет сеттера, поле private), поэтому final
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


    public static int getCatCounter() {
        return catCounter;
    }
}

/* ВЫВОД В КОНСОЛЬ:

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=62097:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\GeekBrains\JAVA\Java1_Lesson_6\target\classes ru.geekbrains.java.java1.Animals

В нашем зоопарке сейчас 5 животных. Из них собак - 3, котов - 2

Приступим к тренировке...

БЕГ на дистанцию
Dog(50.5):false     	Пес Старик не пробежал 50.5 м.
Dog(1000.0):true    	Пес Кибердог пробежал 1000.0 м.
Dog(400.0):true     	Пес Бобик пробежал 400.0 м.
Cat(200.0):true     	Кот Мурзик пробежал 200.0 м.
Cat(500.0):false    	Кот Толстяк не пробежал 500.0 м.

ЗАПЛЫВ на дистанцию
Dog(5.0):false      	Пес Старик не проплыл 5.0 м.
Dog(10.0):true      	Пес Кибердог проплыл 10.0 м.
Dog(10.0):true      	Пес Бобик проплыл 10.0 м.
Зачем же так жестоко? Коты не умеют плавать! Мурзик чуть не утонул.
Зачем же так жестоко? Коты не умеют плавать! Толстяк чуть не утонул.

ПРЫЖКИ в высоту
Dog(0.1):true       	Пес Старик подпрыгнул на 0.1 м.
Dog(2.0):true       	Пес Кибердог подпрыгнул на 2.0 м.
Dog(0.5):true       	Пес Бобик подпрыгнул на 0.5 м.
Cat(2.0):true       	Кот Мурзик подпрыгнул на 2.0 м.
Cat(2.0):false      	Кот Толстяк не подпрыгнул на 2.0 м.

Process finished with exit code 0
*/
