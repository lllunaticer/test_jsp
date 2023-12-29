package JsonMapperTest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-14
 */
public class testMapper {
    @Test
    public void testJson() {
        //        String json = "{\n"
        //                + "    \"userName\": \"longxingjian\",\n"
        //                + "    \"musicId\":123456\n"
        //                + "}";
        //
        //        MusicModel musicModel = ObjectMapperUtils.fromJSON(json, MusicModel.class);
        //        System.out.println(musicModel.getMusicId());
        //        System.out.println(musicModel.getUserId());
        //        System.out.println(DigestUtils.md2Hex("longxingjian").substring(0,10));

        String ss = "external_userIdentity";
        String value = "good";
        Map<String, Object> result = new HashMap<>();
        result.put(ss, value);
        Map<String, Object> external_ = result.keySet().stream()
                .filter(k -> k.startsWith("aexternal_"))
                .collect(Collectors.toMap(k -> k.substring(9), result::get, (a, b) -> a));
        System.out.println(ss.substring(9));
        System.out.println(external_);
    }
}
