package concurrency.showtime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DeadlockExecutorExample {
    private static final int LIMIT = 10;
    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(1);

        ExecutorService es = Executors.newFixedThreadPool(LIMIT);
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < LIMIT; i++) {
            Future<?> future = es.submit(() -> {
                ExecutorService es2 = Executors.newFixedThreadPool(LIMIT);
                List<Future<?>> futures2 = new ArrayList<>();
                for (int j = 0; j < LIMIT; j++) {
                    Future<?> future2 = es2.submit(() -> {
                        try {
                            System.out.println("Future at level2 started ...");
                            cdl.await();
                            System.out.println("Future at level2 done! ...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    futures2.add(future2);
                    System.out.println("Future at level2 added!");
                }

                for (Future<?> future2 : futures2) {
                    try {
                        System.out.println("Waiting for future at level2 ...");
                        future2.get();
                        System.out.println("One more future at level2 done!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            futures.add(future);
            System.out.println("Future at level1 added!");
        }

        for (Future<?> future : futures) {
            try {
                System.out.println("Waiting for future at level1 ...");
                future.get();
                System.out.println("One more future at level1 done!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(1000);
        cdl.countDown();
        es.shutdown();
    }
}
