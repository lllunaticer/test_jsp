package FutureTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-02-23
 */
public class SquareCalculator {
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public Future<Integer> calculate(Integer input) {
        return executorService.submit(() -> {
            Thread.sleep(2000);
            return input * input;
        });
    }
}
