import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableMap;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-01-20
 */
public class IndexGetterImpl implements IndexGetter{
    @Override
    public List getIndex(String type) {
        if(type.equals("mapList")){
            return Arrays.asList(ImmutableMap.of("1", 1), ImmutableMap.of("2",2));
        }
        if(type.equals("longList")){
            return Arrays.asList(1L, 2L);
        }
        return Collections.singletonList("error");
    }
}
