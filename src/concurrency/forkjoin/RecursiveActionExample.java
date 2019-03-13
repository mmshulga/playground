package concurrency.forkjoin;

import java.util.concurrent.RecursiveAction;

public class RecursiveActionExample {

    public static void main(String[] args) {
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(0, 50);
        myRecursiveAction.compute();
    }

    private static class MyRecursiveAction extends RecursiveAction {

        int from;
        int to;
        private static final int THRESHOLD = 5;

        public MyRecursiveAction(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected void compute() {
            String currentThreadName = Thread.currentThread().getName();
            if (from + THRESHOLD > to) {
                for (int i = from; i < to; i++) {
                    try {
                        Thread.sleep(i*10);
                        System.out.println(currentThreadName + ": " + i);
                    } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
            else {
                int middle = (to + from) / 2;
                MyRecursiveAction task1 = new MyRecursiveAction(from, middle);
                MyRecursiveAction task2 = new MyRecursiveAction(middle, to);

                invokeAll(task1, task2);
            }
        }
    }
}
