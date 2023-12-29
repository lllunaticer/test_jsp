import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2020-12-31
 */
public class GuavaTest {
    private static Integer notNullMethod(@NotNull Integer integer) {
        return integer;
    }

    private static int BATCH_LIMIT_COUNT = 200;
    private static final int MAX_PAGE_SIZE = 500;

    public static void main(String[] args) {
        List<Long> photoIds = Collections.singletonList(150001065323431L);
        UnmodifiableIterator<List<Long>> partition = Iterators.partition(photoIds.iterator(), MAX_PAGE_SIZE);

        //                .forEachRemaining(
//                GuavaTest::partition
//        );
    }

    private static void partition(Collection<Long> list) {
        List<List<Long>> partition = Lists.partition(new ArrayList<>(list), BATCH_LIMIT_COUNT);
        partition.forEach(
                System.out::println
        );
    }
}
