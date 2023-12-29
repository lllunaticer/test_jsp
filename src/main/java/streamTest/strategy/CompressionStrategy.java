package streamTest.strategy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Xingjian LONG
 * Created on 2021-08-27
 */
public interface CompressionStrategy {
    OutputStream compress(OutputStream data) throws IOException;
}
