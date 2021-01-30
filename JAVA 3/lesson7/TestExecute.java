package lesson7;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestExecute {

    public static void start (Class<TestSet> c) {
        List<Method> listMethods = new ArrayList<>();
        Method[] arrMethods = c.getDeclaredMethods();
        Method bsMethod = null;
        Method asMethod = null;

        TestSet testSet = null;
        try {
            testSet = c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Method m : arrMethods) {
            if (m.isAnnotationPresent(Test.class)) {
                listMethods.add(m);
            } else if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (bsMethod != null) {throw new RuntimeException("Можно использовать только одну аннотацию @BeforeSuite");}
                bsMethod = m;
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (asMethod != null) {throw new RuntimeException("Можно использовать только одну аннотацию @AfterSuite");}
                asMethod = m;
            }
        }

        listMethods.sort(Comparator.comparingInt((Method i) -> i.getAnnotation(Test.class).priority()).reversed());

        if (bsMethod != null) listMethods.add(0, bsMethod);
        if (asMethod != null) listMethods.add(asMethod);

        try {
            if (listMethods.size() > 0) {
                for (Method m : listMethods) {
                    m.invoke(testSet);
                    if (m.isAnnotationPresent(Test.class)) {
                        System.out.println("приоритет: " + m.getAnnotation(Test.class).priority());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
