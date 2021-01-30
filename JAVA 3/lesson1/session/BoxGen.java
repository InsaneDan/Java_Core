package lesson1.session;

public class BoxGen<T> {
    //T type, E element, K key, V value, N number
    private T obj;

    public BoxGen(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    private void test(){
//        T t = new T();
//        T[] ts = new T[10];
    }
}
