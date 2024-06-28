package TestTimeUnit;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestTimeUnit {
    @Test
    public void testTimeUnit() {
        System.out.println(TimeUnit.MILLISECONDS.toMillis(1L));
        System.out.println(TimeUnit.MILLISECONDS.toDays(-1L));
    }
}
