package thread1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: shaoff
 * Date: 2020/9/18 11:34
 * Package: thread1
 * Description:
 */
public class FixScheduleTest {

    //测试init delay为0
    public static void testInitDelay0(){
        ScheduledExecutorService executor=Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(()->{
            System.out.println("in calling");
            return;
        },0,100, TimeUnit.MILLISECONDS);
        System.out.println("after calling");
        System.out.println("after calling");
    }

    public static void main(String[] args) {
        testInitDelay0();
    }
}
