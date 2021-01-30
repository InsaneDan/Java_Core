package lesson4;

public class MultithreadingHW {

    private final Object monitor = new Object();        // монитор
    private final int repeat = 5;                       // количество повторений
    private static char firstSymbol = 'A';              // первый символ в последовательности
    private static final int seqLength = 3;             // количество символов в последовательности
    private static volatile char printSymbol = firstSymbol; // текущий символ; не кэшировать значение, отдавать в основную память

    public static void main(String[] args) throws InterruptedException {
        MultithreadingHW thread = new MultithreadingHW();
        // старт нужного количества потоков (в зависимости от длины последовательности)
        for (int i = 0; i < seqLength; i++) {
            char symbol = (char) (firstSymbol + i);     // символ, который выводится в потоке
            new Thread(() -> thread.printLetter(symbol)).start();
        }
    }

    void printLetter(char currentSymbol) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < repeat; i++) {
                    while (printSymbol != currentSymbol)
                        monitor.wait();
                    System.out.print(currentSymbol);
                    printSymbol = (char) (currentSymbol + 1);
                    if (printSymbol > firstSymbol + seqLength - 1) {
                        printSymbol = firstSymbol;
                        System.out.print(" "); // разрыв между последовательностями (группировка)
                    }
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
