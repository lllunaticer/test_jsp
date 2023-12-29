package streamTest.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author Xingjian LONG
 * Created on 2021-08-27
 */
public class GzipCompressionStrategy implements CompressionStrategy{
    @Override
    public OutputStream compress(OutputStream data) throws IOException {
        return new GZIPOutputStream(data);
    }
}
