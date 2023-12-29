package MapTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-23
 */
public class MapDifferenceTest {
    @Test
    public void givenDifferentMaps_whenGetDiffUsingGuava_thenSuccess() {
        Map<String, String> asia1 = new HashMap<String, String>();
        asia1.put("Japan", "Tokyo");
        asia1.put("South Korea", "Seoul");
        asia1.put("India", "New Delhi");

        Map<String, String> asia2 = new HashMap<String, String>();
        asia2.put("Japan", "Tokyo");
        asia2.put("China", "Beijing");
        asia2.put("India", "Delhi");
        String ss = "";

        MapDifference<String, String> diff = Maps.difference(asia1, asia2);
        Map<String, ValueDifference<String>> entriesDiffering = diff.entriesDiffering();

        assertFalse(diff.areEqual());
        assertEquals(1, entriesDiffering.size());
        assertEquals("New Delhi", entriesDiffering.get("India").leftValue());
        assertEquals("Delhi", entriesDiffering.get("India").rightValue());
    }

}
