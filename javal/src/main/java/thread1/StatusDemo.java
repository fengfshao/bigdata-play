package thread1;

import util.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * Author: shaoff
 * Date: 2020/8/13 23:55
 * Package: thread1
 * Description:
 * 测试线程的state以及 isAlive方法
 *
 * 线程的5种状态NEW,RUNNABLE,BLOCKED,WAITING,TIMED_WAITING,TERMINATED
 * 当线程执行创建后处于NEW，执行完成后处理TERMINATED
 * isAlive方法只有在NEW和TERMINATED状态都为false
 */
public class StatusDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread th=new Thread(()->{
            ThreadUtil.sleepUninterruptible(3, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getState());
            System.out.println(Thread.currentThread().isAlive());
        });

        /*before start*/
        System.out.println("before start");
        System.out.println(th.getState());
        System.out.println(th.isAlive());

        th.start();
        th.join();

        /*after finish*/
        System.out.println("after finish");
        System.out.println(th.getState());
        System.out.println(th.isAlive());
    }
}
