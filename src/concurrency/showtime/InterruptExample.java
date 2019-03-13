package concurrency.showtime;

import java.util.concurrent.CountDownLatch;

public class InterruptExample {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            CountDownLatch cdl = new CountDownLatch(1);
            while (!Thread.interrupted()) {
                System.out.println("Not interrupted yet ...");
            }

            try {
                cdl.await();
            } catch (InterruptedException e) {
                System.out.println("Interruption happened!");
            }
        });
        t.start();
        Thread.sleep(1);
        t.interrupt();
        Thread.sleep(1000);
        t.interrupt();
        Thread.sleep(1000);
    }
}