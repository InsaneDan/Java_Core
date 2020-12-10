package lesson5.thread;

public class TestCreateThread {

    private volatile Object mockObject;

    static class MyRunnableClass implements Runnable {

        private final int delayInMilli;

        public MyRunnableClass(int delayInMilli) {
            this.delayInMilli = delayInMilli;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": " + i);

                    System.out.println(Thread.currentThread().getName() + ": " + i
                            + " with priority: " + Thread.currentThread().getPriority());

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread has been interrupted");
                        return;
                    }
                    Thread.sleep(delayInMilli);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted from exception");
                    e.printStackTrace();
                    return;
                }
            }
            System.out.println("finish thread 1");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Main priority: " + Thread.currentThread().getPriority());

        Thread thread1 = new Thread(new MyRunnableClass(10), "Поток 1");
//        thread1.start();

        Thread.sleep(100);
        Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
//                    System.out.println(Thread.currentThread().getName() + ": " + i);
                System.out.println(Thread.currentThread().getName() + ": " + i
                        + " with priority: " + Thread.currentThread().getPriority());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Поток 2");
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();

        thread1.interrupt();

//        thread1.join();
//        thread2.join();

//        Thread.sleep(5000);
//        thread1.start();
//

//        Thread.sleep(10);
//        thread1.interrupt();

        while (thread1.isAlive()) {
            Thread.sleep(10);
        }
//        thread1.join();

//        System.out.println("finish");

//        thread2.start();
//        while (thread2.isAlive()) {
//            Thread.sleep(10);
//        }
//        thread2.join();
//        thread2.start();
        System.out.println("the end");
    }

}
