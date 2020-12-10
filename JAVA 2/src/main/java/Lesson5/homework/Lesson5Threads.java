package Lesson5.homework;

import java.util.Arrays;

public class Lesson5Threads implements Runnable {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
    float[] arr1 = new float[h];
    float[] arr2 = new float[h];

    public static void main(String[] args) {
        Lesson5Threads t = new Lesson5Threads();
        t.singleThreaded();
        t.multiThreaded();
    }

    void singleThreaded() {
        Arrays.fill(arr, 1);                        // заполнить единицами
        long a = System.currentTimeMillis();            // засечь начало выполнения
        for (int i = 0; i < size; i++) {                // пересчитать значения каждой ячейки
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();            // время окончания выполнения
        System.out.println("Время выполнения метода 1 (однопоточный): " + (b - a));
    }

    void multiThreaded() {
        Arrays.fill(arr, 1);                        // заполнить единицами
        long a = System.currentTimeMillis();            // засечь начало выполнения
        // сплит массива на две части
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, arr.length - h);

        // отдельный поток для каждого массива
        Lesson5Threads runner = new Lesson5Threads();
        Thread firstThread = new Thread(runner);
        firstThread.setName("firstThread");
        Thread secondThread  = new Thread(runner);
        secondThread.setName("secondThread");
        firstThread.start();
        secondThread.start();

        // склейка частей массива в один
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        long b = System.currentTimeMillis();            // время окончания выполнения
        System.out.println("Время выполнения метода 2 (в двух параллельных потоках): " + (b - a));
    }

    public void run() {
        String nameThread = Thread.currentThread().getName();
        for (int i = 0; i < h; i++) {
            switch (nameThread) {
                case "firstThread" -> arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                case "secondThread" -> arr2[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                // TODO: 10.12.2020 попробовать большее количество потоков
            }
        }
    }
}

/* КОНСОЛЬ
"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\lib\idea_rt.jar=51427:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\GeekBrains\JAVA\JAVA 2\target\classes;C:\Users\IDA\.m2\repository\org\openjfx\javafx-controls\11\javafx-controls-11.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-controls\11\javafx-controls-11-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-graphics\11\javafx-graphics-11.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-graphics\11\javafx-graphics-11-win.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-base\11\javafx-base-11.jar;C:\Users\IDA\.m2\repository\org\openjfx\javafx-base\11\javafx-base-11-win.jar" Lesson5.homework.Lesson5Threads
Время выполнения метода 1 (однопоточный): 2439
Время выполнения метода 2 (в двух параллельных потоках): 72

Process finished with exit code 0

 */