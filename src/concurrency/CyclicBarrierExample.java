package concurrency;

import java.util.concurrent.*;

public class CyclicBarrierExample {

    private static class CyclicBarrierTester implements Runnable {
        static int counter = 0;
        CyclicBarrier cb;
        int id;

        public CyclicBarrierTester(CyclicBarrier cb) {
            this.cb = cb;
            this.id  = ++counter;
        }

        @Override
        public void run() {
            System.out.println("Running with id = " + this.id);
            try {
                cb.await();
                System.out.println("Done with id " + this.id);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(2, ()->System.out.println("Done!"));
        CyclicBarrierTester cbt1 = new CyclicBarrierTester(cb);
        CyclicBarrierTester cbt2 = new CyclicBarrierTester(cb);
        new Thread(cbt1).start();
        new Thread(cbt2).start();
    }
}


