package async;

import util.ThreadUtil;

import javax.management.relation.RoleUnresolved;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Author: shaoff
 * Date: 2020/8/13 23:11
 * Package: async
 * Description:
 * 借助join实现异步阻塞
 * <p>
 * 模拟场景：
 * 程序员回到家后做三件事：
 * 1.拿出一瓶果汁放入冰箱，冰冻到10度再喝
 * 2.洗澡
 * 3.看新闻
 *
 * 程序员会将冰冻果汁这件事异步地处理，然后去洗澡，喝完冰冻的饮料后看新闻。
 *
 * 这种行为是异步阻塞模式
 * 1.借助join实现
 *
 */


/*class Customer {
    void ordering() {

    }

    void eating() {

    }
}

class Waiter {
    void service() {

    }

    void tellChief() {

    }
}

class Chief {
    void cook() {

    }

    void tellWaiter() {
    }

}*/

interface ProgrammerRoutine{}

class CoolingJuice implements ProgrammerRoutine,Runnable{
    @Override
    public void run() {
        System.out.println("begin cooling");
        ThreadUtil.sleepUninterruptible(10, TimeUnit.SECONDS);
        System.out.println("finish cooling");
    }
}
class Shower implements ProgrammerRoutine, Runnable{
    @Override
    public void run() {
        System.out.println("begin shower");
        ThreadUtil.sleepUninterruptible(3, TimeUnit.SECONDS);
        System.out.println("finish shower");
    }
}

class WatchingNews implements ProgrammerRoutine, Runnable{
    @Override
    public void run() {
        System.out.println("begin watchingNews");
        ThreadUtil.sleepUninterruptible(6, TimeUnit.SECONDS);
        System.out.println("finish watchingNews");
    }
}

class CoolingJuiceCall implements ProgrammerRoutine, Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("begin cooling");
        ThreadUtil.sleepUninterruptible(1, TimeUnit.SECONDS);
        System.out.println("finish cooling");
        return 11; //冰箱有问题，果汁只冻到了11度
    }
}
public class AsyncBlock {

    /*使用join实现异步非阻塞，
    * 当需要等待多个异步线程时，都join上即可，最终等待的时间一定为最大需要等待的时间*/
    static void byJoin() throws InterruptedException {
        /*异步线程中冰冻果汁*/
        Thread coolingThread=new Thread(new CoolingJuice());
        coolingThread.start();

        /*洗澡，等待喝饮料，然后看新闻*/
        new Shower().run();
        coolingThread.join();
        new WatchingNews().run();
    }

    /*join没有提供api判断异步线程的执行结果，可以借助Future实现*/
    static void byFuture() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new CoolingJuiceCall());
        Thread coolingThread=new Thread(futureTask);
        coolingThread.start();

        /*洗澡，等待喝饮料，然后看新闻*/
        new Shower().run();
        int temperature=futureTask.get();
        if(temperature<=10){
            new WatchingNews().run();
        }else{
            System.out.println("饮料不够冰，日常失败");
        }
    }

    public static void main(String[] args) throws Exception {
        byFuture();
    }
}
