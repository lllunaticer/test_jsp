import java.util.List;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-01-20
 */
public interface IndexGetter {
    <R> List<R> getIndex(String type);
}
