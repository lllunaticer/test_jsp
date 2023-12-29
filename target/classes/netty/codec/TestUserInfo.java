package netty.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-10-12
 */
/*
* ObjectOutputStream 提供的序列化方式序列化的结果很大
* 同样的对象java ObjectOutputStream 序列化的结果比较大， 127；
* 而ByteBuffer 序列化的结果小的多，25
* */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.setUserName("welcome to netty");
        info.setUserId(100L);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("the native java serializer length is : " + b.length);
        bos.close();
        System.out.println("the byte array serializer length is : " + info.codeC().length);
    }
}
