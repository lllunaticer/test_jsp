package TestRoaringBitMap;

import org.junit.Test;
import org.roaringbitmap.RoaringBitmap;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-01-03
 */
public class TestRoaringBitMap {
    @Test
    public void testRoaringBitMap() {
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);
        RoaringBitmap rr2 = new RoaringBitmap();
        rr2.add(4000L, 4005L);
        System.out.println("select 3 from rr: " + rr.select(3)); // would return the third value or 1000
        System.out.println("rank of 2 in rr: " + rr.rank(2)); // would return the rank of 2, which is index 1
        System.out.println("rr contains 1000 ? " + rr.contains(1000)); // will return true
        System.out.println("rr contains 7 ? " + rr.contains(7)); // will return false

        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);// new bitmap
        rr.or(rr2); //in-place computation
        boolean equals = rror.equals(rr);// true
        System.out.println("rror equals to rr ? " + equals);
        // number of values stored?
        long cardinality = rr.getLongCardinality();
        System.out.println("numbers of element in rr: "+cardinality);
        System.out.println("all elements in rr:");
        for (int i : rr) {
            System.out.print(i+" ");
        }
    }
}
