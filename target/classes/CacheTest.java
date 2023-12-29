import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-27
 */
public class CacheTest {
    private  final LoadingCache<String, String> stringCache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    System.out.println("xx");
                    if (s.equals("hello"))
                        return "world";
                    throw new RuntimeException();
                }
            });

    public static void main(String[] args) throws ExecutionException {
        CacheTest cacheTest = new CacheTest();
        System.out.println(cacheTest.getString("hell"));
    }

    public String getString(String s) throws ExecutionException {
        return stringCache.get(s);
    }
}
