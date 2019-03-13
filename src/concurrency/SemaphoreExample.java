package concurrency;

import java.util.concurrent.*;

public class SemaphoreExample {

    private static class SemaphoreTester extends Thread {
        final Semaphore semaphore;
        final String name;
        public SemaphoreTester(String name, Semaphore semaphore) {
            this.name = name;
            this.semaphore = semaphore;
        }

        public void run() {
            System.out.println(name + " is acquiring the lock.");
            try {
                semaphore.acquire();
                System.out.println(name + " acquired the lock.");
                System.out.println(name + " is performing internal operations.");
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                }
                System.out.println(name + " performed all of its operations. Releasing the semaphore.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                semaphore.release();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        new SemaphoreTester("A", semaphore).start();
        new SemaphoreTester("B", semaphore).start();
        new SemaphoreTester("C", semaphore).start();
        new SemaphoreTester("D", semaphore).start();
        new SemaphoreTester("E", semaphore).start();
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleAtFixedRate(()->
                System.out.println("Remaining locks: " + semaphore.availablePermits()),
                0, 1, TimeUnit.SECONDS);
        Thread.sleep(10000);
        es.shutdown();
    }
}
