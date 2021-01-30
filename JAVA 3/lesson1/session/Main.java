package lesson1.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(10, 20, 30));
        List<String> strList = new ArrayList<>();
//        System.out.println(sumOfList(nums));

        int x = getFirstElement(nums);

//        Collections.copy();
    }

    public static <T> T getFirstElement(List<T> list){
        return list.get(0);
    }

    public static double sumOfList(List<? extends Number> nums) {
        double d = 0.0;
        for (int i = 0; i < nums.size(); i++) {
            d += nums.get(i).doubleValue();
        }
        return d;
    }

    private static void boxWithNumbersEx() {
        BoxWithNumbers<Float> boxFloat1 = new BoxWithNumbers<>(10f, 20f, 30f);
        BoxWithNumbers<Float> boxFloat2 = new BoxWithNumbers<>(10f, 20f, 30f);

        System.out.println(boxFloat1.avg());
        System.out.println(boxFloat2.avg());
        System.out.println(boxFloat1.sameAvg(boxFloat2));

        BoxWithNumbers<Integer> boxInteger = new BoxWithNumbers<>(10, 20, 30);
        System.out.println(boxInteger.avg());

        System.out.println(boxFloat1.sameAvg(boxInteger));
    }

    private static void boxGenEx() {
        BoxGen<Integer> bgi1 = new BoxGen<>(10);
        BoxGen<Integer> bgi2 = new BoxGen<>(20);

        bgi1.setObj(8);

        int sum = bgi1.getObj() + bgi2.getObj();
        System.out.println("sum = " + sum);

        BoxGen<String> bgstr = new BoxGen<>("fgdfg");

        System.out.println(bgi1.getClass().getName());
        System.out.println(bgstr.getClass().getName());
    }

    private static void simpleBoxEx() {
        SimpleBox box1 = new SimpleBox(10);
        SimpleBox box2 = new SimpleBox(20);
        /////
        box1.setObj(67);
        /////
        if (box1.getObj() instanceof Integer && box2.getObj() instanceof Integer) {
            int sum = (int) box1.getObj() + (int) box2.getObj();
            System.out.println("sum = " + sum);
        }
    }
}
