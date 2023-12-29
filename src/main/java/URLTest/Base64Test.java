package URLTest;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.kuaishou.keycenter.security.common.Nonce;

/**
 * @author Xingjian LONG
 * Created on 2021-05-27
 */
public class Base64Test {
    @Test
    public void base64Test() {
        String sig3 = new String("12345678my name is longxingjian".getBytes(), StandardCharsets.UTF_8);
        byte[] bytes = Base64.decodeBase64(sig3);
        Nonce nonce = Nonce.of(ArrayUtils.subarray(bytes, 0, Nonce.BYTE_SIZE));
        String sign = new String(ArrayUtils.subarray(bytes, Nonce.BYTE_SIZE, bytes.length),
                StandardCharsets.UTF_8);
        System.out.println(nonce.toString().trim());
        System.out.println(sign);
    }
}
