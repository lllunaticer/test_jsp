package streamTest.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author Xingjian LONG
 * Created on 2021-08-27
 */
public class ZipCompressionStrategy implements CompressionStrategy{
    @Override
    public OutputStream compress(OutputStream data) throws IOException {
        return new ZipOutputStream(data);
    }
}
