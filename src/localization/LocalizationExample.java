package localization;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class LocalizationExample {
    public static void main(String[] args) {
        System.out.println("Default locale: " + Locale.getDefault());

        // Resource file in resources/ directory, which is in classpath.
        ResourceBundle defaultRB = ResourceBundle.getBundle("MyBundle");

        Properties defaultProperties = new Properties();
        defaultRB.keySet().forEach((k)->defaultProperties.put(k, defaultRB.getString(k)));
        System.out.println("defaultProperties = " + defaultProperties);


        Locale ruLocale = new Locale("ru", "RU");
        ResourceBundle russianRB = ResourceBundle.getBundle("localization.MyBundle", ruLocale);
        Properties russianProperties = new Properties();
        russianRB.keySet().forEach((k)->russianProperties.put(k, russianRB.getObject(k)));
        System.out.println("russianProperties = " + russianProperties);
    }
}
