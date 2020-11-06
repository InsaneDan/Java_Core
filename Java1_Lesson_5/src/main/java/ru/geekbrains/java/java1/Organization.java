package ru.geekbrains.java.java1;

public class Organization {

    private static Employee[] staff = new Employee[5];
    // неизменяемое поле - название организации, доступ только для чтения через геттер
    private static final String ORG_TITLE = "ООО «Рога и копыта»";

    public Organization() {

        FullName FIO = new FullName();

        FIO.setLastName("Kohen"); FIO.setFirstName("Leonard"); FIO.setPatronim("");
        staff[0] = new Employee(FIO.getStringFullName(), "General Director", "l.kohen.director@sobaka.ru", "+7999111111", 287345.09f, 65);

        FIO.setLastName("Adams"); FIO.setFirstName("Adam"); FIO.setPatronim("A.");
        staff[1] = new Employee(FIO.getStringFullName(), "Commercial Director", "a.adams@sobaka.ru", "+7999222222", 150000.00f, 53);

        FIO.setLastName("Schmidt"); FIO.setFirstName("Emma"); FIO.setPatronim("");
        staff[2] = new Employee(FIO.getStringFullName(), "Art Director", "e.schmidt@sobaka.ru", "+7999333333", 130000.00f, 21);

        FIO.setLastName("Anderson"); FIO.setFirstName("Hanz"); FIO.setPatronim("");
        staff[3] = new Employee(FIO.getStringFullName(), "Head of the department", "h.anderson@sobaka.ru", "+7999444444", 100000.01f, 43);

        FIO.setLastName("Ivanov"); FIO.setFirstName("Ivan"); FIO.setPatronim("Ivanovich");
        staff[4] = new Employee(FIO.getStringFullName(), "Nigga Engineer", "i.ivanov@sobaka.ru", "+7999555555", 25000.00f, 28);

    }


    public static String getOrgTitle() {
        return ORG_TITLE;
    }

    public Employee getPersonalDataFromID(int personalID) {
        return staff[personalID];
    }

}
