package concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        }).thenApplyAsync(i->i*i)
                .thenApplyAsync(i->i+1)
                .whenCompleteAsync((i,e)->System.out.println(i));

        System.out.println("Before completable future is done!");
        try {
            System.out.println("cf.get() = " + cf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
