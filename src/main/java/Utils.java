import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

import org.junit.Test;

import FunctionTest.Mapper.HandlerImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-02-17
 */
public class Utils {

    public static <R> R nullThenRetry(Callable<R> call, int retryTimes) {
        if (retryTimes < 0 || retryTimes > 10) {
            retryTimes = 1;
        }
        R r = null;
        while (r == null && retryTimes > 0) {
            try {
                r = call.call();
                retryTimes--;
            } catch (Exception e) {
            }
        }
        if (r == null) {
            throw new RuntimeException("null result");
        }
        return r;
    }

    @Test
    public void testUtils() {
        int i = 1;
        switch (i) {
            case 1:
            case 3:
            case 2:
                System.out.println("good");
                break;
            default:
                System.out.println("bad");
        }

        BiFunction<Integer, Integer, Boolean> biFunction = (a, b) -> ((a >>> b) & 1) == 1;
        System.out.println(biFunction.apply(4, 2));
    }

    public Integer randomGet(int i) {

        if ((new Random().nextInt() + i) % 3 == 0) {
            return 1;
        } else {
            return null;
        }
    }

    @Test
    public void generateSql() {
        String template =
                "ALTER TABLE `photo_music_tag_index_%d` ADD INDEX idx_merged_id_create_time (`merged_id`,"
                        + "`create_time`);";
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format(template, i));
        }
    }

    @Test
    public void testDate() {
        DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
        System.out.println(DATE_FORMAT.format(new Date()));
    }

    @Test
    public void testMov() {
        BiFunction<Integer, Integer, Boolean> f = (a, b) -> ((a >>> (b - 1)) & 1) == 1;
        List<Integer> bs = Arrays.asList(2, 3, 4, 10);
        for (int b : bs) {
            System.out.println("b + " + b + " res - " + f.apply(16, b));
        }

    }

    @Test
    public void testSql() {
        int[] a = new int[10];
        Set<Integer> set = new HashSet<>();
        set.stream().findFirst().get();
    }


}
