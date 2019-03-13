package concurrency.collections;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;

public class ConcurrentMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentMap<Integer, Integer> cMap = new ConcurrentHashMap<>();

        ScheduledExecutorService es = Executors.newScheduledThreadPool(2);
        es.scheduleAtFixedRate(()->{
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(threadName + " before put: " + cMap);
                    cMap.put(i, i*i*i);
                    Thread.sleep(1000);
                    System.out.println(threadName + " after put: " + cMap);
                }
                catch (InterruptedException ignored){}
            }
        }, 0, 1, TimeUnit.SECONDS);

        es.scheduleAtFixedRate(()->{
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " before iterating: " + cMap);
            for (Map.Entry<Integer, Integer> entry: cMap.entrySet()) {
                System.out.println(threadName + " entry: " + entry);
            }
            System.out.println(threadName + " after iterating: " + cMap);
        }, 0, 1, TimeUnit.SECONDS);
        Thread.sleep(10000);
        es.shutdown();
    }
}
