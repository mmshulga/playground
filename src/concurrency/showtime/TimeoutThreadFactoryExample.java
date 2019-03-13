package concurrency.showtime;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeoutThreadFactoryExample {
    public static void main(String[] args) {
        TimeoutThreadFactory tf = new TimeoutThreadFactory(1L, 5L);
        tf.initializeGuardThread();
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(4, 10, 1, TimeUnit.HOURS, new LinkedBlockingQueue<>(), tf);

        CountDownLatch cdl = new CountDownLatch(1);
        Future<?> f = tpe.submit(() -> {
            try {
                System.out.println("Hey!");
                cdl.await();
            }
            catch (InterruptedException e) {
                System.out.println("Long-running runnable was automatically interrupted!");
            }
        });
    }
}

final class TimeoutThreadFactory implements ThreadFactory {
    private final Map<Thread, Long> timeoutMap;
    private final AtomicBoolean guardThreadInitialized;
    private final AtomicBoolean isActive;
    private final long checkInterval;
    private final long maximumTimeout;

    public TimeoutThreadFactory(long checkInterval, long maximumTimeout) {
        this.checkInterval  = checkInterval;
        this.maximumTimeout = maximumTimeout;
        this.isActive       = new AtomicBoolean(true);
        this.timeoutMap     = new HashMap<>();
        this.guardThreadInitialized = new AtomicBoolean(false);
    }

    @Override
    public Thread newThread(Runnable r) {
        if (!guardThreadInitialized.get()) {
            throw new RuntimeException("Be sure to call initializeGuardThread() method before submitting new threads!");
        }

        if (!isActive.get()) {
            throw new RuntimeException("Factory shutdown already");
        }

        Thread t = new Thread(r);
        long endsAt = Instant.now().getEpochSecond() + maximumTimeout;
        timeoutMap.put(t, endsAt);
        return t;
    }

    public void shutdown() {
        isActive.set(false);
    }

    public final synchronized void initializeGuardThread() {
        Thread guardThread = new Thread(() -> {
            try {
                List<Thread> removeList = new ArrayList<>();
                while (isActive.get()) {
                    Thread.sleep(checkInterval);
                    for (Map.Entry<Thread, Long> entry : timeoutMap.entrySet()) {
                        Thread t = entry.getKey();
                        Long end = entry.getValue();
                        if (Instant.now().getEpochSecond() >= end) {
                            t.interrupt();
                            removeList.add(t);
                        }
                    }
                    for (Thread t : removeList) {
                        timeoutMap.remove(t);
                    }
                    removeList.clear();
                }
            } catch (InterruptedException e) {
                System.out.println("TimeoutThreadFactory interrupted!");
            }
        }, "timeout-thread-factory-guard");
        guardThread.setDaemon(true);
        guardThread.start();
        guardThreadInitialized.set(true);
    }
}