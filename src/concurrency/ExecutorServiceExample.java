package concurrency;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    private static Set<LocalDateTime> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService appender = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService remover = Executors.newSingleThreadScheduledExecutor();
        appender.scheduleAtFixedRate(()-> {
            set.add(LocalDateTime.now());
            System.out.println(set);
        }, 0, 1, TimeUnit.SECONDS);
        remover.scheduleAtFixedRate(() -> {
            set.clear();
            System.out.println(set);
        }, 3, 2, TimeUnit.SECONDS);

        ScheduledExecutorService stopper = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> f = stopper.schedule(()-> {
            System.out.println("Stopping");
            appender.shutdown();
            remover.shutdown();
            System.out.println(set);
        }, 10, TimeUnit.SECONDS);
        stopper.shutdown();
    }
}
