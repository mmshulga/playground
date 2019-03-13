package time;

import java.time.Instant;
import java.time.ZonedDateTime;

public class InstantExample {
    public static void main(String[] args) {
        // Работает только с форматами, хранящими таймзону!!!
        Instant instant = Instant.from(ZonedDateTime.now());
        System.out.println(instant);
    }
}
