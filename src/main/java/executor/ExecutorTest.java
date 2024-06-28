package executor;

import java.util.concurrent.ExecutorService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.kuaishou.framework.concurrent.ExecutorsEx;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            log.info("num [{}]", i);
            addExecutor(i);
        }
        int k = 0;
    }

    private static void addExecutor(int i) {
        ExecutorService executorService = ExecutorsEx.newFixedThreadPool(1, new ThreadFactoryBuilder()
                .build());
        executorService.submit(() -> {
            log.info("hello {} from {}", i, Thread.currentThread().getName());});
    }
}
