package Lesson5;

public class Organization {

    private Employee[] staff = new Employee[5];
    private static final String ORG_TITLE = "ООО «Рога и копыта»";

    public Organization() {

        String strFIO;

        strFIO = new FullName("Leonard", "Kohen", "").getStringFullName();
        staff[0] = new Employee(strFIO, "General Director", "l.kohen.director@sobaka.ru", "+7999111111", 287345.09, 65);

        strFIO = new FullName("Adam", "Adams", "A.").getStringFullName();
        staff[1] = new Employee(strFIO, "Commercial Director", "a.adams@sobaka.ru", "+7999222222", 150000, 53);

        strFIO = new FullName("Emma","Schmidt","").getStringFullName();
        staff[2] = new Employee(strFIO, "Art Director", "e.schmidt@sobaka.ru", "+7999333333", 130001.00, 21);

        strFIO = new FullName("Hanz", "Anderson", "").getStringFullName();
        staff[3] = new Employee(strFIO, "Head of the department", "h.anderson@sobaka.ru", "+7999444444", 100000.01, 43);

        strFIO = new FullName("Ivan", "Ivanov", "Ivanovich").getStringFullName();
        staff[4] = new Employee(strFIO, "Engineer", "i.ivanov@sobaka.ru", "+7999555555", 25000, 28);

        staff[4].salary = 500.0; // поле salary имеет модификатор доступа public, исправил специально в качестве примера,
        // если исправить этот модификатор доступа на private + добавить геттер, будет полная инкапсуляция
    }

    public static String getOrgTitle() { return ORG_TITLE; }

    public Employee getPersonalDataFromID(int personalID) {
        return staff[personalID];
    }
}