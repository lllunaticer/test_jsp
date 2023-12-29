package FutureTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import org.junit.Test;


/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-02-23
 */
public class FutureTest {
    @Test
    public void testFuture() throws InterruptedException, ExecutionException {
        Future<Integer> calculate = new SquareCalculator().calculate(10);
        while (!calculate.isDone()) {
            System.out.println("Calculating ... ");
            Thread.sleep(300);
        }
        System.out.println("get calculation result: " + calculate.get());
    }

    @Test
    public void testMultiFuture() throws Exception {
        SquareCalculator squareCalculator = new SquareCalculator();

        Future<Integer> future1 = squareCalculator.calculate(10);
        Future<Integer> future2 = squareCalculator.calculate(100);

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println(result1 + " and " + result2);
    }

    @Test
    public void testForkJoinTask() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialSquareCalculator factorialSquareCalculator = new FactorialSquareCalculator(10);
        forkJoinPool.execute(factorialSquareCalculator);
    }

    @Test
    public void commonFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> submit = executorService.submit(() -> "get result !");
        System.out.println(submit.get());
    }
}
