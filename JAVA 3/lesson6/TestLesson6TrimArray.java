package lesson6;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestLesson6TrimArray {

    private static Lesson6HW hwTest;
    private final int[] arrRes;
    private final int[] arrTested;
    private static int testCounter = 1;

    public TestLesson6TrimArray(int[] arrRes, int[] arrTested) {
        this.arrRes = arrRes;
        this.arrTested = arrTested;
    }

    @Parameterized.Parameters //(name = "{index}: testAdd({0}) >>> {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 7}, new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}},
                {new int[]{}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}},
                {new int[]{}, new int[]{4}},
                {new int[]{}, new int[]{1, 2, 5, 9, 2, 3, 14, 1, 7}},
                {new int[]{}, new int[]{1, 2, 4, 4, 2, 3, 4}},
                {new int[]{}, new int[]{}}
        });
    }

    @BeforeClass
    public static void init() {
        hwTest = new Lesson6HW();
    }

    @Test
    public void testTrimArray() {
        int number = 4;
        System.out.println("Исходный массив: " + Arrays.toString(arrTested));
        try {
            Assert.assertArrayEquals(arrRes, hwTest.trimArrayOfNumbers(arrTested, number));
            System.out.println("Результат: " + Arrays.toString(hwTest.trimArrayOfNumbers(arrTested, number)));
            System.out.println("Тест " + testCounter + " пройден\n");
        } catch (RuntimeException e) {
            Assert.assertEquals("В массиве нет числа " + number, e.getMessage());
            System.out.println("В массиве нет числа " + number + ". Выброшено исключение RuntimeException");
            System.out.println("Тест " + testCounter + " пройден\n");
        } finally {
            testCounter++;
        }
    }

}

/* РЕЗУЛЬТАТ (консоль):

"C:\Program Files\Java\jdk-15\bin\java.exe" -ea -Dfile.encoding=UTF-8 -Didea.test.cyclic.buffer.size=1048576 "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=58721:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -classpath "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\plugins\junit\lib\junit5-rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\plugins\junit\lib\junit-rt.jar;C:\GeekBrains\JAVA\JAVA 3\target\test-classes;C:\GeekBrains\JAVA\JAVA 3\target\classes;C:\Users\IDA\.m2\repository\org\openjfx\javafx-controls\14.0.1\javafx-controls-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-controls\14.0.1\javafx-controls-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-graphics\14.0.1\javafx-graphics-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-graphics\14.0.1\javafx-graphics-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-base\14.0.1\javafx-base-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-base\14.0.1\javafx-base-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-fxml\14.0.1\javafx-fxml-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-fxml\14.0.1\javafx-fxml-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\xerial\sqlite-jdbc\3.32.3.2\sqlite-jdbc-3.32.3.2.jar;C:\Users\IDA\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\IDA\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 lesson6.TestLesson6TrimArray
Исходный массив: [1, 2, 4, 4, 2, 3, 4, 1, 7]
Результат: [1, 7]
Тест 1 пройден

Исходный массив: [4, 4, 4, 4, 4, 4, 4, 4, 4]
Результат: []
Тест 2 пройден

Исходный массив: [4]
В массиве нет числа 4. Выброшено исключение RuntimeException
Тест 3 пройден

Исходный массив: [1, 2, 5, 9, 2, 3, 14, 1, 7]
В массиве нет числа 4. Выброшено исключение RuntimeException
Тест 4 пройден

Исходный массив: [1, 2, 4, 4, 2, 3, 4]
Результат: []
Тест 5 пройден

Исходный массив: []
В массиве нет числа 4. Выброшено исключение RuntimeException
Тест 6 пройден


Process finished with exit code 0

*/