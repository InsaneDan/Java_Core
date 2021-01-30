package Lesson5;
/* Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
* Конструктор класса должен заполнять эти поля при создании объекта;
* Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
* Создать массив из 5 сотрудников
Пример:
Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30); // потом для каждой ячейки массива задаем объект
persArray[1] = new Person(...);
...
persArray[4] = new Person(...);

* С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
public class Homework {
*/

public class Homework {
    public static void main(String[] args) {

        int selectAge = 40;

        // создаем организацию, содержащую список сотрудников, у каждого из которых есть свои свойства
        // список сотрудников заполняется при инициализации класса
        Organization someOrg = new Organization();

        // объект - сотрудник; переменная для перебора (доступа к разным сотрудникам) в цикле
        Employee fellowWorker = new Employee();

        System.out.println("\n\nСписок сотрудников " + Organization.getOrgTitle() + " старше 40 лет:");

        for (int i = 0; i < 5; i++) {
            fellowWorker = someOrg.getPersonalDataFromID(i);
            if (fellowWorker.getAge() > selectAge) {
                fellowWorker.printPersonalData();
            }
        }
    }
}

