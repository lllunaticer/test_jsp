package URLTest;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.google.common.net.InternetDomainName;

/**
 * @author Xingjian LONG
 * Created on 2021-05-26
 */
public class URLTest {
    @Test
    public void testURLUtils() throws URISyntaxException {
        String url = "https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest";
        URI uri = new URI(url);
        System.out.println("host: " + uri.getHost());
        System.out.println("path: " + uri.getPath());
        System.out.println("query: " + uri.getQuery());
        System.out.println("port: " + uri.getPort());
        InternetDomainName domainName = InternetDomainName.from(uri.getHost()).topPrivateDomain();
        System.out.println(domainName);
    }
}
