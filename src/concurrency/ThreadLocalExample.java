package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalExample {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(ThreadLocalExample.get());
            }).start();
        }
    }

    private static final AtomicInteger ai = new AtomicInteger(0);
    private static final ThreadLocal<Integer> i = ThreadLocal.withInitial(ai::incrementAndGet);

    public static Integer get() {
        return i.get();
    }
}
