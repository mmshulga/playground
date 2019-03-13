package concurrency;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        Callable<Integer> callable = () -> 101;
        ScheduledFuture<Integer> sf = ses.schedule(callable, 10, TimeUnit.SECONDS);
        while (! sf.isDone()) {
            System.out.println("Not done yet...");
            Thread.sleep(1000);
        }
        int result = sf.get(3, TimeUnit.SECONDS);
        System.out.println("result = " + result);
        ses.shutdown();
    }
}
