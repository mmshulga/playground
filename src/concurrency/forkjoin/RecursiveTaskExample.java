package concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class RecursiveTaskExample {
    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 91, 10};
        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);
        System.out.println("task.compute() = " + task.compute());
    }

    private static class MyRecursiveTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 2;

        private int from = 0;
        private int to  = 0;
        private int[] array;

        private MyRecursiveTask(int[] array, int from, int to) {
            this.array = array;
            this.from = from;
            this.to = to;
        }

        @Override
        public Integer compute() {
            if (from + THRESHOLD > to) {
                int middle = (from + to) / 2;
                MyRecursiveTask task1 = new MyRecursiveTask(array, from, middle);
                MyRecursiveTask task2 = new MyRecursiveTask(array, middle, from);

                task1.fork();

                return task2.compute() + task1.join();
            }
            else {
                int sum = 0;
                for (int i = from; i < to; i++) {
                    sum += array[i];
                }
                return sum;
            }
        }
    }
}
