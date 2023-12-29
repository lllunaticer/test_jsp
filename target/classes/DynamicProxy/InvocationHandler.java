package DynamicProxy;

import java.lang.reflect.Method;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-20
 */
public interface InvocationHandler {
    void invoke(Object proxy, Method method, Object[] args);
}
