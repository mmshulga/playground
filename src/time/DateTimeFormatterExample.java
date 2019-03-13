package time;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterExample {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY|MM|dd & HH|mm|ss Z");
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(dtf.format(zdt));
    }
}
