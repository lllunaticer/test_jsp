package DynamicProxy;

import java.io.File;

import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-20
 */
public class JavaCompiler {
    public static void compile(File javaFile) throws Exception{
        javax.tools.JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable iterable = fileManager.getJavaFileObjects(javaFile);
        CompilationTask task = javaCompiler.getTask(null,fileManager, null, null, null, iterable);
        task.call();
        fileManager.close();
    }
}
