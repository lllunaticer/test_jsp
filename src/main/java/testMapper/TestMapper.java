package testMapper;

import java.util.Locale;

import org.junit.Test;

import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author Xingjian LONG
 * Created on 2021-07-20
 */
public class TestMapper {
    @Test
    public void testMapper() {
        Model model = new Model();
        model.setAge(11);
        model.setName("good");

        ModelA modelA = ObjectMapperUtils.fromJSON(ObjectMapperUtils.toJSON(model), ModelA.class);
        System.out.println(ObjectMapperUtils.toJSON(modelA));

        System.out.println(Locale.forLanguageTag("ur").getDisplayLanguage());
        System.out.println(Locale.forLanguageTag("in").getDisplayLanguage());
        System.out.println(Locale.forLanguageTag("en").getDisplayLanguage());
        System.out.println(Locale.forLanguageTag("bn").getDisplayLanguage());

        String ss = "%s کے لئے اب ویڈیو دیکھ رہا ہو ~";
        System.out.println(String.format(ss, "kk"));
    }
}
