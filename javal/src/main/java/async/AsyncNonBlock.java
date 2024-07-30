package async;

import com.google.common.util.concurrent.*;
import util.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: shaoff
 * Date: 2020/8/14 00:46
 * Package: async
 * Description:
 * AsyncBlock中的场景下
 *
 * 使用异步非阻塞，回调的方式实现
 */


public class AsyncNonBlock {

    /*guava包回调机制*/
    static void guavaCallBack(){
        //guava 线程池
        ExecutorService jPool = Executors.newCachedThreadPool();
        ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);

        Callable<Integer> coolingJuiceCall= new CoolingJuiceCall();
        ListenableFuture<Integer> hFuture = gPool.submit(coolingJuiceCall);
        ThreadUtil.sleepUninterruptible(2, TimeUnit.SECONDS);
        System.out.println(hFuture.isDone());
        Futures.<Integer>addCallback(hFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("drink cooled juice");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("routing failed");
            }
        });


    }
    public static void main(String[] args) {
        guavaCallBack();
    }
}
