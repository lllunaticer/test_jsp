package FutureTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-02-23
 */
public class ListenableFutureCalculator {

    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public ListenableFuture<Integer> calculate(int input) {
        return executorService.submit(() -> {
            System.out.println("call execute");
            Thread.sleep(3000);
            return input * input;
        });
    }

    @Test
    public void testListenableFuture() throws InterruptedException {
        List<Integer> arrays = Arrays.asList(3, 4, 5, 6, 7);
        List<ListenableFuture<Integer>> listenableFutures = arrays.stream()
                .map(e -> new ListenableFutureCalculator().calculate(e))
                .collect(Collectors.toList());
        ListenableFuture<List<Integer>> listListenableFuture = Futures.successfulAsList(listenableFutures);

        //        ListenableFuture<Integer> calculate = new ListenableFutureCalculator().calculate(10);

        Futures.addCallback(listListenableFuture, new FutureCallback<List<Integer>>() {
            @Override
            public void onSuccess(@Nullable List<Integer> result) {
                assert result != null;
                result.forEach(e -> System.out.println("get result: " + e));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }, Executors.newSingleThreadExecutor());

        long time = 0;
        while (time <= 5000) {
            System.out.println("do business logic ... ");
            time += 1000;
            Thread.sleep(1000);
        }
    }
}
