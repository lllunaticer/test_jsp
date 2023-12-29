package DynamicProxy;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.lang.model.element.Modifier;

import org.junit.Test;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-19
 * refs: https://www.jianshu.com/p/fc285d669bc5
 */
public class Proxy {
    public static Object newProxyInstance(Class inf, Object client) throws Exception {
        TypeSpec.Builder typeSpecBuild = TypeSpec.classBuilder("TimeProxy")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(inf);

        FieldSpec flyableSpec = FieldSpec.builder(inf, "client", Modifier.PRIVATE).build();
        typeSpecBuild.addField(flyableSpec);

        MethodSpec methodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(inf, "client")
                .addStatement("this.client=client")
                .build();
        typeSpecBuild.addMethod(methodSpec);

        Method[] methods = inf.getDeclaredMethods();
        for (Method method : methods) {
            MethodSpec methodSpec1 = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addStatement("long start = $T.currentTimeMillis()", System.class)
                    .addCode("\n")
                    .addStatement("this.client." + method.getName() + "()")
                    .addCode("\n")
                    .addStatement("long end = $T.currentTimeMillis()", System.class)
                    .addStatement("$T.out.println(\"Fly Time = \" + (end - start))", System.class)
                    .build();
            typeSpecBuild.addMethod(methodSpec1);
        }

        String sourcePath = "/Users/longxingjian/Desktop/";
        JavaFile javaFile = JavaFile.builder("com.longxingjian.proxy", typeSpecBuild.build()).build();
        // 生成代理类的代码
        javaFile.writeTo(new File(sourcePath));

        //编译代理类
        JavaCompiler.compile(new File(sourcePath + "/com/longxingjian/proxy/TimeProxy.java"));

        //运行代理
        URL[] urls = new URL[] {new URL("file:" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("com.longxingjian.proxy.TimeProxy");
        Constructor constructor = clazz.getConstructor(inf);
        return constructor.newInstance(client);
    }

    public static Flyable newProxyInstanceV2(Class inf, InvocationHandler handler) throws Exception {
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxyV2")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(inf);

        FieldSpec fieldSpec = FieldSpec.builder(InvocationHandler.class, "handler", Modifier.PRIVATE).build();
        typeSpecBuilder.addField(fieldSpec);

        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(InvocationHandler.class, "handler")
                .addStatement("this.handler = handler")
                .build();

        typeSpecBuilder.addMethod(constructorMethodSpec);

        Method[] methods = inf.getDeclaredMethods();
        for (Method method : methods) {
            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addCode("try {\n")
                    .addStatement("\t$T method = " + inf.getName() + ".class.getMethod(\"" + method.getName() + "\")",
                            Method.class)
                    // 为了简单起见，这里参数直接写死为空
                    .addStatement("\tthis.handler.invoke(this, method, null)")
                    .addCode("} catch(Exception e) {\n")
                    .addCode("\te.printStackTrace();\n")
                    .addCode("}\n")
                    .build();
            typeSpecBuilder.addMethod(methodSpec);
        }

        JavaFile javaFile = JavaFile.builder("com.longxingjian.proxy", typeSpecBuilder.build()).build();
        // 为了看的更清楚，我将源码文件生成到桌面
        String sourcePath = "/Users/longxingjian_1/Desktop/";
        javaFile.writeTo(new File(sourcePath));

        // 编译
        JavaCompiler.compile(new File(sourcePath + "/com/longxingjian/proxy/TimeProxy.java"));

        // 使用反射load到内存
        URL[] urls = new URL[] {new URL("file:/" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("com.longxingjian.proxy.TimeProxy");
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
        Flyable flyable = (Flyable) constructor.newInstance(handler);

        return flyable;
    }


    public static void main(String[] args) throws Exception {
        //        newProxyInstance();
        Flyable flyable = Proxy.newProxyInstanceV2(Flyable.class, new MyInvocationHandler(new Bird()));
        flyable.fly();
    }

    @Test
    public void testNewProxyInstance() throws Exception {
        Bird bird = new Bird();
        Flyable flyable = (Flyable)Proxy.newProxyInstance(Flyable.class, bird);
        flyable.fly();
    }

    @Test
    public void testClassLoader() throws Exception {
        String sourcePath = "/Users/longxingjian/Desktop/";
        URL[] urls = new URL[] {new URL("file:" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        //        Class clazz = classLoader.loadClass("com.longxingjian.proxy.TimeProxyV2");
        Class<?> clazz = classLoader.loadClass("com.longxingjian.proxy.TimeProxy");
        Constructor constructor = clazz.getConstructor(Flyable.class);
        Flyable flyable = (Flyable) constructor.newInstance(new Bird());
        flyable.fly();
    }

    @Test
    public void testProxy() throws Exception {
        String sourcePath = "/Users/longxingjian/Desktop/";
        URL[] urls = new URL[] {new URL("file:" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class<?> aClass = classLoader.loadClass("com.longxingjian.proxy.TimeProxy");
        Constructor<?> constructor = aClass.getConstructor(Flyable.class);
        Flyable flyable = (Flyable) constructor.newInstance(new Bird());
        flyable.fly();
    }
}
