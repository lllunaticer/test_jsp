package KconfTest;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

import com.kuaishou.kconf.client.Kconf;
import com.kuaishou.kconf.client.Kconfs;

/**
 * @author Xingjian LONG
 * Created on 2021-06-18
 */
public class KconfTest {
    private static final Kconf<Map<String, Map<String, Integer>>> MAX_COIN_AND_NUMBER_CONFIG =
            Kconfs.ofMapMap("snackServer.activity.inviterAwardBottomConfig",
                    Collections.emptyMap(), String.class, String.class, Integer.class).build();

    @Test
    public void testKconf() {
        Integer orDefault = MAX_COIN_AND_NUMBER_CONFIG.get()
                .getOrDefault("in", Collections.emptyMap())
                .getOrDefault("inviterMaxAward", 0);

        System.out.println("############### - " + orDefault);
    }
}
