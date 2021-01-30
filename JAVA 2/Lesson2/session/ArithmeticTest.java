package lesson2;

public class ArithmeticTest {

    public static void main(String[] args) {
        try {
            int b = 0;
            int result = divide(10, b);
            System.out.println("Result = " + result);

//            String t = null;
//            t.length();

            int[] c = {1, 2, 3};
            System.out.println(c[42]);


        } catch (DivideByZeroException/* | NullPointerException | IndexOutOfBoundsException*/ e) {
            System.err.println(e.getMessage());
        } catch (ArithmeticException exc) {
//            logger.log("Details message", exc);
            System.err.println(exc.getMessage());
            System.out.println("Деление на ноль!");
            exc.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid array index value!");
        } catch (Exception e) {
            System.err.println("Упс! Что-то случилось!");
        }
        System.out.println("finish!");
    }

    private static int divide(int a, int b) {
        if (b == 0) {
            throw new DivideByZeroException();
        }
        return a / b;
    }
}
