package lesson5;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу (ждет): " + description);
                // получаем разрешение от семафора на въезд в туннель
                MainClass.TUNNEL.acquire();
                // когда разрешение получено, участник едет дальше
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                // участник проехал туннель - возвращаем разрешение семафору
                MainClass.TUNNEL.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
