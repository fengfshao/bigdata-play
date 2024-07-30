package base;

/**
 * Author: shaoff
 * Date: 2020/11/22 08:27
 * Package: base
 * Description:
 * 1.clone方法是object中的方法，保护权限，只能在子类中调用，且需要子类实现Cloneable接口，否则抛出异常
 * 2.clone对象不会执行构造函数
 * 3.clone方法默认是浅拷贝,逐字段拷贝
 */
public class CloneDemo {
    private static class Bean implements Cloneable{
        Bean partner;
        @Override
        public Bean clone() throws CloneNotSupportedException {
            return (Bean) super.clone();
        }

        Bean(){
            System.out.println("init");
        }
    }

    //clone方法需要类即实现Cloneable接口同时重写clone方法
    //因为Cloneable接口是一个标记接口，Object中的clone方法是protected
    //clone对象不会执行构造函数
    protected static void f1() throws CloneNotSupportedException {
        Bean b1=new Bean();
        b1.clone();
    }

    //clone方法默认是浅拷贝
    static void f2() throws CloneNotSupportedException {
        Bean b1=new Bean();
        Bean b2=new Bean();
        b1.partner=b2;
        System.out.println(b1.clone().partner == b2);
    }

    public static void main(String[] args) throws Exception {
        f2();
    }
}
