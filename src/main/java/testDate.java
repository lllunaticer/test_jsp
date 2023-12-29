import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-04
 */
public class testDate {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:MM";

    public static void main(String[] args) throws ParseException {

        String startAuditTimeStr = "2020-11-09 12:00";
        long time = DateUtils.parseDate(startAuditTimeStr, DATE_PATTERN).getTime();
        System.out.println(time);
    }

    @Test
    public void testCollection() {
        //        String s = "[1,2.0,3.0,4]";
        //        Collection<Number> numbers = ObjectMapperUtils.fromJSON(s, List.class, Number.class);
        //        System.out.println(numbers);
        //        System.out.println(numbers.contains(3d));
        //
        //        Collection<Number> longList = new ArrayList<>();
        //        longList.add(1L);
        //        longList.add(2);
        //        longList.add(3d);
        //        System.out.println(longList);
        //        System.out.println(longList.contains(1));
        //        System.out.println(longList.contains(2));

        Collection<Integer> intCollection = Arrays.asList(1, 3, 2, 5, 10, 9);
        intCollection.stream()
                .sorted(Comparator.comparingInt(i -> i))
                .forEach(i -> {
                    if (i == 3) {
                        return;
                    }
                    System.out.println(i);
                });
        System.out.println(intCollection);
    }

}
