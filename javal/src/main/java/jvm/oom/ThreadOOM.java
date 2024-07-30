package jvm.oom;

/**
 * Author: shaoff
 * Date: 2020/12/4 11:01
 * -Xss10M
 * Package: jvm.oom
 * Description:
 * Jvm创建线程需要为每个线程分配内存，又叫线程栈，用于进行函数执行，栈大小默认为1M
 * 当内存时不足无法分配时创建线程失败，抛出OOM unable to create native thread
 * 其次，还有可能是由于线程的数量过多，内存还充足，但是线程id先到了最大值，此时需要更改linux配置
 */
public class ThreadOOM {
    static Thread[] ths=new Thread[1024*1024*10];
    static final Runnable target=new Runnable() {
        @Override
        public void run() {
            while (true){
                int i=1;
                try {
                    Thread.sleep(1000*60*60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    public static void main(String[] args) throws Exception {
        for(int i=0;i<ths.length;i++){
            ths[i]=new Thread(target);
            ths[i].start();
//            Thread.sleep(10);
        }
    }
}
