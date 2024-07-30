package jvm.oom;
//import javassist.ClassPool;

/**
 * Author: shaoff
 * Date: 2020/12/4 10:35
 * Package: jvm.oom
 * Description:
 * -XX:MaxMetaSpaceSize=10M
 *
 *  静态属性分配在堆区，无法通过静态变量造成metaspace区的oom
 *  还是需要使用gclib或asm等字节码生成相关的工具模拟
 *
 * https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/memleaks002.html
 */
/*class A{
    static int[] arr1=new int[1024*1024*100];
    static int[] arr2=new int[1024*1024*20];
    static {
        for(int i=0;i<arr1.length;i++){
            arr1[i]=i;
        }
        for(int i=0;i<arr2.length;i++){
            arr2[i]=i;
        }
    }
    void f(){
        arr1[2]=arr2[3];
    }
}*/


public class MetaspaceOOM {


//    static ClassPool classPool = ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000000; i++) {
            //makeClass method - Creates a new class (or interface) from the given class file.
//            Class clas = classPool.makeClass(
//                    i + " outofmemory.OutOfMemoryErrorMetaspace ").toClass();
            //Print name of class loaded
//            System.out.println(clas.getName());
        }
    }
}
