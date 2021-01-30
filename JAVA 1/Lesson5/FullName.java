package Lesson5;

public class FullName {

    // поля класса private
    // доступ к полям только для записи (setter)
    // геттер отдает только значение полного имени
    private String firstName;
    private String lastName;
    private String patronim;

    FullName (String firstName, String lastName, String patronim) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronim = patronim;
    }

    public String getStringFullName() {
        String strFullName = this.lastName + " " + this.firstName + ((this.patronim != "") ? " " + this.patronim : "");
        return strFullName;
    }
}