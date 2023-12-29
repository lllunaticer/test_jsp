import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-15
 */
public class TestGenericInterface {
    private class Food {
        String foodName;
    }

    private interface Eat {
        void eat(String things);
    }

    private interface Eat2<T> {
        void eat(T things);
    }

    private interface Run {
        void run();
    }

    private class Dog implements Eat, Run {
        @Override
        public void run() {
        }

        @Override
        public void eat(String things) {
        }
    }

    private class Cat implements Eat2<Food>, Run {
        @Override
        public void run() {
        }

        @Override
        public void eat(Food things) {
        }
    }

    public static void main(String[] args) {
        Class<?> clazz = Dog.class;
        Type[] genericInterfacesDog = clazz.getGenericInterfaces();
        Class<?>[] interfacesDog = clazz.getInterfaces();

        Class<?> clazz2 = Cat.class;
        Type[] genericInterfacesCat = clazz2.getGenericInterfaces();
//        ParameterizedType type = (ParameterizedType) genericInterfacesCat[0];
//        Type[] actualTypeArguments = type.getActualTypeArguments();
//        Type rawType = type.getRawType();
//        String typeName = type.getTypeName();
//        Type ownerType = type.getOwnerType();
        Class<?>[] interfacesCat = clazz.getInterfaces();

        System.out.println();
    }
}
