package i18nTest;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;
import com.snack.common.util.SnackResourceBundleUtils;

/**
 * @author Xingjian LONG
 * Created on 2021-06-29
 */
public class i18Test {

    @Test
    public void testI18n() {
        Locale ur = Locale.forLanguageTag("ur");
        Set<String> h0 = Sets.newConcurrentHashSet(Arrays.asList("{0}$", "${0}"));
        Set<String> h1 = Sets.newConcurrentHashSet(Arrays.asList("{1}$", "${1}"));
        Set<String> h2 = Sets.newConcurrentHashSet(Arrays.asList("{2}$", "${2}"));
        Set<String> h3 = Sets.newConcurrentHashSet(Arrays.asList("{3}$", "${3}"));
        Set<String> h4 = Sets.newConcurrentHashSet(Arrays.asList("{4}$", "${4}"));

        String p0 = "{0}$";
        String p1 = "{1}$";
        String p2 = "{2}$";
        String p3 = "{3}$";
        String p4 = "{4}$";

        String k0_0 = "2020_invitation_activity_inviter_coin_withdrawl_push_key4";
        String k0_1 = "2020_invitation_activity_inviter_coin_withdrawl_push_gradient_title3";
        String k0_2 = "2020_invitation_activity_inviter_coin_withdrawl_push_gradient_desc1";

        String k1_0 = "2020_invitation_activity_invitee_bind_push_gradient_title1";

        String k2_0 = "2020_invitation_activity_invitee_bind_push_gradient_desc1";

        String k3_0 = "2020_invitation_activity_invitee_bind_push_gradient_title3";
        String k3_1 = "2020_invitation_activity_inviter_coin_withdrawl_push_gradient_desc3";

        String k4_0 = "2020_invitation_activity_invitee_bind_push_gradient_desc3";
        String k4_1 = "2020_invitation_activity_invitee_bind_push_gradient_title2";
        String k4_2 = "2020_invitation_activity_inviter_coin_withdrawl_push_gradient_desc2";

        Set<String> keys = Sets.newConcurrentHashSet(Arrays.asList(k0_0, k0_1, k0_2));
//        Set<String> keys = Sets.newConcurrentHashSet(Arrays.asList(k2_0));
//        Set<String> keys = Sets.newConcurrentHashSet(Arrays.asList(k3_0, k3_1));
//        Set<String> keys = Sets.newConcurrentHashSet(Arrays.asList(k4_0, k4_1, k4_2));
        for (String key : keys) {
            String message = SnackResourceBundleUtils
                    .getMessage(key, ur);
            System.out.println(message);
            System.out.println(h1.stream().filter(message::contains).map(h -> true).findFirst().orElse(false));
            System.out.println(message.replace(p1, "good"));
            System.out.println("####################");
        }
    }
}
