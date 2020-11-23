package ru.geekbrains.java.java1;

import java.util.Scanner;

public class FeedTheCat {

/*
1. Расширить задачу про котов и тарелки с едой.
2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20).
3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило еды), сытость = true.
4. Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы).
5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль.
6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.

Усложнил:
1) сытость - имеет степень, т.е. кот может быть "немного голодный", коты едят по очереди до полного насыщения;
2) тарелок несколько, выбираем из какой тарелки коту есть;
3) когда все тарелки становятся пустыми, можем положить в них еду; у тарелок есть объем, который нельзя превышать; исходно все тарелки пустые.
*/

    static Scanner scanner = new Scanner(System.in);    // подключаем ввод данных
    static CatNew[] cats = new CatNew[]{                // массив котиков (задаем сразу)
            new CatNew("Cat1", 5),
            new CatNew("Cat2", 3),
            new CatNew("Cat3", 6)};
    static PlateNew[] plates = new PlateNew[]{          // массив тарелок (задаем сразу)
            new PlateNew(3, 0),
            new PlateNew(5, 0),
            new PlateNew(10, 0)
    };

    public static void main(String[] args) {

        System.out.println("\nЗадача: накормить котиков."); // задание
        for (CatNew cat : cats) {
            System.out.print("\nКормим кота по имени " + cat.getName() + ". "); // выводим имя кота, которого кормим
            do {
                showState(); // состояние тарелок и котов
                if (plates[0].getTotalFood() == 0) { // если еда кончилась - наполнить тарелки
                    fillPlates(); // наполнение тарелок
                    showState(); // показать сколько еды стало в тарелках
                }
                cat.eat(plates[chooseThePlate()]); // выбираем индекс тарелки -> объект тарелка -> кормим кота
            } while (cat.getAppetite() > 0); // пока кот не станет сытым
        }
        showState(); // еще раз показать, что котики накормлены
        System.out.println("Все котики накормлены, задача выполнена.");

        scanner.close();
    }

    /** Выбор номера тарелки, из которой будем кормить кота
     * @return - индекс в массиве тарелок */
    private static int chooseThePlate() {
        int p;
        System.out.print("Из какой тарелки будем кормить кота? [1.." + plates.length + "] ");
        do {
            p = scanner.nextInt();
            if (p > plates.length) {
                System.out.print("У нас всего " + (plates.length) + " тарелок. Выберите снова: ");
            } else if (p < 0) {
                System.out.print("Нет такой тарелки. Выберите снова: ");
            } else {
                return --p;
            }
        } while (true);
    }

    /** Вывод состояния: количество еды в каждой тарелке и степень голода котов
     *  cats (массив котов) и plates (массив тарелок) - объявлены на уровне класса */
    private static void showState() {
        // количество еды в тарелках
        System.out.printf("\nСтатус: Еда (тарелок %d) [", plates.length);
        for (int i = 0; i < plates.length; i++) {
            System.out.printf("%d = %d", (i + 1), plates[i].getFood());
            System.out.print((i != plates.length - 1) ? "; " : "].");
        }
        System.out.printf(" Голод (котов %d) [", cats.length);
        for (int i = 0; i < plates.length; i++) {
            System.out.printf("%s = %d", cats[i].getName(), cats[i].getAppetite());
            System.out.print((i != plates.length - 1) ? "; " : "].");
        }
        System.out.println();
    }

    /** Указать, сколько еды нужно положить, и наполнить тарелки начиная с первой, что осталось - во вторую и т.д., пока не закончится еда
     *  plates объявлен на уровне класса */
    private static void fillPlates() {
        int foodRenew;
        int maxAmount = plates[0].getTotalVolume();

        System.out.print("Нужно наполнить тарелки, сколько положить еды? ");
        do { // проверка, чтобы не переполнить тарелки
            foodRenew = scanner.nextInt();
            if (foodRenew > maxAmount) {
                System.out.print("Это много, все не поместится в тарелки. Положим поменьше (максимум " + maxAmount + ")? ");
            }
        } while (foodRenew > maxAmount);

        for (PlateNew plate : plates) {
            foodRenew = plate.addFood(foodRenew);
            if (foodRenew == 0) break;
        }
    }
}

