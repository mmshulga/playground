package concurrency.showtime;

public class UncaughtExceptionHandlerExample {
    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("Oops!");
        });
        new Thread(() -> {
            throw new RuntimeException("asd");
        }).start();

        Thread.sleep(1000);
    }
}
