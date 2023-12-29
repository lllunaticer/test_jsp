package FunctionTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.springframework.beans.BeanUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-01-20
 */
public enum Mapper {
    rpc1(list -> list.containsAll(Arrays.asList("field1", "field1")),
            (fieldList, ids) -> HandlerFactory.getHandler(HandlerImpl.class).getByIds(fieldList, ids));

    private Predicate<List<String>> predicate;
    private BiFunction<List<String>, List<Long>, Map<Long, Map<String, Object>>> handler;

    Mapper(Predicate<List<String>> predicate,
            BiFunction<List<String>, List<Long>, Map<Long, Map<String, Object>>> handler) {
        this.predicate = predicate;
        this.handler = handler;
    }

    private interface Handler {
        Map<Long, Map<String, Object>> getByIds(List<String> fields, List<Long> ids);
    }

    private static class HandlerFactory {
        public static Handler getHandler(Class<? extends Handler> cls) {
            try {
                return cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class HandlerImpl implements Handler {
        public Map<Long, Map<String, Object>> getByIds(List<String> fields, List<Long> ids) {
            System.out.println(fields);
            return null;
        }
    }

    public static void main(String[] args) {
        Mapper[] values = Mapper.values();
        List<String> needFields = new ArrayList<>();
        needFields.add("field1");
        needFields.add("field2");

        List<Long> ids = Arrays.asList(1L, 2L);

        for (Mapper value : values) {
            if (value.predicate.test(needFields)) {
                value.handler.apply(needFields, ids);
            }
        }
    }
}