class PlateNew {
    private int food;                   // количество еды в тарелке
    private int volume;                 // объем тарелки
    private static int totalFood;       // счетчик общего количества еды (во всех тарелках)
    private static int totalVolume;     // счетчик общего объема (всех тарелок)

    public PlateNew(int volume, int food) {
        this.food = food;
        this.volume = volume;
        totalFood += food;
        totalVolume += volume;
    }

    // сколько еще нужно "доесть" коту, чтобы насытиться
    public int decreaseFood(int n) {
        if (food >= n) {
            totalFood -= n;
            this.food -= n;
            return 0;
        } else {
            totalFood -= food;
            int appetiteLeft = n - food;
            this.food = 0;
            System.out.println("Тарелка пустая.");
            return appetiteLeft;
        }
    }
    // геттеры
    public int getFood() { return this.food; }

    public int getTotalVolume() { return totalVolume; }

    public int getTotalFood() { return totalFood; }

    /**
     * Добавляем в тарелку еду, пока тарелка не наполнится; остаток - возвращаем
     * @param n - количество добавляемой еды
     * @return int - количество еды, которая не поместилась в тарелку
     */
    public int addFood(int n) {
        int foodToAdd = (this.volume - this.food);
        if (foodToAdd >= n) {
            this.food += n;
            totalFood += food;
            return 0;
        } else {
            this.food += foodToAdd;
            totalFood += food;
            return n - foodToAdd;
        }
    }
}

class CatNew {
    private String name;                // имя кота
    private int appetite;               // степень голода кота

    public CatNew(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public int getAppetite() { return this.appetite; }

    public void eat(PlateNew p) { this.appetite = p.decreaseFood(this.appetite); }

    public String getName() { return this.name; }
}


/*
Решение (консоль):
"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=53385:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath C:\GeekBrains\JAVA\Java1_Lesson_7\target\classes ru.geekbrains.java.java1.FeedTheCat

Задача: накормить котиков.

Кормим кота по имени Cat1.
Статус: Еда (тарелок 3) [1 = 0; 2 = 0; 3 = 0]. Голод (котов 3) [Cat1 = 5; Cat2 = 3; Cat3 = 6].
Нужно наполнить тарелки, сколько положить еды? 18

Статус: Еда (тарелок 3) [1 = 3; 2 = 5; 3 = 10]. Голод (котов 3) [Cat1 = 5; Cat2 = 3; Cat3 = 6].
Из какой тарелки будем кормить кота? [1..3] 3

Кормим кота по имени Cat2.
Статус: Еда (тарелок 3) [1 = 3; 2 = 5; 3 = 5]. Голод (котов 3) [Cat1 = 0; Cat2 = 3; Cat3 = 6].
Из какой тарелки будем кормить кота? [1..3] 3

Кормим кота по имени Cat3.
Статус: Еда (тарелок 3) [1 = 3; 2 = 5; 3 = 2]. Голод (котов 3) [Cat1 = 0; Cat2 = 0; Cat3 = 6].
Из какой тарелки будем кормить кота? [1..3] 3
Тарелка пустая.

Статус: Еда (тарелок 3) [1 = 3; 2 = 5; 3 = 0]. Голод (котов 3) [Cat1 = 0; Cat2 = 0; Cat3 = 4].
Из какой тарелки будем кормить кота? [1..3] 1
Тарелка пустая.

Статус: Еда (тарелок 3) [1 = 0; 2 = 5; 3 = 0]. Голод (котов 3) [Cat1 = 0; Cat2 = 0; Cat3 = 1].
Из какой тарелки будем кормить кота? [1..3] 2

Статус: Еда (тарелок 3) [1 = 0; 2 = 4; 3 = 0]. Голод (котов 3) [Cat1 = 0; Cat2 = 0; Cat3 = 0].
Все котики накормлены, задача выполнена.

Process finished with exit code 0
*/
