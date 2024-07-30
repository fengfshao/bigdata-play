package jvm.memory;

import sun.misc.Unsafe;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author: shaoff
 * Date: 2020/5/13 18:16
 * Package: jvm.memory
 * Description:
 */
public class NonHeap {
    static void nonHeap1() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe us = (Unsafe) f.get(null);
        Long addr = us.allocateMemory(1024 * 1024);
//        us.freeMemory(1024);
    }

    static void nonHeap2() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);
    }

    static void mmap() throws Exception{
        FileChannel channel = null;
        try {
            channel = new RandomAccessFile("/Users/sakura/jd-gui-1.6.6.jar", "r").getChannel();
            // Just copy the buffer if it's sufficiently small, as memory mapping has a high overhead.
            ByteBuffer b= channel.map(FileChannel.MapMode.READ_ONLY, 0, 1024 * 1024);
            b.get();
        }catch (Exception e){

        }
        Thread.sleep(1000 * 60 * 10);
    }

    public static void main(String[] args) throws Exception {
//        Unsafe unsafe = Unsafe.getUnsafe();
//        Field f = Unsafe.class.getDeclaredField("theUnsafe");
//        f.setAccessible(true);
//        Unsafe us = (Unsafe) f.get(null);
//        Long addr=us.allocateDirect(1024);
//        us.reallocateMemory(1024, 1024);
//        us.freeMemory(1024);
//        ByteBuffer buffer = ByteBuffer.allocateDirect(1024*10);
        mmap();
        Thread.sleep(1000 * 60 * 10);
    }


}
