package general;

import java.util.concurrent.CountDownLatch;

public class NeverendingMain {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        System.out.println("Never ending main started!");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                boolean interrupted = false;
                while (!interrupted) {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        interrupted = true;
                    }
                    System.out.println("Done!");
                }
            }
        });
    }
}
