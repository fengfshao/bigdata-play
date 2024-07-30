package jvm.oom;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: shaoff
 * Date: 2020/8/14 09:24
 * Package: jvm.oom
 * Description:
 * 使用--XX:MaxDirectMemorySize=10M，或-Xmx10M限制直接内存大小
 */
public class DirectMemoryOOM {

    private static final int _1MB=1024*1024;

    static void unsafe() throws IllegalAccessException, InterruptedException{
        Field unsafeField= Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);

        Unsafe unsafe= (Unsafe) unsafeField.get(null);
        int c=100;
        while (true){
            if(c>0){
                unsafe.allocateMemory(_1MB*5);
                c-=1;
            }
            Thread.sleep(100);
            System.out.println("once");
        }
    }

    static void direct() throws InterruptedException {
        List<ByteBuffer> ls = new ArrayList<>();
        while (true){
            ls.add( ByteBuffer.allocateDirect(_1MB));
            Thread.sleep(100);
        }
    }
    public static void main(String[] args) throws Exception  {
        unsafe();
    }

}
