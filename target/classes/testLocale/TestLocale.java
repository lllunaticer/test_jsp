package testLocale;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

/**
 * @author Xingjian LONG
 * Created on 2021-06-22
 */
public class TestLocale {
    @Test
    public void testLocale() {
        Locale pak = Locale.forLanguageTag("pak");
        System.out.println(pak.getDisplayLanguage());

        Locale in = Locale.forLanguageTag("in");
        System.out.println(in.getDisplayLanguage());

        Locale ur = Locale.forLanguageTag("ur");
        System.out.println(ur.getDisplayLanguage());

        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(integers.subList(0, integers.size()));
    }
}
