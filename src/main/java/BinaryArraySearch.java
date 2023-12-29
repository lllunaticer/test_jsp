import java.util.Arrays;
import java.util.Comparator;

import javax.annotation.Nullable;

import org.junit.Before;
import org.junit.Test;

import com.kuaishou.kconf.client.Kconf;
import com.kuaishou.kconf.client.Kconfs;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-03-07
 */
public class BinaryArraySearch {
    private static final Kconf<Config> CONFIG =
            Kconfs.ofJson("overseaServer.music.testConfig", new Config(), Config.class).build();

    static class Config {
        private Integer[][] samples;

        public Integer[][] getSamples() {
            return samples;
        }

    }

    private static Integer[][] orderedSamples;

    @Before
    public void before() {
        orderedSamples =
                Arrays.stream(CONFIG.get().getSamples()).sorted(Comparator.comparingInt(arr -> arr[0]))
                        .toArray(size -> new Integer[size][2]);
    }

    @Test
    public void testBinary() {
        for (int i = 0; i <= 36; i++) {
            binarySearch(i);
        }
    }

    public void binarySearch(int target) {
        // 找到小于等于给定值的中点
        Integer[] search = search(target);
        if (search == null) {
            System.out.println("target " + target + " 不在任何区间内");
            return;
        }
        System.out.println("target " + target + " 在区间 " + Arrays.toString(search) + " 中。"
                + "");
    }


    @Nullable
    private Integer[] search(int target) {
        long l = System.currentTimeMillis();
        long l1 = System.nanoTime();

        int left = 0;
        int right = orderedSamples.length - 1;
        if (orderedSamples[left][0] > target) {
            return null;
        }
        if (orderedSamples[right][1] < target) {
            return null;
        }

        int mid;
        while (left < right) {
            mid = left + (right - left) / 2 + 1;
            if (orderedSamples[mid][0] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        Integer[] result = orderedSamples[left];
        if (result[1] < target) {
            return null;
        }
        return result;
    }
}
