package Lesson1.homework.tasks;

public enum Actions {
    RUN("пробежал"), JUMP("перепрыгнул");
    private String rusWord;

    Actions(String rusWord) { this.rusWord = rusWord; }

    public String toString() { return rusWord; }
}
