package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Homework1 {

    public static void main(String[] args) {

        Homework1 hw = new Homework1();

        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] strs = {"Арбуз", "Банан", "Вишня", "Груша", "Дыня"};

        System.out.println("\nЗадание 1. Поменять два элемента массива местами:");
        System.out.println(Arrays.toString(nums) + " --- исходный массив");
        hw.swap(nums, 3, 7); // для 4-го и 8-го элементов
        System.out.println(Arrays.toString(nums) + " --- массив после перестановки элементов");

        System.out.println("\nЗадание 2. Преобразовать массив в ArrayList:");
        System.out.println(hw.convertArray(nums) + " --- ArrayList of Integer");
        System.out.println(hw.convertArray(strs) + " --- ArrayList of String");

        System.out.println("\nЗадание 3. Большая задача:");
        Box<Apple> boxApple = new Box<>();
        Box<Orange> boxOrange = new Box<>();
        boxApple.add(new Apple(),9);
        boxOrange.add(new Orange(),6);
        System.out.println(boxApple.getState());
        System.out.println(boxOrange.getState());
        System.out.println("Вес ящиков одинаковый? " + boxApple.compare(boxOrange));

        System.out.println("Берем новый ящик для яблок, кладем в него 3 штуки.");
        Box<Apple> boxApple2 = new Box<>();
        boxApple2.add(new Apple(), 3);
        System.out.println(boxApple2.getState());
        System.out.println("Вес ящиков с яблоками одинаковый? " + boxApple.compare(boxApple2));
        System.out.println("Пересыпаем все яблоки в новый ящик.");
        boxApple.moveAllFruits(boxApple2);
        System.out.println(boxApple2.getState());
        System.out.println("Попытка пересыпать фрукты из последнего ящика в тот же ящик:");
        boxApple2.moveAllFruits(boxApple2);
        System.out.println(boxApple2.getState());
    }

    // 1. метод, который меняет два элемента массива местами (амссив может быть любого ссылочного типа)
    private <T> void swap (T[] arr, int index1, int index2) {
        T temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;
    }

    // 2. метод, который преобразует массив в ArrayList
    private <T> ArrayList<T> convertArray(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
//      return new ArrayList<T>(Arrays.asList(arr)); // Warning: Explicit type argument T can be replaced with <>
//      тип ArrayList'а будет зависеть от типа элементов массива: public static <T> List<T> asList(T... a)
    }
}

/*
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку
    нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока -
    1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в
    compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем
    сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку
    фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
    а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
 */

abstract class Fruit {
    protected float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getFruitWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "=" + Math.floor(weight * 10)/10; // округляем вниз до 1 знака после запятой
    }
}

class Apple extends Fruit {
    public Apple() {
        super(1.0f);
    }
}

class Orange extends Fruit {
    public Orange() {
        super(1.5f);
    }
}

class Box<T extends Fruit> {

    private final ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit, int amount) {
        for (int i = 0; i < amount; i++) {
            fruits.add(fruit);
        }
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public void addFruits(ArrayList<T> fruits) {
        this.fruits.addAll(fruits);
    }

    public float getWeight() {
        if (fruits.size() > 0) {
            // т.к. вес каждого фрукта фиксирован, то берем первый и умножаем на количество
            float weight = fruits.get(0).getFruitWeight() * fruits.size();
// если бы фрукты одного типа отличались по весу, то посчитали бы в цикле, сложив вес каждого из них
//            float weight = 0.0f;
//            for (T fruit : fruits) {
//                weight += fruit.getFruitWeight();
//            }
            return (float)Math.floor(weight * 10)/10; //округляем вниз до 1 знака после запятой
        }
        return 0;
    }

    public String getState() {
        if (fruits.size() > 0) {
            return "Тип ящика: " + fruits.get(0).getClass().getSimpleName() + ", количество: " + fruits.size() + ", вес: " + getWeight();
        }
        return "Пустой ящик";
    }

    public boolean compare(Box<?> box) {
        return Float.compare(getWeight(), box.getWeight()) == 0;
    }

    public void moveAllFruits(Box<T> box) {
// при попытке пересыпать в самого себя ящик очистится, поэтому 1) пересыпаем только в другой ящик
        if (!this.equals(box)) {
            box.addFruits(fruits);
            fruits.clear();
// 2) создаем временную копию фруктов (new ArrayList), очищаем ящик, кладем в него фрукты из временной копии
//            ArrayList<T> fruitsTemp = new ArrayList<>(getFruits());
//            fruits.clear();
//            box.addFruits(fruitsTemp);
        }
    }
}

/* КОНСОЛЬ:
"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=58961:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\GeekBrains\JAVA\JAVA 3\target\test-classes" lesson1.Homework1

Задание 1. Поменять два элемента массива местами:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10] --- исходный массив
[1, 2, 3, 8, 5, 6, 7, 4, 9, 10] --- массив после перестановки элементов

Задание 2. Преобразовать массив в ArrayList:
[1, 2, 3, 8, 5, 6, 7, 4, 9, 10] --- ArrayList of Integer
[Арбуз, Банан, Вишня, Груша, Дыня] --- ArrayList of String

Задание 3. Большая задача:
Тип ящика: Apple, количество: 9, вес: 9.0
Тип ящика: Orange, количество: 6, вес: 9.0
Вес ящиков одинаковый? true
Берем новый ящик для яблок, кладем в него 3 штуки.
Тип ящика: Apple, количество: 3, вес: 3.0
Вес ящиков с яблоками одинаковый? false
Пересыпаем все яблоки в новый ящик.
Тип ящика: Apple, количество: 12, вес: 12.0

Process finished with exit code 0

 */