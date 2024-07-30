package classloading1.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Author: shaoff
 * Date: 2020/9/18 12:34
 * Package: classloading1.test
 * Description:
 */
public class LoadTwoSameDemo {
    private static <T>Class<T> loadFrom(String name,String path) throws ClassNotFoundException {
        ClassLoader myObjectLoader=new ClassLoader(null) {
            @Override
            public Class<?> findClass(String name) {
                try {
                    BlockingQueue q= new LinkedBlockingQueue();
                    InputStream in = new BufferedInputStream(new FileInputStream(path));
                    byte[] bytes=new byte[in.available()];
                    in.read(bytes);
                    return defineClass(name,bytes,0,bytes.length);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        
        return (Class<T>) myObjectLoader.loadClass(name);

    }

    static void testLoadTwo() throws Exception {
        Object b1 = loadFrom("classloading1.test.Bean", "/Users/sakura/stuff/stuff-projects/corel/javal/src/main/java/classloading1/test/Bean1.class").newInstance();
        Object b2 = loadFrom("classloading1.test.Bean", "/Users/sakura/stuff/stuff-projects/corel/javal/src/main/java/classloading1/test/Bean2.class").newInstance();


        assert (!b1.getClass().equals(b2.getClass()));
        int v1= (int) b1.getClass().getMethod("getValue").invoke(b1);
        int v2= (int) b2.getClass().getMethod("getValue").invoke(b2);
        assert v1==1;
        assert v2==2;
    }

    public static void main(String[] args) throws Exception {
        testLoadTwo();
    }
}
