package concurrency.collections;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Как только тред A схватил итератор, он создал "копию",
 * так что если другой тред B внесет изменения в list, то эти изменения
 * не отобразятся в итераторе(!) треда A. В list отобразятся.
 */
public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3));

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i: list) {
                    System.out.println("A: i = " + i);
                    list.add(i*10);
                    list.remove(new Integer(i));
                    try {
                        Thread.sleep(i*1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("A: list = " + list);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i: list) {
                    System.out.println("B: i = " + i);
                    list.add(i*100);
                    list.remove(new Integer(i*10));
                    try {
                        Thread.sleep(i*5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("B: list = " + list);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i: list) {
                    System.out.println("C: i = " + i);
                    list.add(i*1000);
                    list.remove(new Integer(i*100));
                    try {
                        Thread.sleep(i*10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("C: list = " + list);
                }
            }
        }).start();

        try {
            Thread.sleep(10000);
            System.out.println("list = " + list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
