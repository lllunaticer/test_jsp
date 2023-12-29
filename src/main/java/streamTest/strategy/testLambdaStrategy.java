package streamTest.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

/**
 * @author Xingjian LONG
 * Created on 2021-08-27
 */
public class testLambdaStrategy {

    @Test
    public void testStrategy() throws IOException {
        Path inFile = new File("").toPath();
        File outFile = new File("");

        //普通的策略模式，需要额外定义两个类GzipCompressionStrategy和ZipCompressionStrategy
        Compressor gzipCompressor = new Compressor(new GzipCompressionStrategy());
        gzipCompressor.compress(inFile, outFile);
        Compressor zipCompressor = new Compressor(new ZipCompressionStrategy());
        zipCompressor.compress(inFile, outFile);

        // 使用方法引用初始化, 简化代码，不需额外的类
        Compressor gzipCompressorLambda = new Compressor(GZIPOutputStream::new);
        gzipCompressorLambda.compress(inFile, outFile);
        Compressor zipCompressorLambda = new Compressor(ZipOutputStream::new);
        zipCompressorLambda.compress(inFile, outFile);
    }
}
