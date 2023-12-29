import java.util.Arrays;

import org.junit.Test;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-25
 */
public class ClassTest {
    private static final long id = 1000_0000_00_00L;

    public static void main(String[] args) throws NoSuchMethodException {
        ClassTest classTest = new ClassTest();
        classTest.test();
    }

    public void test() throws NoSuchMethodException {
        Process p = this::doApprove;
    }

    public void doApprove() {
    }

    interface Process {
        void process();
    }

    @Test
    public void testGetName() {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass().getSimpleName());
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(Arrays.toString(this.getClass().getClasses()));
        System.out.println(Arrays.toString(this.getClass().getInterfaces()));
        System.out.println(id);
    }

    @Test
    public void testExtend() {
        Student student = (Student) People.initPeople();
        student.setStudentId(1234);
        System.out.println(student.getSimpleName());
    }
}
