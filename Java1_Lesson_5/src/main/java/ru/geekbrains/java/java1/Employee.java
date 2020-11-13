package ru.geekbrains.java.java1;

public class Employee {

    // поля класса public
    private FullName classFIO = new FullName();
    private String strFIO;
    private String position, email, mobilePhoneNumber;
    private float salary;
    private int age;

    // перегрузка конструкторов
    public Employee() {};

    public Employee(String strFIO, String position, String email, String mobilePhoneNumber, float salary, int age) {
        this.strFIO = strFIO;
        this.position = position;
        this.email = email;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public String getStrFIO() {
        return strFIO;
    }

    public void setStrFIO(String strFIO) {
        this.strFIO = strFIO;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printPersonalData() {
        System.out.printf("ФИО: %25s | Возраст: %5d | Должность: %25s | Телефон: %13s | E-mail: %15s \n", strFIO, age, position, mobilePhoneNumber, email);
    }


}
