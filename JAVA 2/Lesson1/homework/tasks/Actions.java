package Lesson1.homework.tasks;

public enum Actions {
    RUN("пробежал"), JUMP("перепрыгнул");
    private String rusWord;

    Actions(String rusWord) {
        this.rusWord = rusWord;
    }
    //OKrylov: предпочтительнее использовать геттер-именование, так как это просто поле енама
    // public String toString() { return rusWord; }
    public String getRusWord() {
        return rusWord;
    }

}
