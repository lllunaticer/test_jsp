package netty.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-10-12
 */
/*
 * ObjectOutputStream 提供的序列化方式序列化的结果很大
 * 同样的对象java ObjectOutputStream 序列化 耗时比较大 9s, ByteBuffer 耗时比较小 1s
 * */
public class TestPerfomanceUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        int loop = 10000000;
        info.setUserName("welcome to netty");
        info.setUserId(100L);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < loop; i++) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(info);
            os.flush();
            os.close();
            byte[] b = bos.toByteArray();
            bos.close();
        }
        stopWatch.stop();
        System.out.println("cost " + stopWatch.getTime(TimeUnit.SECONDS) + " seconds to serialized");
        stopWatch.reset();
        stopWatch.start();
        for (int i = 0; i < loop; i++) {
            byte[] bytes = info.codeC();
        }
        stopWatch.stop();
        System.out.println("cost " + stopWatch.getTime(TimeUnit.SECONDS) + " seconds to codeC");
    }
}
