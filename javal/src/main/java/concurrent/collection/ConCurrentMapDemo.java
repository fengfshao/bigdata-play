package concurrent.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

/**
 * Author: shaoff
 * Date: 2020/9/11 18:42
 * Package: concurrent.collection
 * Description:
 */
public class ConCurrentMapDemo {
    private static final ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();

    static void notAtomicallyDemo() throws InterruptedException {
        String k="abc";
        Thread t1=new Thread(()->{
            Integer num=map.getOrDefault(k,0);
            sleep(3, TimeUnit.SECONDS);
            map.put(k, num + 1);
        });

        Thread t2=new Thread(()->{
            Integer num=map.getOrDefault(k,0);
            sleep(3, TimeUnit.SECONDS);
            map.put(k, num + 1);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertEquals(1,map.get(k).intValue());
    }

    static void atomicallyDemo() throws InterruptedException {
        String k="def";
        List<Integer> list= Collections.synchronizedList(new ArrayList<>());
        Thread t1=new Thread(()->{
            Integer res= map.compute(k,(key,value)->{
                int oldValue=value==null?0:value;
                sleep(3, TimeUnit.SECONDS);
                return oldValue+1;
            });
            list.add(res);
        });

        Thread t2=new Thread(()->{
            Integer res= map.compute(k,(key,value)->{
                int oldValue=value==null?0:value;
                sleep(3, TimeUnit.SECONDS);
                return oldValue+1;
            });
            list.add(res);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertEquals(2,map.get(k).intValue());
        assertEquals(Arrays.asList(1,2),list);
    }

    static void sleep(long value, TimeUnit unit){
        try {
            Thread.sleep(unit.toMillis(value));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        notAtomicallyDemo();
    }
}
