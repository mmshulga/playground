package localization;

import java.util.ListResourceBundle;

public class MyBundle_ru_RU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"hello", "Privet"},
                {"country", "Russia"},
                {"person", new Person("Mikhail", 22)}
        };
    }
}
