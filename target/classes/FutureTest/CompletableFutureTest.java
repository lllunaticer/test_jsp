package FutureTest;

import static java.lang.Thread.sleep;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import com.google.common.util.concurrent.Uninterruptibles;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Xingjian LONG
 * Created on 2021-06-16
 * refs: https://kstack.corp.kuaishou.com/tech/web/article/info/3149?userSource=engineer-group-bot
 */
@Slf4j
public class CompletableFutureTest {
    @Test
    public void test1() throws Exception {
        CompletableFuture<String> stringCompletableFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "build ";
                }).thenApply(s -> s + "your ").thenApply(s -> s + "dream");
        System.out.println(stringCompletableFuture.get());

        CompletableFuture.supplyAsync(() -> "开启新的任务").thenRun(() -> {
            //串行后需操作，无参数也无返回值
            System.out.println("输出结果");
        });

        /*
         * thenAcceptBothAsync将获取到的两个ComplatableFuture的结果合并一起进行下一步的操作，此方法没有返回值
         * */
        // 输出：你好,我是谁
        CompletableFuture.supplyAsync(() -> "你好,")
                .thenAcceptBothAsync(CompletableFuture.supplyAsync(() -> "我是谁"), (firstTask, secondTask) -> {
                    System.out.println(firstTask + secondTask);
                });

        /*
        * CompletableFuture聚合 And 关系的方法
        * 聚合 And 关系是指：任务C要等待任务A或任务B都执行完后才执行。CompleteFeature支持And聚合的方法有：
        * public <U> CompletableFuture<Void> thenAcceptBoth( CompletionStage<? extends U> other,BiConsumer<? super T,
        *  ? super U> action)
        public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super
        * T, ? super U> action)
        public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super
        * T, ? super U> action, Executor executor)
        public CompletableFuture<Void> runAfterBoth(CompletionStage<?> other,Runnable action)
        public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action)
        public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor)
        public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super
        *  U,? extends V> fn)
        public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,?
        *  super U,? extends V> fn)
        public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,?
        * super U,? extends V> fn, Executor executor)
        * */

        // 输出：老铁,欢迎来到快手
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "老铁,")
                .thenCombine(CompletableFuture.supplyAsync(() -> "欢迎来到快手"), (a, b) -> a + b);
        System.out.println(completableFuture.get());

        /*
        * CompletableFuture聚合 Or 关系
            聚合Or关系是指：任务C等待任务A或任务B其中一个执行完后就执行，即C只需等待最先执行完成的任务后就可执行。CompleteFeature支持此关系的方法如下：
            public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)
        public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U>
        *  fn)
        public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U>
        *  fn, Executor executor)
        public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
        public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
        public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T>
        * action,Executor executor)
        public CompletableFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action)
        public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action)
        public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action, Executor executor)
        * */

        //输出：你好,老铁
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "你好,老铁")
                .applyToEither(CompletableFuture.supplyAsync(() -> "快手"), (returnVal) -> returnVal);
        System.out.println(completableFuture2.get());

        // 输出：快手
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "你好,老铁";
        }).applyToEither(CompletableFuture.supplyAsync(() -> "快手"), (returnVal) -> returnVal);
        System.out.println(completableFuture3.get());


            /*
    * CompletableFuture异常处理
    * public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
        public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
        public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action)
        public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor
        * executor)
        public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)
        public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn)
        public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor)
        * 这个异常处理看着还挺多的，但用传统的 try/catch/finally 做个对比也就瞬间懂了
        * exceptionally --> try/catch
        * whenCompleteAsync & handle --> try/finally
        *
        * exceptionally异常示例：exceptionally 就相当于 catch，出现异常，将会跳过 thenApply 的后续操作，直接捕获异常，进行异常处理
    * */

        // 输出：异常处理
        CompletableFuture<String> completableFuture4 =
                CompletableFuture.completedFuture(true).thenApply(booleanValue -> {
                    if (booleanValue) {
                        throw new RuntimeException("抛出异常");
                    }
                    return "正常结果值";
                }).thenApply(s -> "执行then apply方法").exceptionally(throwable -> "异常处理");
        System.out.println(completableFuture4.get());



        /*
         * handle相当于 try/finally, handle 就可以起到 finally 的作用, 对上述代码进行更改, handle 接受两个参数，一个是正常返回值，一个是异常
         * */
        // 输出：执行then apply方法
        CompletableFuture<String> completableFuture5 =
                CompletableFuture.completedFuture(false).thenApply(booleanValue -> {
                    if (booleanValue) {
                        throw new RuntimeException("抛出异常");
                    }
                    return "正常结果值";
                }).thenApply(s -> "执行then apply方法").handle((result, exception) -> {
                    if (exception != null) {
                        return "异常处理";
                    }
                    return result;
                });
        System.out.println(completableFuture5.get());

    }

    @Test
    public void completeFuture() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> name = CompletableFuture.supplyAsync(() -> (String) getValue("name"));
        CompletableFuture<Integer> age = CompletableFuture.supplyAsync(() -> (int) getValue("age"));
        CompletableFuture<Long> tel = CompletableFuture.supplyAsync(() -> (long) getValue("tel"));
        CompletableFuture<String> unknown = CompletableFuture.supplyAsync(() -> (String) getValue("xx"));
        StopWatch stopWatch = StopWatch.createStarted();
        CompletableFuture.allOf(name, age, tel, unknown).get(20, TimeUnit.SECONDS);
        if (name.isDone() && age.isDone() && tel.isDone() && unknown.isDone()) {
            long time = stopWatch.getTime(TimeUnit.SECONDS);
            log.info("take {} seconds to get name {} age {} tel {} unknown {}", time,
                    name.get(), age.get(), tel.get(), unknown.get());
        }
    }

    private Object getValue(String type) {
        switch (type) {
            case "name":
                Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
                return "long";
            case "age":
                Uninterruptibles.sleepUninterruptibly(11, TimeUnit.SECONDS);
                return 28;
            case "tel":
                Uninterruptibles.sleepUninterruptibly(12, TimeUnit.SECONDS);
                return 18222668382L;
            default:
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                return "unknown";
        }
    }
}
