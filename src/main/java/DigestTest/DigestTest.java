package DigestTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.apache.commons.codec.binary.Hex;

import TestGuava.People;


/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2024-02-21
 */
public class DigestTest {
    @Test
    public void disgestTest() {
        People people = new People(12, "longxingjian");
        System.out.println(digestObjects(people));
        people.setAge(13);
        System.out.println(digestObjects(people));
        people.setName("llongxingjian");
        System.out.println(digestObjects(people, people));

        System.out.println(digestObjects(people,  System.currentTimeMillis()));
        System.out.println(digestObjects(people,  System.currentTimeMillis()));
    }

    public String digestObjects(Serializable... objects) {
        byte[] allBytes = objectsToByteArray(objects);
        return DigestUtils.md5Hex(allBytes);
    }

    public byte[] objectsToByteArray(Serializable... objects) {
        int size = 0;
        List<byte[]> bytesList = new ArrayList<>();
        for (Serializable object : objects) {
            byte[] bytes = SerializationUtils.serialize(object);
            bytesList.add(bytes);
            size += bytes.length;
        }
        byte[] allBytes = new byte[size];
        int pos = 0;
        for (byte[] bin : bytesList) {
            int length = bin.length;
            System.arraycopy(bin, 0, allBytes, pos, length);
            pos += length;
        }
        return allBytes;
    }
}
