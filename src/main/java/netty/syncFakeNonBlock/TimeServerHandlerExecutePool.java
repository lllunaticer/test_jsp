package netty.syncFakeNonBlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-09-29
 */
public class TimeServerHandlerExecutePool {
    private final ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        executor =
                new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable task) {
        executor.submit(task);
    }
}
