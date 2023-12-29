import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.junit.Test;

import com.kuaishou.framework.config.util.CityHashTailNumberConfig;

import ALeetCode.leecode336.Student;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-12-08
 */
public class UpdateTest {
    private static final AtomicIntegerFieldUpdater<UpdateTest> refCntUpdater =
            AtomicIntegerFieldUpdater.newUpdater(UpdateTest.class, "refCnt");

    @SuppressWarnings("FieldMayBeFinal")
    private volatile int refCnt = 1;

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void testUpdate() {
        refCntUpdater.compareAndSet(this, 1, 2);
        atomicInteger.compareAndSet(0, 1);
        System.out.println(refCnt);
    }

    @Test
    public void testPow() {
        double v = 0.1 * (1 - 0.5) / (1 - Math.pow(0.5, 0));
        System.out.println(v);
    }

    @Test
    public void testTailNumber() {
        CityHashTailNumberConfig mode0 = CityHashTailNumberConfig.from("3;0");
        CityHashTailNumberConfig mode1 = CityHashTailNumberConfig.from("3;1");
        CityHashTailNumberConfig mode2 = CityHashTailNumberConfig.from("3;2");

        System.out.println(mode0.isOnFor(3));
        System.out.println(mode0.isOnFor(4));
        System.out.println(mode0.isOnFor(5));

        System.out.println(mode1.isOnFor(3));
        System.out.println(mode1.isOnFor(4));
        System.out.println(mode1.isOnFor(5));

        System.out.println(mode1.isOnFor(3));
        System.out.println(mode2.isOnFor(4));
        System.out.println(mode2.isOnFor(5));
    }

    @Test
    public void testNumber() {
        double progress = 2999.9999999999995d;
        double threshold = 3000d;
        double percent = progress / threshold;
        System.out.println(percent);
    }


    @Test
    public void testHashCodeInDifferentProcess() {
        Student student = Student.builder()
                .name("xx")
                .addr("xx")
                .t(true)
                .age(10L)
                .build();

        for (int i = 10; i > 0; i--) {
            new Thread(() -> System.out.println(Objects.hashCode(student))).start();
        }
    }
}
