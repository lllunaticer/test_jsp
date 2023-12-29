package enumTest;

import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-04-13
 */
public enum Book {
    GOOD(1, "good"),
    BAD(2, "bad");
    private int code;
    private String name;

    Book(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Book of(String name) {
        return valueOf(name);
    }
}
