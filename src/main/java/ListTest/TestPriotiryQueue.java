package ListTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2024-06-20
 */
public class TestPriotiryQueue {

    @Test
    public void testPriorityQueue() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Comparator<Integer> comparator = (x, y) -> -Integer.compare(x, y);
        PriorityQueue<Integer> pq = new PriorityQueue<>(1, comparator);
        pq.addAll(list);
        while (!pq.isEmpty()){
            System.out.println(pq.poll());
        }
    }
}
