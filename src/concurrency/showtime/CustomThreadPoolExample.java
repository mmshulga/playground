package concurrency.showtime;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomThreadPoolExample {
    private static final int CORE_POOL_EXECUTOR = 1;
    private static final int MAXIMUM_POOL_SIZE  = 2;
    private static final Logger logger = Logger.getLogger("my.logger");

    public static void main(String[] args) throws InterruptedException {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (throwable, exception) -> {
            logger.log(Level.SEVERE, String.format("Thread %s threw %s\n", throwable.getName(), exception));
        };

        ThreadFactory threadFactory = r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            t.setPriority(Thread.NORM_PRIORITY);
            t.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            return t;
        };

        RejectedExecutionHandler rejectedExecutionHandler = (runnable, executor) -> {
            logger.log(Level.SEVERE, String.format("Runnable %s failed in executor %s\n", runnable, executor));
        };

        ThreadPoolExecutor customExecutor =
                new ThreadPoolExecutor(CORE_POOL_EXECUTOR,
                        MAXIMUM_POOL_SIZE,
                        100L,
                        TimeUnit.SECONDS,
                        new SynchronousQueue<>(),
                        threadFactory,
                        rejectedExecutionHandler);

        for (int i = 0; i < 10; i++) {
            customExecutor.execute(()-> {
                throw new RuntimeException("something bad happened ...");
            });
        }

        Thread.sleep(3000);
        customExecutor.shutdown();
    }
}
