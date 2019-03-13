package concurrency;

import java.util.concurrent.*;

public class CompletionServiceExample {
    public static void main(String[] args) {
        int tasksNumber = 15;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService<Result<Integer>> cs = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < tasksNumber; ++i) {
            cs.submit(() -> {
                Thread.sleep(1000);
                String threadName = Thread.currentThread().getName();
                int randomValue = ThreadLocalRandom.current().nextInt(100);
                return new Result<>(threadName, randomValue);
            });
        }

        for (int i = 0; i < tasksNumber; i++) {
            try {
                Future<Result<Integer>> future = cs.take();
                Result result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

    private static class Result<V> {
        private final String threadName;
        private final V value;

        private Result(String threadName, V value) {
            this.threadName = threadName;
            this.value = value;
        }

        public String getThreadName() {
            return threadName;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "threadName='" + threadName + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}