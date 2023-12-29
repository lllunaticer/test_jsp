package SetTest;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xingjian LONG
 * Created on 2021-05-25
 */
public class testSet {
    private static final Logger LOGGER = LoggerFactory.getLogger(testSet.class);

    @Test
    public void testSet() {
        String[] t1 = new String[] {
                "ANDROID_b294fb9acaaae6d7",
                "ANDROID_d486deb653142e9c",
                "ANDROID_1c55d16f999f3ce4",
                "ANDROID_70167399c8f2b4d0",
                "327AB02B-001B-41B3-81EA-FE4416B5F19F",
                "A6262B9B-F9CF-4186-871C-66B66C9C4C2E",
                "ANDROID_9627e2c7b39e1bf1",
                "F88C506F-8AFD-47CC-887D-866F5ECDCB07",
                "7F391A30-9215-41D5-8432-C966EF6301E3",
                "ANDROID_eb6a59363705d691",
                "ANDROID_fa4bba3a35f30268",
                "ANDROID_a49a909439c84c88",
                "ANDROID_f9657a6221c1b036",
                "ANDROID_0f27f2ac8f8e4463",
                "ANDROID_2db3cdf68e34824f",
                "ANDROID_ef346f55fcbe00a7",
                "ANDROID_afe022e104330b05",
                "ANDROID_d5a58705643bb236",
                "ANDROID_97efcd2014534b93",
                "ANDROID_f6725400f7d9e17c",
                "ANDROID_2d755aeec089c5d3",
                "ANDROID_cb816687719ea622",
                "ANDROID_c3a3973aeef09041",
                "ANDROID_1b284788d5181867",
                "ANDROID_f51505affc4bbc56",
                "ANDROID_bd12a6b6c39cca00",
                "ANDROID_878409365858fb5c",
                "ANDROID_035f4d83af47fccb",
                "ANDROID_5ab720cd635c1eb0",
                "ANDROID_c917f543404e5c15",
                "ANDROID_2b229e89ba588321",
                "ANDROID_30e923978907a1e8",
                "ANDROID_4d528f309d4492fe",
                "ANDROID_79ebba2e7c0be0db",
                "ANDROID_9486052e2c19f85d",
                "ANDROID_999ba825ebecfe3b",
                "ANDROID_9eda9ba906bca9f9",
                "ANDROID_a0662a3fdba3ed3e",
                "ANDROID_a66a330def689a4e",
                "ANDROID_ab6fe19cfebc972d",
                "ANDROID_cbca0957efb33348",
                "ANDROID_d3033a38ee69edea",
                "ANDROID_ddb844fa8aac644a",
                "ANDROID_b9b55bf2802326a1",
                "ANDROID_4117a7853cb84e68",
                "ANDROID_86e09784e27c2d05",
                "ANDROID_247572a3e10b16d2",
                "ANDROID_dc96a29472d24131",
                "ANDROID_fde6743e517f4bd8",
                "ANDROID_5f0ad6aadf4f322c",
                "ANDROID_859a8ba337f5e5a0",
                "ANDROID_2fce6aaea6242f71",
                "ANDROID_975e35c5c2aba949",
                "CFB50C45-46FA-4861-B38F-7A327FEF37D9",
                "C7656889-7342-43C5-852C-F99E1F649531",
                "8012D9B3-9DE9-4681-A09D-88D2BE0FA5B4",
                "327AB02B-001B-41B3-81EA-FE4416B5F19F",
                "ANDROID_8fa68357e779d076",
                "ANDROID_40362df62e1f7d1e",
                "A1933572-B58C-473F-82C5-9A037791D73C",
                "ANDROID_ebc1acd796d5beb9",
                "29AECB33-6480-42FB-A707-DF2725D5581C",
                "ANDROID_845855cb9fcd7f07",
                "CC23CD87-6863-45A4-9C2F-CE0776B78EE4",
                "82BB01C3-2168-406F-88AC-8C3427E531B4",
                "ANDROID_f20eebeb81b8aa8e",
                "ANDROID_b726f8f7e840d004"
        };
        long[] kk = new long[] {1, 2};
        Set<Long> collect = Arrays.stream(kk).boxed().collect(Collectors.toSet());

        String[] t2 = new String[] {
                "/rest/bulldog/feed/hot/slide",
                "/rest/bulldog/feed/myfollow",
                "/rest/bulldog/feed/nearby",
                "/rest/bulldog/user/profile",
                "/rest/bulldog/comment/list/v2",
                "/rest/bulldog/photo/comment/add",
                "/rest/bulldog/relation/fol",
                "/rest/bulldog/feed/liked",
                "/rest/bulldog/feed/profile",
                "/rest/bulldog/tag/feedV2",
                "/rest/bulldog/music/agg/photo",
                "/rest/bulldog/photo/get",
                "/rest/bulldog/config/coldStart",
                "/rest/bulldog/config/hotStart",
                "/rest/bulldog/log/web/collect",
                "/rest/bulldog/share/shorten",
                "/rest/bulldog/feed/detail/recommend",
                "/rest/bulldog/feed/selected",
                "/rest/bulldog/geo/search",
                "/rest/bulldog/user/search",
                "/rest/bulldog/tag/search",
                "/rest/bulldog/music/search",
                "/rest/bulldog/search/all",
                "/rest/bulldog/photo/search",
                "/rest/bulldog/search/top/music",
                "/rest/bulldog/user/getInfo",
                "/rest/bulldog/push/get",
                "/rest/bulldog/w/feed/hot",
                "/rest/o/feed/hot",
                "/rest/o/comment/list/v2",
                "/rest/o/user/profile",
                "/rest/o/relation/fol",
                "/rest/o/feed/liked",
                "/rest/o/feed/profile",
                "/rest/o/tag/feedV2",
                "/rest/o/music/agg/photo",
                "/rest/o/photo/get",
                "/rest/o/config/coldStart",
                "/rest/o/config/hotStart",
                "/rest/o/log/web/collect",
                "/rest/o/share/shorten",
                "/rest/o/geo/search",
                "/rest/o/user/search",
                "/rest/o/tag/search",
                "/rest/o/music/search",
                "/rest/o/search/all",
                "/rest/o/photo/search",
                "/rest/o/search/top/music",
                "/rest/o/w/photo/get",
                "/rest/o/w/v0/user/profile",
                "/rest/o/w/v0/feed/profile",
                "/rest/o/w/comment/list",
                "/rest/o/w/v0/feed/liked",
                "/rest/o/feed/detail/recommend",
                "/rest/o/user/getInfo",
                "/rest/o/photo/like",
                "/rest/o/push/get"};


        Set<String> result = Arrays.stream(t2).map(
                t -> t.replace("bulldog", "o")).collect(Collectors.toSet());
        result.forEach(
                r -> System.out.println("\"" + r + "\",")
        );
    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        int left = 0, right = 0;
        while (right < prices.length) {
            if (prices[right] > prices[left]) {
                profit += prices[right] - prices[left];
            }
            left = right;
            right++;
        }
        return profit;
    }

    @Test
    public void testMaxProfit() {
        System.out.println(maxProfit(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    public void testString() {
        double t = 3000d;
        String template = "进度仅差%.2f%%，立即提现%d元哦";
        System.out.println(String.format(template, (1 - 2999.0 / 3000.0) * 100.0, (int) t / 100));
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void testNameOfRunnable() {
        executorService.submit(this::testName);
    }

    public void withException() {
        try {
            testName();
        } catch (Exception e) {
            LOGGER.info(Thread.currentThread()
                    .getStackTrace()[1]
                    .getMethodName());
        }
    }

    public void testName() {
        LOGGER.info("normal test");
        throw new IllegalArgumentException();
    }
}
