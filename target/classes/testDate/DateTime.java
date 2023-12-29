package testDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.kuaishou.ip.constant.Country;
import com.snack.explore.model.ExploreLocale;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-08-17
 */
public class DateTime {

    private static final Map<ExploreLocale, ZoneId> map =
            ImmutableMap.<ExploreLocale, ZoneId> builder()
                    .put(ExploreLocale.pak, ZoneId.of("Asia/Karachi"))
                    .put(ExploreLocale.br, ZoneId.of("America/Sao_Paulo"))
                    .put(ExploreLocale.egy, ZoneId.of("Africa/Cairo"))
                    .put(ExploreLocale.spa, ZoneId.of("Europe/Madrid"))
                    .put(ExploreLocale.in, ZoneId.of("Asia/Jakarta"))
                    .build();

    private static final DateTimeFormatter ftf = DateTimeFormatter.ofPattern("hh");

    public static int getLocalHour(long time, ExploreLocale bucket) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), map.get(bucket));
        Map<String, String> map = new HashMap<>();
        Map<String, Long> collect = map.entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, e -> Long.parseLong(e.getValue())));

        return localDateTime.getHour();
    }

    @Test
    public void testTime() {
        long timestamp = System.currentTimeMillis();
        map.forEach(
                (k, v) -> System.out.printf("bucket[%s]'s current hour is %d%n", k, getLocalHour(timestamp, k))
        );
    }
}
