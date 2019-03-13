package localization;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatExample {
    public static void main(String[] args) {
        long l = 1000000000L;
        double d = 1321123.2343D;


        System.out.println(Locale.getDefault());
        NumberFormat nm = NumberFormat.getInstance();
        System.out.println(nm.format(l));
        System.out.println(nm.format(d));

        System.out.println();

        Locale ru = new Locale("ru", "RU");
        System.out.println(ru);
        Locale.setDefault(ru);
        System.out.println(nm.format(l));
        System.out.println(nm.format(d));
    }
}
