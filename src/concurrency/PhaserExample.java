package concurrency;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();

        new Thread(new TaskExample(0, phaser)).start();
        new Thread(new TaskExample(1, phaser)).start();
        new Thread(new TaskExample(2, phaser)).start();
        new Thread(new TaskExample(3, phaser)).start();
        new Thread(new TaskExample(4, phaser)).start();
    }

    private static class TaskExample implements Runnable {
        private int id;
        private Phaser phaser;

        public TaskExample(int id, Phaser phaser) {
            this.id     = id;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            int currentPhase = phaser.register();
            if (currentPhase < 0) {
                System.out.println("Phaser already terminated!");
                phaser.arriveAndDeregister();
                return;
            }
            for (int i = 1; i <= 10; i++) {
                doWork();
            }
        }

        private void doWork() {
            int currentPhase = phaser.getPhase();
            System.out.printf("Task with id=%d registered at phase %d!\n", id, currentPhase);
            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            phaser.arriveAndAwaitAdvance();
            System.out.printf("Task with id=%d done phase %d!\n", id, currentPhase);
        }
    }
}
