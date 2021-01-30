package lesson6;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestLesson6ArrayContainsNumbers {

    private static Lesson6HW hwTest;
    private final int[] arr;
    private final Boolean expectedResult;
    private static final int num1 = 1;
    private static final int num2 = 4;
    private static int testCounter = 0;

    public TestLesson6ArrayContainsNumbers(Boolean expectedResult, int[] arr) {
        this.expectedResult = expectedResult;
        this.arr = arr;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, new int[]{1, 4, 4, 4, 1, 1, 4, 1, 1}},
                {true, new int[]{1, 4, 1, 4}},
                {true, new int[]{1, 4}},
                {false, new int[]{}},
                {false, new int[]{1}},
                {false, new int[]{4}},
                {false, new int[]{2, 3, 5}},
                {false, new int[]{1, 2, 3, 4, 5}},
                {false, new int[]{1, 1, 1, 1, 4, 4, 4, 4, 0}}
        });
    }

    @BeforeClass
    public static void init() {
        hwTest = new Lesson6HW();
    }

    @Test
    public void testIsArrayContainsNumbers() {
        System.out.println("Тест " + testCounter);
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        Assert.assertEquals(expectedResult, hwTest.isArrayContainsNumbers(arr, num1, num2));
        System.out.println("Результат: " + hwTest.isArrayContainsNumbers(arr, num1, num2));
        testCounter++;
    }
}

/* РЕЗУЛЬТАТ (консоль):

"C:\Program Files\Java\jdk-15\bin\java.exe" -ea -Dfile.encoding=UTF-8 -Didea.test.cyclic.buffer.size=1048576 "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=59343:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -classpath "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\plugins\junit\lib\junit5-rt.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\plugins\junit\lib\junit-rt.jar;C:\GeekBrains\JAVA\JAVA 3\target\test-classes;C:\GeekBrains\JAVA\JAVA 3\target\classes;C:\Users\IDA\.m2\repository\org\openjfx\javafx-controls\14.0.1\javafx-controls-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-controls\14.0.1\javafx-controls-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-graphics\14.0.1\javafx-graphics-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-graphics\14.0.1\javafx-graphics-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-base\14.0.1\javafx-base-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-base\14.0.1\javafx-base-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-fxml\14.0.1\javafx-fxml-14.0.1.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-fxml\14.0.1\javafx-fxml-14.0.1-win.jar;C:\Users\IDA\.m2\repository\org\xerial\sqlite-jdbc\3.32.3.2\sqlite-jdbc-3.32.3.2.jar;C:\Users\IDA\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\IDA\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 lesson6.TestLesson6ArrayContainsNumbers
Тест 0
Исходный массив: [1, 4, 4, 4, 1, 1, 4, 1, 1]
Результат: true
Тест 1
Исходный массив: [1, 4, 1, 4]
Результат: true
Тест 2
Исходный массив: [1, 4]
Результат: true
Тест 3
Исходный массив: []
Результат: false
Тест 4
Исходный массив: [1]
Результат: false
Тест 5
Исходный массив: [4]
Результат: false
Тест 6
Исходный массив: [2, 3, 5]
Результат: false
Тест 7
Исходный массив: [1, 2, 3, 4, 5]
Результат: false
Тест 8
Исходный массив: [1, 1, 1, 1, 4, 4, 4, 4, 0]
Результат: false

Process finished with exit code 0

*/