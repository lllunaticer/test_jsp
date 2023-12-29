package FutureTest;

import java.util.concurrent.RecursiveTask;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-02-23
 */
public class FactorialSquareCalculator extends RecursiveTask<Integer> {

    private Integer n;

    public FactorialSquareCalculator(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        FactorialSquareCalculator calculator
                = new FactorialSquareCalculator(n - 1);

        calculator.fork();
        System.out.println("n is: " + n);

        return n * n + calculator.join();
    }
}
