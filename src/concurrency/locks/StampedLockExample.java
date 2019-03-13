package concurrency.locks;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample implements Runnable {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new StampedLockExample(false)).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new StampedLockExample(true)).start();
        }
    }

    boolean doWrite;

    public StampedLockExample(boolean doWrite) {
        this.doWrite = doWrite;
    }

    private static StampedLock stampedLock = new StampedLock();

    void read() {
        long readLockStamp = stampedLock.tryReadLock();
        if (stampedLock.validate(readLockStamp)) {
            System.out.println("tryReadLock succeeded");
        }
        else {
            System.out.println("tryReadLock failed, awaiting ...");
            readLockStamp = stampedLock.readLock();
        }
        System.out.println("Doing read ...");
        System.out.println("Releasing read lock ...");
        stampedLock.unlockRead(readLockStamp);
        System.out.println("Read lock released!");
    }

    void write() {
        long writeLockStamp = stampedLock.tryWriteLock();
        if (stampedLock.validate(writeLockStamp)) {
            System.out.println("tryWriteLock succeeded");
        }
        else {
            System.out.println("tryWriteLock failed, awaiting ...");
            writeLockStamp = stampedLock.writeLock();
        }
        System.out.println("Doing write ...");
        System.out.println("Releasing write lock ...");
        stampedLock.unlockWrite(writeLockStamp);
        System.out.println("Write lock released!");
    }

    @Override
    public void run() {
        while (true) {
            if (doWrite) {
                write();
            } else {
                read();
            }

            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}