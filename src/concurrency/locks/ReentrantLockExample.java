package concurrency.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) {
        final List<String> list = new ArrayList<>();

        ReentrantLockTester<String> tester = new ReentrantLockTester<>("A", list);

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                tester.add("Item " + i + " added by T1");
                System.out.println("T1 successfully added another element.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                tester.add("Item " + i*10 + " added by T2");
                System.out.println("T2 successfully added another element.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class ReentrantLockTester<E> {
        private final ReentrantLock reentrantLock;
        private final List<E> list;
        private final String name;
        public ReentrantLockTester(String name, final List<E> list) {
            this.reentrantLock = new ReentrantLock();
            this.list = new ArrayList<>(list);
            this.name = name;
        }

        public void add(E e) {
            reentrantLock.lock();
            System.out.println("Current locks held: " + reentrantLock.getHoldCount());
            list.add(e);
            printInfo(e);
            reentrantLock.unlock();
        }

        private void printInfo(E e) {
            reentrantLock.lock();
            System.out.println("Current locks held: " + reentrantLock.getHoldCount());
            System.out.println(e);
            reentrantLock.unlock();
        }

    }
}
