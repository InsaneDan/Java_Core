package lesson1.session;

public class Box implements Comparable<Box> {
    private int size;

    public Box(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(Box o) {
        return size - o.size;
    }

    //    @Override
//    public int compareTo(Object o) {
//        if (this == o) {
//            return 0;
//        }
//
//        if (!(o instanceof Box)) {
//            throw new IllegalArgumentException();
//        }
//
//        Box another = (Box) o;
//
//        return size - another.size;
//    }
}
