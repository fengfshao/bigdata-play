package util;

import java.util.concurrent.TimeUnit;

/**
 * Author: shaoff
 * Date: 2020/8/13 23:25
 * Package: util
 * Description:
 */
public class ThreadUtil {

    public static void sleepUninterruptible(int duration, TimeUnit unit) {
        long left=unit.toMillis(duration);
        long begin=0;
        boolean interrupted = false;

        try{
            while (left>0){
                try{
                    begin=System.currentTimeMillis();
                    Thread.sleep(left);
                    return;
                }catch (InterruptedException e){
                    left-=(System.currentTimeMillis()-begin);
                    interrupted = true;
                }
            }
        }finally {
            if(interrupted){
                Thread.currentThread().interrupt();
            }
        }


    }
}
