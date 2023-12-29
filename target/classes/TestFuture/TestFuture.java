package TestFuture;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.util.StopWatch;

import com.github.phantomthief.concurrent.MoreFutures;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Uninterruptibles;
import com.kuaishou.framework.concurrent.DynamicThreadExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-07-17
 */
@Slf4j
public class TestFuture {
    private static final ExecutorService executor =
            DynamicThreadExecutor.dynamic(() -> 2, "main-executor-----%d");

//    private static final ExecutorService MEMBER_PAGE_EXECUTOR =
    //            new ThreadPoolExecutor(1, 1, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
    //                    new RejectedExecutionHandler() {
    //                        @Override
    //                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    //                            log.info("rejectedExecution wm");
    //                            throw new RejectedExecutionException();
    //                        }
    //                    }
    //            );

        private static final ExecutorService MEMBER_PAGE_EXECUTOR =
                DynamicThreadExecutor.dynamic(() -> 1, "member-page-executor-%d");


    @Test
    public void test02() throws ExecutionException, InterruptedException {
        boolean is = null instanceof Integer;
        System.out.println(is);
//        testTmp();
    }

    public void testTmp() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000L);
                log.info("future1 sleep over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("步骤一  " + Thread.currentThread().getName());
        }, MEMBER_PAGE_EXECUTOR);

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000L);
                log.info("future2 sleep over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("步骤二  " + Thread.currentThread().getName());
        }, MEMBER_PAGE_EXECUTOR);
        //        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
        //            try {
        //                Thread.sleep(1000L);
        //                log.info("future3 sleep over");
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            log.info("步骤三  " + Thread.currentThread().getName());
        //        }, MEMBER_PAGE_EXECUTOR);

        CompletableFuture<Void> future4 = future1.thenRunAsync(() -> {
            try {
                Thread.sleep(1000L);
                log.info("future4 sleep over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("步骤三  " + Thread.currentThread().getName());
        }, MEMBER_PAGE_EXECUTOR);
        log.info("begin");

        try {
            CompletableFuture.allOf(future1, future2, future4).join();
        } catch (Exception e) {
            log.info("exception", e);
        }
        log.info("end");
    }

    @Test
    public void testRunAsync() {
        CompletableFuture.runAsync(this::printAndSleep2, executor);
        log.info("thread name: " + Thread.currentThread().getName());
    }

    @Test
    public void testFutureAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(this::printAndSleep2)
                .thenRun(() -> CompletableFuture.allOf(CompletableFuture.runAsync(() -> printAndSleep(4), executor),
                        CompletableFuture.runAsync(() -> printAndSleep(1), executor)).join()).join();
    }

    @Test
    public void testListenableFuture() {
        ExecutorService execService = Executors.newFixedThreadPool(20);
        ListeningExecutorService lExecService = MoreExecutors.listeningDecorator(execService);
        Collection<ListenableFuture<Integer>> futures = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            ListenableFuture<Integer> integerFuture = lExecService.submit(() -> printAndSleep(finalJ));
            futures.add(integerFuture);
        }
        int result = MoreFutures.tryWait(futures, Duration.ofSeconds(5)).values()
                .stream()
                .reduce(0, Integer::sum);
        stopWatch.stop();
        log.info("final result is " + result + "\n takes " + stopWatch.getTotalTimeSeconds()
                + " seconds to complete task");
    }

    @Test
    public void testListenableFutureWithAllOf() {
        ExecutorService execService = Executors.newFixedThreadPool(20);
        ListeningExecutorService lExecService = MoreExecutors.listeningDecorator(execService);
        Collection<ListenableFuture<Integer>> futures = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            ListenableFuture<Integer> integerFuture = lExecService.submit(() -> printAndSleep(finalJ));
            futures.add(integerFuture);
        }
        ListenableFuture<List<Integer>> result = Futures.successfulAsList(futures);
        Integer sum = MoreFutures.getUnchecked(result, Duration.ofSeconds(5))
                .stream()
                .filter(Objects::nonNull)
                .reduce(0, Integer::sum);
        stopWatch.stop();
        log.info("final result is " + sum + "\n takes " + stopWatch.getTotalTimeSeconds()
                + " seconds to complete task");
    }

    private Integer printAndSleep(int start) {
        if (start == 4) {
            throw new RuntimeException();
        }
        for (int i = 0; i < 5; i++) {
            start += i;
            log.info("progress tid: " + Thread.currentThread().getName() + " i = " + i + " start = " + start);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        return start;
    }

    private void printAndSleep2() {
        for (int i = 0; i < 5; i++) {
            log.info("progress 2 tid: " + Thread.currentThread().getName());
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
    }
}
