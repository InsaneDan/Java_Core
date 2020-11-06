package ru.geekbrains.java.java1;

public class FullName {

    // поля класса private (доступ через set / get)
    private String firstName;
    private String lastName;
    private String patronim;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronim(String patronim) {
        this.patronim = patronim;
    }

    public String getStringFullName() {
        String strFullName = this.lastName + " " + this.firstName + ((this.patronim != "") ? " " + this.patronim : "");
        return strFullName;
    }
}
