package concurrency.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    public static void main(String[] args) {
        Employee employee = new Employee("Mikhail", 90000.0);
        int threadCount = 3;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                long randomNum = (long)(Math.random()*1000);
//                try {
//                    Thread.sleep(randomNum);
//                } catch (InterruptedException e) {}

                System.out.println(threadName + " current e salary: " + employee.getSalary());
                employee.setSalary(randomNum);
                System.out.println(threadName + " current e salary: " + employee.getSalary());
            });
        }

        for (int i = 0; i < threadCount; ++i) {
            threads[i].start();
        }
    }

    public static class Employee {
        private final ReadWriteLock readWriteLock;
        private final String name;
        private double salary;

        public Employee(String name, double salary) {
            readWriteLock = new ReentrantReadWriteLock();
            this.name = name;
            this.salary = salary;
        }

        public double getSalary() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " locking read lock.");
            readWriteLock.readLock().lock();
            System.out.println(threadName + " locked read lock.");
            double salary = this.salary;
            System.out.println(threadName + " unlocking read lock.");
            readWriteLock.readLock().unlock();
            System.out.println(threadName + " unlocked read lock.");
            return salary;
        }

        public void setSalary(double salary) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " locking write lock.");
            readWriteLock.writeLock().lock();
            System.out.println(threadName + " locked write lock.");
            System.out.println(threadName + " setting e salary: " + salary);
            this.salary = salary;
            System.out.println(threadName + " unlocking write lock.");
            readWriteLock.writeLock().unlock();
            System.out.println(threadName + " unlocked write lock.");
        }
    }
}
