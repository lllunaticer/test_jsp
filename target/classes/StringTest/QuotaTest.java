package StringTest;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.collect.MapBuilder;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.kuaishou.encryptid.util.EncryptedIdType;
import com.kuaishou.encryptid.util.IdEncryptor;
import com.kuaishou.encryption.util.IdEncryptorUtils;
import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-11
 */
public class QuotaTest {
    public static void main(String[] args) {
        String test = "\"\"[good]\"\"\"";
        System.out.println(removeSurroundQuta(test));
    }

    private static String removeSurroundQuta(String numListStr) {
        if (!numListStr.startsWith("\"") || !numListStr.endsWith("\"")) {
            return numListStr;
        }
        numListStr = numListStr.substring(1, numListStr.length() - 1);
        return removeSurroundQuta(numListStr);
    }

    @Test
    public void testFormat() {
        String format = String.format("%,d", Long.MAX_VALUE);
        System.out.println(format);
        System.out.println(NumberFormat.getInstance(Locale.forLanguageTag("in")).format(Long.MAX_VALUE));
        System.out.println(NumberFormat.getInstance(Locale.JAPANESE).format(Long.MAX_VALUE));
        System.out.println(NumberFormat.getInstance(Locale.CHINA).format(Long.MAX_VALUE));
    }

    @Test
    public void testThreadLocalRandom() {
        System.out.println(ThreadLocalRandom.current().nextInt(4));
        System.out.println(ThreadLocalRandom.current().nextInt(4));
        System.out.println(ThreadLocalRandom.current().nextInt(4));
    }

    @Test
    public void testCityName() {
        String city = "Hong Kong";
        System.out.println(city.equalsIgnoreCase("hong kong"));
    }

    @Test
    public void generateUniStr() {
        List<Long> uids = Arrays.asList(
                150001011747454L
        );
        Map<Long, String> result = uids.stream()
                .collect(Collectors.toMap(Function.identity(),
                        uid -> String.valueOf(IdEncryptor.encrypt(uid, EncryptedIdType.USER_ID))));
        System.out.println(result);
    }

    @Test
    public void generateLink() {

        Map<String, Long> map = new HashMap<>();
        map.put("3xetajc44978m7k", 783527333L);

        String template =
                "https://m.snackvideo.com/one/incentive/in/invite-share?inviteCode=%s&uniqueStr=%s&source=copy_link"
                        + "&langCode=en&reshare=1";

        Map<String, String> result = map.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Entry::getKey,
                                e -> String.format(template, formatInviteCode(e.getValue()), e.getKey())
                        )
                );
        result.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private static String formatInviteCode(long inviteCode) {
        String emptyHolder = "%2520";
        char[] chars = String.valueOf(inviteCode).toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i == 2 || i == 5) {
                stringBuilder.append(emptyHolder);
            }
            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }

    @Test
    public void testPartition() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Lists.partition(list, list.size())
                .forEach(
                        System.out::println
                );
    }

    @Test
    public void testDeepCopy() {
        List<Integer> result = new ArrayList<>();

        System.out.println(result.contains(null));


        List<Integer> kk = Arrays.asList(1, 2);
        result.addAll(kk);

        kk = Arrays.asList(3, 4);
        result.addAll(kk);

        System.out.println(result);

        Map<String, String> map = MapBuilder.<String, String> newMapBuilder()
                .put("ss", "ss")
                .map();

        System.out.println(map.get(null));
    }

    @Test
    public void decodePid() {
        System.out.println(IdEncryptorUtils.decryptLongPhotoId(5202652054886746079L
        ));
    }

    @Test
    public void testMap() {
        Map<String,Integer> map = new HashMap<>();
        map.put("cashAmount", Integer.MAX_VALUE);
        String json = ObjectMapperUtils.toJSON(map);
        System.out.println(json);
        Map<String, Object> stringObjectMap = ObjectMapperUtils.fromJson(json);
        Number cashAmount =(Number) stringObjectMap.get("cashAmount");
        System.out.println(cashAmount.longValue());
    }

    @Test
    public void testAnyBlank(){
        System.out.println(StringUtils.isAnyBlank("x", "xxx", "xx"));
    }

    @Test
    public void testSwitch(){
        String ss = null;
        System.out.println(ss.equals("xx"));
    }
}
