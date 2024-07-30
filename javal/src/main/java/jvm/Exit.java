package jvm;

/**
 * Author: shaoff
 * Date: 2020/9/2 16:06
 * Package: jvm
 * Description:
 * jvm退出测试
 */
public class Exit {
    public static void main(String[] args) {
        System.out.println("starting...");
        /*Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName()+":"+e.getMessage());
            }
        });*/

        while (true){
            if("0".equals(""+0)){
                throw new Error("fsf");
            }
            try {
                Thread.sleep(1000*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
//        System.out.println("normal exiting...");
    }
}
