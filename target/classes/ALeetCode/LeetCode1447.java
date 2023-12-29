package ALeetCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.kuaishou.explore.model.ExploreLocale;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-02-10
 */
public class LeetCode1447 {
    public List<String> getN(int n) {


        List<String> res = new ArrayList<>();
        if (n == 1) {
            return res;
        }
        res.add("1/" + n);
        int[] dic = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i * j >= n) {
                    if (i * j == n) {
                        dic[i] = 1;
                        dic[n - i] = 1;
                    }
                    break;
                }
            }
        }
        for (int i = 2; i < n; i++) {
            if (dic[i] == 0) {
                res.add(i + "/" + n);
            }
        }
        return res;
    }

    @Test
    public void testGet() {
        System.out.println(getN(6));
    }

    @Test
    public void testTime() {
        System.out.println(toMills(beginOfMonth(0)));
        System.out.println(new Date(toMills(beginOfMonth(0))));

        System.out.println(toMills(beginOfMonth(1)));
        System.out.println(new Date(toMills(beginOfMonth(1))));

        System.out.println(toMills(beginOfMonth(10)));
        System.out.println(new Date(toMills(beginOfMonth(10))));

        System.out.println(toMills(beginOfMonth(11)));
        System.out.println(new Date(toMills(beginOfMonth(11))));
    }

    public static long toMills(LocalDateTime localDateTime) {
        return localDateTime.toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
    }

    public static LocalDateTime beginOfMonth(int offset) {
        return LocalDateTime.of(LocalDate.now().plusMonths(offset).withDayOfMonth(1), LocalTime.MIN);
    }
}
