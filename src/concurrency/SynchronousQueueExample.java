package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> channel = new SynchronousQueue<>();
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.printf("Thread %s tries inserting a value in channel\n", threadName);
            try {
                channel.put(10);
                System.out.printf("Thread %s successfully inserted a value in channel\n", threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(3000);

        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.printf("Thread %s tries reading a value from channel\n", threadName);
            try {
                int i = channel.take();
                System.out.printf("Thread %s successfully read a value from channel (%d)\n", threadName, i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
