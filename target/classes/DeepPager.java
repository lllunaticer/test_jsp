import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-04
 */
public class DeepPager<P, R, C> {
    P searchParam;
    C cursor;
    GetByParamDAO<P, R> dao;
    CursorCountSetter<P, C> cursorCountSetter;
    CursorExtractor<R, C> cursorExtractor;
    Predicate<R> filter;

    public List<R> getResourceByCursorWithFilter(int count, int velocity) {
        int innerCount = velocity * count;
        List<R> result = new ArrayList<>();
        boolean flag = true;
        while (flag && result.size() < count) {
            cursorCountSetter.setCursorCount(searchParam, cursor, innerCount);
            List<R> innerData = dao.getByParam(searchParam);
            cursor = CollectionUtils.isEmpty(innerData) ? null : cursorExtractor
                    .extractCursor(innerData.get(innerData.size() - 1));
            result.addAll(innerData.stream().filter(filter).collect(Collectors.toList()));
            if (null == cursor || innerData.size() < innerCount) {
                flag = false;
            }
        }
        return result.stream().limit(count).collect(Collectors.toList());
    }

    public interface CursorCountSetter<P, C> {
        void setCursorCount(P param, C cursor, int count);
    }

    public interface GetByParamDAO<P, R> {
        List<R> getByParam(P param);
    }

    public interface CursorExtractor<R, C> {
        C extractCursor(R result);
    }
}
