package Lesson5;

public class Employee {

    private String strFIO;
    private String position, email, mobilePhoneNumber;
    public double salary;
    private int age;

    // перегрузка конструкторов
    public Employee() {};

    public Employee(String strFIO, String position, String email, String mobilePhoneNumber, double salary, int age) {
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printPersonalData() {
        System.out.printf("ФИО: %-25s | Возраст: %5d | Должность: %-25s | Телефон: %13s | E-mail: %-30s | Salary: %10.2f\n", strFIO, age, position, mobilePhoneNumber, email, salary);
    }


}
