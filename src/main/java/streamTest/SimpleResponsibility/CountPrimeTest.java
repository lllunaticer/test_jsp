package streamTest.SimpleResponsibility;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

/**
 * @author Xingjian LONG
 * Created on 2021-08-29
 */
public class CountPrimeTest {
    //计算质数个数, 普通方式
    public long countPrimes(int upTo) {
        long tally = 0;
        for (int i = 1; i < upTo; i++) {
            if (isPrime(i)) {
                tally++;
            }
        }
        return tally;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    //计算质数个数，lambda表达式
    public long countPrimesLambda(int upTo) {
        return IntStream.range(1, upTo)
                .filter(this::isPrimeLambda)
                .count();
    }

    private boolean isPrimeLambda(int number) {
        return IntStream.range(2, number)
                .allMatch(x -> (number % x) != 0);
    }

    //使用lambda表达式可以很方便的将程序改为并行
    public long countPrimesLambdaParallel(int upTo) {
        return IntStream.range(1, upTo)
                .parallel()
                .filter(this::isPrimeLambda)
                .count();
    }

    @Test
    public void testPrime() {
        //        assertEquals(countPrimes(10), countPrimesLambda(10), countPrimesLambdaParallel(10));
        int testValue = 500000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        countPrimesLambda(testValue);
        stopWatch.stop();
        System.out.println("非并行计算时间:" + stopWatch.getTime(TimeUnit.SECONDS) + " 秒");

        stopWatch.reset();
        stopWatch.start();
        countPrimesLambdaParallel(testValue);
        stopWatch.stop();
        System.out.println("并行计算时间:" + stopWatch.getTime(TimeUnit.SECONDS) + " 秒");
    }
}
