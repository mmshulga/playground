package concurrency.showtime;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<>();

        MyRunnable<Integer> r1 = new MyRunnable<>(exchanger, 10);
        MyRunnable<Integer> r2 = new MyRunnable<>(exchanger, 100);

        new Thread(r1).start();
        Thread.sleep(3000);
        new Thread(r2).start();
    }

    static class MyRunnable<T> implements Runnable {
        private final Exchanger<T> exchanger;
        private final T value;

        public MyRunnable(Exchanger<T> exchanger, T value) {
            this.exchanger = exchanger;
            this.value = value;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.printf("Thread %s exchanging value %s ...\n", threadName, value);
            try {
                T other = exchanger.exchange(value);
                System.out.printf("Thread %s received value %s \n", threadName, other);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
