package RedisTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author Xingjian LONG
 * Created on 2021-06-18
 */
public class RedisTest {
    private Jedis jedis;

    @Before
    public void setUp() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    @Test
    public void testJedis() {
        jedis.set("name", "longxingjian");
        System.out.println(jedis.get("name"));
        String set = jedis.set("test", "1");
        System.out.println(set);
        System.out.println(jedis.get("test"));
    }

    @Test
    public void testJedisPipeline() {
        try {

            Pipeline pipeline = jedis.pipelined();
            for (int offset = 0; offset < 10000000; offset++) {
                pipeline.setbit("test", offset, true);
            }
            pipeline.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Test
    public void test2() {
        jedis.setbit("test1", 10000000, true);
    }

    @Test
    public void testInputStream() throws IOException {
        String ss = "data";
        InputStream inputStream = IOUtils.toInputStream(ss, StandardCharsets.UTF_8);
        String rawData;
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8);
            rawData = writer.toString();
        } catch (Exception e) {
            rawData = "read raw data error";
        }
        System.out.println("1:" + rawData);

        inputStream.reset();

        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8);
            rawData = writer.toString();
        } catch (Exception e) {
            rawData = "read raw data error";
        }
        System.out.println("2:" + rawData);
    }

    @Test
    public void testAssert() {
        String key = "test";
        jedis.del(key);
        String test = jedis.get(key);
        if (!StringUtils.isEmpty(test)) {
            System.out.println("test not null");
            return;
        }
        assert "OK".equalsIgnoreCase(jedis.set(key, "test set"));
        System.out.println("test value is after assert: " + jedis.get(key));

        jedis.set(key, "test now set");
        System.out.println("test value is after set: " + jedis.get(key));
    }
}
