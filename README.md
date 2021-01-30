# Java core (Table of Contents)

### [Java 1](#java-1)
- [x] [Урок 1. Введение](#%D1%83%D1%80%D0%BE%D0%BA-1-java-%D0%B2%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5)
- [x] [Урок 2. Основные конструкции](#%D1%83%D1%80%D0%BE%D0%BA-2-%D0%BE%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D1%8B%D0%B5-%D0%BA%D0%BE%D0%BD%D1%81%D1%82%D1%80%D1%83%D0%BA%D1%86%D0%B8%D0%B8)
- [x] [Урок 3. Практика](#%D1%83%D1%80%D0%BE%D0%BA-3-%D0%BF%D1%80%D0%B0%D0%BA%D1%82%D0%B8%D0%BA%D0%B0)
- [x] [Урок 4. Крестики-нолики в процедурном стиле](#%D1%83%D1%80%D0%BE%D0%BA-4-%D0%BA%D1%80%D0%B5%D1%81%D1%82%D0%B8%D0%BA%D0%B8-%D0%BD%D0%BE%D0%BB%D0%B8%D0%BA%D0%B8-%D0%B2-%D0%BF%D1%80%D0%BE%D1%86%D0%B5%D0%B4%D1%83%D1%80%D0%BD%D0%BE%D0%BC-%D1%81%D1%82%D0%B8%D0%BB%D0%B5)
- [x] [Урок 5. Введение в ООП](#%D1%83%D1%80%D0%BE%D0%BA-5-%D0%B2%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B2-%D0%BE%D0%BE%D0%BF)
- [x] [Урок 6. Продвинутое ООП](#%D1%83%D1%80%D0%BE%D0%BA-6-%D0%BF%D1%80%D0%BE%D0%B4%D0%B2%D0%B8%D0%BD%D1%83%D1%82%D0%BE%D0%B5-%D0%BE%D0%BE%D0%BF)
- [x] [Урок 7. Практика ООП и работа со строками](#%D1%83%D1%80%D0%BE%D0%BA-7-%D0%BF%D1%80%D0%B0%D0%BA%D1%82%D0%B8%D0%BA%D0%B0-%D0%BE%D0%BE%D0%BF-%D0%B8-%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81%D0%BE-%D1%81%D1%82%D1%80%D0%BE%D0%BA%D0%B0%D0%BC%D0%B8)
- [x] [Урок 8. Написание приложения с графическим интерфейсом](#%D1%83%D1%80%D0%BE%D0%BA-8-%D0%BD%D0%B0%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F-%D1%81-%D0%B3%D1%80%D0%B0%D1%84%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%BC-%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D1%84%D0%B5%D0%B9%D1%81%D0%BE%D0%BC)

# Java 1

## Урок 1. Java. Введение
1. Создать пустой проект в IntelliJ IDEA и прописать метод main();
2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,где a, b, c, d – входные параметры этого метода;
4. Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.
6. Написать метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное;
7. Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
8. \* Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

**[⬆ back to top](#java-core-table-of-contents)**

## Урок 2. Основные конструкции
1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
5. \*\* Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
6. \*\* Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
7. \*\*\*\* Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным), при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.<br>
Вариант №1 [1, 2, 3, 4, 5, 6]  сместить на 2 -> [5, 6, 1, 2, 3, 4]<br>
Вариант №2 [5, 6, 7] сместить на -5 -> [7, 5, 6]

**[⬆ back to top](#java-core-table-of-contents)**

## Урок 3. Практика
1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число. При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше. После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
2. \* Создать массив из слов
    
    ```java
    String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", 
    "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", 
    "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    ```
    
   При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.<br/>
apple – загаданное<br/>
apricot - ответ игрока<br/>
ap############# (15 символов, чтобы пользователь не мог узнать длину слова)<br/>
Для сравнения двух слов посимвольно, можно пользоваться:<br/>
String str = "apple";<br/>
str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции<br/>
Играем до тех пор, пока игрок не отгадает слово.<br/>
Используем только маленькие буквы.

**[⬆ back to top](#java-core-table-of-contents)**

## Урок 4. Крестики-нолики в процедурном стиле
1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку;
2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
3. \* Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4. Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
4. \*\*\* Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.

**[⬆ back to top](#java-core-table-of-contents)**

## Урок 5. Введение в ООП
1. Создать класс «Сотрудник» с полями: ФИО, должность, email, телефон, зарплата, возраст.
2. Конструктор класса должен заполнять эти поля при создании объекта.
3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
4. Создать массив из 5 сотрудников. Пример:

    ```java
    Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
    // потом для каждой ячейки массива задаем объект
    persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan/@mailbox.com", "892312312", 30000, 30); 
    persArray[1] = new Person(...);
    ...
    persArray[4] = new Person(...);
    ```
5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;

**[⬆ back to top](#java-core-table-of-contents)**

## Урок 6. Продвинутое ООП
1. Создать классы Собака и Кот с наследованием от класса Животное.
2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true)
5. \* Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400м., у другой 600м.

**[⬆ back to top](#java-core-table-of-contents)**

## Урок 7. Практика ООП и работа со строками
1. Расширить задачу про котов и тарелки с едой
2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20)
3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило еды), сытость = true
4. Считаем, что если коту мало еды в тарелке, то он ее просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы)
5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль
6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку

**[⬆ back to top](#java-core-table-of-contents)**

## Урок 8. Написание приложения с графическим интерфейсом
Доработать проект, который разрабатывали на уроке. Приветствуется творческий подход.

