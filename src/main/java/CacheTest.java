import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kuaishou.framework.concurrent.DynamicThreadExecutor;
import com.kuaishou.framework.util.ObjectMapperUtils;
import com.kuaishou.infra.framework.common.util.KsCacheBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-27
 */
@Slf4j
public class CacheTest {
    private static final ExecutorService executorService = DynamicThreadExecutor.dynamic(() -> 10, "dd-", false);

    private static final List<Long> keys = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L);

    private final LoadingCache<Long, Optional<String>> localCache = KsCacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(() -> Duration.ofSeconds(60L))
            .expireAfterAccess(() -> Duration.ofSeconds(60L))
            .cacheName("cache")
            .enablePerf("cache")
            .enableAutoCleanup()
            .concurrencyLevel(1)
            .initialCapacity(100)
            .build(new CacheLoader<Long, Optional<String>>() {
                @Override
                public Optional<String> load(@Nonnull Long batchId) throws InterruptedException {
                    log.info("load batchId:{}, thread {}", batchId, Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(2L);
                    return Optional.ofNullable(
                            getCouponBatchByIdsFromRedis(Collections.singletonList(batchId)).get(batchId));
                }

                @Override
                public Map<Long, Optional<String>> loadAll(@Nonnull Iterable<? extends Long> batchIds) {
                    log.info("batch load batchId:{}, thread {}", batchIds, Thread.currentThread().getName());
                    Map<Long, String> result = getCouponBatchByIdsFromRedis(Sets.newHashSet(batchIds));
                    Map<Long, Optional<String>> resultMap =
                            Maps.newHashMapWithExpectedSize(CollectionUtils.size(batchIds));
                    for (Long batchId : batchIds) {
                        resultMap.put(batchId, Optional.ofNullable(result.get(batchId)));
                    }
                    return resultMap;
                }

                private Map<Long, String> getCouponBatchByIdsFromRedis(Collection<Long> batchIds) {
                    log.info("getCouponBatchByIdsFromRedis batchIds:{}", batchIds);
                    List<String> couponBatchDTOs;
                    // catch了异常，异常情况下缓存空值，防止反复击穿
                    try {
                        List<String> res = new ArrayList<>();
                        Iterables.partition(batchIds, 10)
                                .forEach(bIds -> {
                                    res.addAll(new ArrayList<>(
                                            bIds.stream().map(Object::toString).collect(Collectors.toList())));
                                    try {
                                        TimeUnit.SECONDS.sleep(1L);
                                    } catch (InterruptedException e) {
                                        log.error("error", e);
                                    }
                                });
                        couponBatchDTOs = res;
                    } catch (Exception e) {
                        couponBatchDTOs = Collections.emptyList();
                    }
                    return couponBatchDTOs.stream()
                            .collect(Collectors.toMap(Long::parseLong, Function.identity(), (x, y) -> x));
                }
            });


    public void testGetAll() {
        log.info("getAll");
        executorService.submit(this::doWorkGetAll);
        executorService.submit(this::doWorkGetAll);
    }

    private void doWorkGetAll() {
        try {
            ImmutableMap<Long, Optional<String>> all =
                    localCache.getAll(keys);
            log.info("doWorkGetAll: {}, thread {}",
                    ObjectMapperUtils.toJSON(all.values().stream().map(Optional::get).collect(
                            Collectors.toList())), Thread.currentThread().getName());
        } catch (ExecutionException e) {
            log.error("error", e);
        }
    }

    public void testGet() {
        log.info("get");
        executorService.submit(this::doWorkGet);
        executorService.submit(this::doWorkGet);
    }

    private void doWorkGet() {
        try {
            List<String> res = new ArrayList<>();
            for (Long batchId : keys) {
                res.add(localCache.get(batchId).get());
            }
            log.info("doWorkGet: {}, thread {}", res, Thread.currentThread().getName());
        } catch (ExecutionException e) {
            log.error("error", e);
        }
    }

    public void mainDoWork() {
        doWorkGet();
    }

    public static void main(String[] args) {
        CacheTest cacheTest = new CacheTest();
        //        cacheTest.testGet();
        cacheTest.testGetAll();
        //        cacheTest.mainDoWork();
    }
}
