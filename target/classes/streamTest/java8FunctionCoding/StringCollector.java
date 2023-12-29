package streamTest.java8FunctionCoding;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Xingjian LONG
 * Created on 2021-08-26
 */
public class StringCollector implements Collector<String, StringCombiner, String> {
    private String prefix;
    private String suffix;
    private String delim;

    public StringCollector(String prefix, String suffix, String delim) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
    }

    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(prefix, suffix, delim);
    }

    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    //combiner 方法只有在使用并行流的时候才会使用，作用是把并行处理的流的结果合并起来
    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
