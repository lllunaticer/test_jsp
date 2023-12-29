package testLocale;

import org.junit.Test;

/**
 * @author Xingjian LONG
 * Created on 2021-06-28
 */
public class TestUdr {
    @Test
    public void testudr(){
        String text = "{3}$ دوستوں کو مدعو کرو! \uD83D\uDCB0کماؤ!";
        String s0 = "صرف اتنا قریب ہے {0}$ Rb!";
        String s1 = "تمہارا {1}$ دوست آ گیا!\uD83C\uDF89";
        String s2 = "اب تک تم {2}$ Rb حاصل کر چکے!\uD83D\uDCB0\uD83E\uDD29";
        String s3 = "{3}$ دوستوں کو مدعو کرو! \uD83D\uDCB0کماؤ!";
        String s4 = "{4}$ دوستوں کو مدعو کرو! \uD83D\uDCB0کماؤ!";

        String ss1 = " آپ کو زیادہ سے زیادہ  $] 4[آربی ملیں گے";

        String ss = "آؤ اگر تم {4}$ Rb تک حاصل کرنا چاہتے ہو!\uD83D\uDCB0\uD83D\uDD25";
        String sss = "پیسہ کمانے کا ایک موقع ہے\uD83D\uDD14";
        String kk = "مزید {Rb${0 کمانے سے اپنے پیسے لے سکو\uD83E\uDD29\uD83D\uDCB0";

        String h0 = "{0}$";
        String h1 = "{1}$";
        String h2 = "{2}$";
        String h3 = "{3}$";
        String h4 = "{4}$";

        if(s0.contains(h0)){

        }

        if(s1.contains(h1)){

        }

        if(s2.contains(h2)){

        }

        if(s3.contains(h3)){

        }

        if (s4.contains(h4)){

        }

    }
}
