package generic;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Author: shaoff
 * Date: 2020/9/18 16:31
 * Package: generic
 * Description:
 * <p>
 * 泛型擦除的缺点
 */
public class TypeEraseProblemTest {
    @BeforeClass
    public static void setUp() throws Exception {

    }

    //1.instanceOf无法用于含有泛型的对象
    @Test
    public void testInstanceOf() {
        Object o1 = new ArrayList<String>();
//        assertFalse(o1 instanceof List<Integer>);
    }

    //2.泛型类中的静态方法和静态变量不可以使用泛型类所声明的泛型类型参数
    //可以声明泛型方法指定泛型
    static class A<T> {
//        static T ele;

        /*static T getEle() {
            return null;
        }*/

        static <K> K f1() {
            return null;
        }
    }

    //3.泛型擦出后，反射时无法使用泛型
    static class B{
        static <K> K getInstance() {
            K ins=null;
            //k= k.getClass.newIntance();
            return ins;
        }

        //需要传入class信息
        static <K> K getInstance(Class<K> c) throws Exception {
            K ins= c.newInstance();
            return ins;
        }
    }

    @AfterClass
    public static void tearDown() {

    }
}
