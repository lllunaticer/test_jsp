package TimeTest;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-09-05
 */
public class TimeTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneOffset SYSTEM_OFFSET = OffsetDateTime.now().getOffset();


    @Test
    public void testTime() {
        String zoneIdStr = "America/Sao_Paulo";
        LocalDateTime endOfTodayAtBr = LocalDate.now(ZoneId.of(zoneIdStr)).atTime(LocalTime.MAX);
        LocalDateTime nowAtBr = LocalDateTime.now(ZoneId.of(zoneIdStr));
        System.out.println(nowAtBr);

        LocalDate braNowDate = LocalDate.now(ZoneId.of(zoneIdStr));

        System.out.println("巴西下个周日的0点" + braNowDate.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX)
                .atZone(ZoneId.of(zoneIdStr)));

        System.out.println(
                "巴西本月最后一天的0点" + braNowDate.with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX)
                        .atZone(ZoneId.of(zoneIdStr)));

        long timestampMillsNowBra = nowAtBr.atZone(ZoneId.of(zoneIdStr)).toInstant().toEpochMilli();
        long timestampMillsEndDaybra = endOfTodayAtBr.atZone(ZoneId.of(zoneIdStr)).toInstant().toEpochMilli();
        System.out.println("巴西当前时间到24点的毫秒差: " + (timestampMillsEndDaybra - timestampMillsNowBra));

        long currentTimeStamp = System.currentTimeMillis();
        LocalDateTime nowAtBr2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeStamp),
                ZoneId.of(zoneIdStr));
        System.out.println(nowAtBr2);
        System.out.println(endOfTodayAtBr);


        LocalDateTime nowSh = LocalDateTime.now();
        System.out.println(ZoneId.systemDefault() + "当前时间" + nowSh);
        LocalDate nowDateSh = LocalDate.now();
        LocalDateTime endOfTodaySh = nowDateSh.atTime(LocalTime.MAX);
        System.out.println(ZoneId.systemDefault() + "当地时间24点" + endOfTodaySh);
        long timestampMillsNowSh = nowSh.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(
                ZoneId.systemDefault() + " 时区当前时间到24点的毫秒时间差: " + (endOfTodaySh
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - timestampMillsNowSh));


        System.out.println("current time 与巴西current time之差: " + (currentTimeStamp - timestampMillsNowBra));
        System.out.println("current time 与当前current time之差: " + (currentTimeStamp - timestampMillsNowSh));

        System.out.println("当前时区日期" + nowSh);
        System.out.println("当前时区下个周日的0点" + nowDateSh.with(DayOfWeek.SUNDAY).atTime(LocalTime.MIDNIGHT)
                .atZone(ZoneId.systemDefault()));

        System.out.println(
                "当前时区本月最后一天的0点" + nowDateSh.with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MIDNIGHT)
                        .atZone(ZoneId.systemDefault()));

    }
}
