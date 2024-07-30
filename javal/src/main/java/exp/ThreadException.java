package exp;

/**
 * Author: shaoff
 * Date: 2020/12/4 07:16
 * Package: exp
 * Description:
 * 测试异常跨线程抛出
 */
public class ThreadException {
    static volatile Throwable e;
    public static void main(String[] args) {
        Runnable target;
        Thread a=new Thread(()->{
            System.out.println("begin");
            throw new RuntimeException("abc");
//            System.out.println("continue");

        });
        a.setDefaultUncaughtExceptionHandler((t,exception)->{
            e=exception;
        });

        a.start();

    }
}
