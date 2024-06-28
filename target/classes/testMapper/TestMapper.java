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
        Model model = new Model("good", 11);

        Model modelA = ObjectMapperUtils.fromJSON(ObjectMapperUtils.toJSON(model), Model.class);
        System.out.println(ObjectMapperUtils.toJSON(modelA));
    }
}
