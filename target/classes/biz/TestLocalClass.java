package biz;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.junit.Test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.common.util.concurrent.Uninterruptibles;

import io.grpc.netty.shaded.io.netty.util.concurrent.Future;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-24
 */
public class TestLocalClass {
    private static long timestamp = 0;

    private static long thisMonthStartTimestamp = 0;

    private static long nextMonthStartTimestamp = 0;

    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1,
            new ThreadFactoryBuilder()
                    .setNameFormat("questionnaire-refresh-time")
                    .setDaemon(false)
                    .build()
    );

    @PostConstruct
    public void init() {
        EXECUTOR_SERVICE.scheduleWithFixedDelay(this::refreshTime, 0, 1, TimeUnit.SECONDS);
    }

    private void refreshTime() {
        timestamp = System.currentTimeMillis();
    }

    public void lookTime() {
        int i = 20;
        while (i > 0) {
            System.out.println("this time is " + timestamp);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            i--;
        }
    }

    private synchronized void refreshTimes() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0); //获取当前月第一天
        c.set(Calendar.DAY_OF_MONTH, 1); //设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0); //将小时至0
        c.set(Calendar.MINUTE, 0); //将分钟至0
        c.set(Calendar.SECOND, 0); //将秒至0
        c.set(Calendar.MILLISECOND, 0); //将毫秒至0
        thisMonthStartTimestamp = c.getTimeInMillis();
        c.add(Calendar.MONTH, 1); //日期调整到下个月
        nextMonthStartTimestamp = c.getTimeInMillis();
    }

    @Test
    public void testRefreshTimes() throws ExecutionException, InterruptedException {
        refreshTimes();
        System.out.println("timestamp:" + thisMonthStartTimestamp + " time" + new Date(thisMonthStartTimestamp));
        System.out.println("timestamp:" + nextMonthStartTimestamp + " time" + new Date(nextMonthStartTimestamp));

        refreshTimes();
        System.out.println("timestamp:" + thisMonthStartTimestamp + " time" + new Date(thisMonthStartTimestamp));
        System.out.println("timestamp:" + nextMonthStartTimestamp + " time" + new Date(nextMonthStartTimestamp));

        System.out.println((nextMonthStartTimestamp - System.currentTimeMillis()) / (1000 * 60 * 60));
        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> 1);
        Integer integer = integerFutureTask.get();
    }
}
