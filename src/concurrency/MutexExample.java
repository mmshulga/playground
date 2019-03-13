package concurrency;

import sun.awt.Mutex;

public class MutexExample {
    public static void main(String[] args) {
        Mutex m = new Mutex();
        new MutexTester("A", m).start();
        new MutexTester("B", m).start();
        new MutexTester("C", m).start();
        new MutexTester("D", m).start();
        new MutexTester("E", m).start();
    }

    private static class MutexTester extends Thread {

        final Mutex m;
        final String name;

        private MutexTester(String name, Mutex m) {
            this.m = m;
            this.name = name;
        }

        public void run() {
            System.out.println(name + " ready to lock.");
            m.lock();
            System.out.println(name + " successfully locked.");
            System.out.println(name + " performing internal operations.");
            System.out.println(name + " ready to unblock.");
            m.unlock();
            System.out.println(name + " successfully unlocked");
        }
    }
}
