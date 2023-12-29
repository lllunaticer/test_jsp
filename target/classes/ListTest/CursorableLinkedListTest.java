package ListTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.list.CursorableLinkedList;
import org.apache.commons.collections4.list.CursorableLinkedList.Cursor;
import org.junit.Test;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-11-16
 */
public class CursorableLinkedListTest {
    @Test
    public void testCursor(){
        CursorableLinkedList<Long> collection = new CursorableLinkedList<>();
        collection.add(1L);
        collection.add(2L);
        collection.add(3L);

        String ss = "xxx";
    }
}
