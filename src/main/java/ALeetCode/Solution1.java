package ALeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-10-24
 */
public class Solution1 {
    private static Set<Character> biaodian = new HashSet<>();

    static {
        biaodian.add('.');
        biaodian.add('!');
        biaodian.add(',');
        biaodian.add(' ');
    }

    public int countValidWords(String sentence) {
        String[] sen = sentence.split(" ");
        int cnt = 0;
        for (String s : sen) {
            if (check(s.toCharArray())) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean check(char[] str) {
        int lastIdx = str.length - 1;

        if (lastIdx == 0) {
            return (str[0] >= 'a' && str[0] <= 'z') || biaodian.contains(str[0]);
        }

        if (lastIdx < 0) {
            return false;
        }

        int dashCnt = 0;

        if (!(str[0] >= 'a' && str[0] <= 'z')) {
            return false;
        }

        for (int i = 1; i < str.length - 1; i++) {
            if (str[i] == '-') {
                if (i + 1 < str.length) {
                    if (!(str[i + 1] >= 'a' && str[i + 1] <= 'z') || !(str[i - 1] >= 'a' && str[i - 1] <= 'z')) {
                        return false;
                    }
                }
                dashCnt++;
                if (dashCnt > 1) {
                    return false;
                }
                continue;
            }

            if (!(str[i] >= 'a' && str[i] <= 'z')) {
                return false;
            }
        }

        return (str[lastIdx] >= 'a' && str[lastIdx] <= 'z') || biaodian.contains(str[lastIdx]);
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.countValidWords("62   nvtk0wr4f  8 qt3r! w1ph 1l ,e0d 0n 2v 7c.  n06huu2n9 s9"
                + "   ui4 nsr!d7olr  q-, vqdo!btpmtmui.bb83lf g .!v9-lg 2fyoykex uy5a 8v whvu8 .y sc5 -0n4 zo pfgju "
                + "5u 4 3x,3!wl  fv4   s  aig cf j1 a i  8m5o1  !u n!.1tz87d3 .9    n a3  .xb1p9f  b1i a j8s2 cugf "
                + "l494cx1! hisceovf3 8d93 sg 4r.f1z9w   4- cb r97jo hln3s h2 o .  8dx08as7l!mcmc isa49afk i1 fk,s e "
                + "!1 ln rt2vhu 4ks4zq c w  o- 6  5!.n8ten0 6mk 2k2y3e335,yj  h p3 5 -0  5g1c  tr49, ,qp9 -v p  "
                + "7p4v110926wwr h x wklq u zo 16. !8  u63n0c l3 yckifu 1cgz t.i   lh w xa l,jt   hpi ng-gvtk8 9 j "
                + "u9qfcd!2  kyu42v dmv.cst6i5fo rxhw4wvp2 1 okc8!  z aribcam0  cp-zp,!e x  agj-gb3 !om3934 k "
                + "vnuo056h g7 t-6j! 8w8fncebuj-lq    inzqhw v39,  f e 9. 50 , ru3r  mbuab  6  wz dw79.av2xp . gbmy "
                + "gc s6pi pra4fo9fwq k   j-ppy -3vpf   o k4hy3 -!..5s ,2 k5 j p38dtd   !i   b!fgj,nx qgif"));
    }
}
