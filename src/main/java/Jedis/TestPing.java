package Jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-09-04
 */
@Slf4j
public class TestPing {
    public static void main(String[] args) {
        //1、new Jedis对象即可
        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
            System.out.println(jedis.ping());
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
