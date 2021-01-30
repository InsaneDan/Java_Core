package Lesson7;

public class FeedTheCatSimple {

/*
1. Расширить задачу про котов и тарелки с едой.
2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20).
3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило еды), сытость = true.
4. Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы).
5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль.
6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.

Решение (консоль):

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=51798:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\GeekBrains\JAVA\Java1_Lesson_7\target\classes ru.geekbrains.java.java1.FeedTheCatSimple
Команда котов: Cat1 Cat2 Cat3
Еды в тарелке: 1
Добавили: 10
Еды в тарелке: 11
Кормим котов...
Имя кота: Cat1; аппетит: 5; голодный: нет
Имя кота: Cat2; аппетит: 3; голодный: нет
Не хватает еды.
Имя кота: Cat3; аппетит: 6; голодный: да

Process finished with exit code 0
*/

    private static final Cat[] cats = new Cat[]{            // команда котов
            new Cat("Cat1", 5),
            new Cat("Cat2", 3),
            new Cat("Cat3", 6)};
    private static final Plate plate = new Plate(1);   // миска с едой

    public static void main(String[] args) {

        System.out.print("Команда котов:");
        for (Cat cat : cats) {
            System.out.print(" " + cat.getName()); // выводим имена котов
        }
        System.out.println();
        plate.info(); // выводим сколько еды в тарелке
        int n = 10;
        plate.addFood(n); // добавляем еды
        System.out.println("Добавили: " + n);
        plate.info(); // сколько еды стало в тарелке

        System.out.println("Кормим котов...");
        for (Cat cat : cats) {
            cat.eat(plate);
            cat.getCatInfo();
        }
    }
}

class Plate {
    private int food;

    public Plate(int food) { this.food = food; }

    public boolean decreaseFood(int n) {
        if (food >= n) {
            food -= n;
            return true;
        }
        return false;
    }

    public void addFood(int n) { this.food += n; }

    public void info() { System.out.println("Еды в тарелке: " + food); }
}

class Cat {
    private final String name;  // не меняется
    private final int appetite; // не меняется, изменяется только состояние "наелся"
    private boolean isFedUp;

    Cat (String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isFedUp = false;
    }

    public void eat(Plate p) {
        if (p.decreaseFood(appetite)) {
            this.isFedUp = true;
        } else {
            System.out.println("Не хватает еды.");
        }
    }

    public String getName() { return this.name; }

    public void getCatInfo() {
        System.out.println("Имя кота: " + this.name + "; аппетит: " + this.appetite + "; голодный: " + ((!isFedUp) ? "да" : "нет"));
    }
}