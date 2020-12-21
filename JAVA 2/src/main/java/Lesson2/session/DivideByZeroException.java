package Lesson2.session;

public class DivideByZeroException extends ArithmeticException {

    public DivideByZeroException() {
        super("Произошло деление на ноль!");
    }
}
