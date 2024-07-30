package object.interface1;

/**
 * Author: shaoff
 * Date: 2020/4/23 20:33
 * Package: object.interface1
 * Description:
 */

interface Hello1{
     default void abc(){
    }
    void sayHello();
}
interface Hello2{
    void sayHello();
}

class Student implements Hello1,Hello2{
    @Override
    public void sayHello() {
        System.out.println("hello");
    }

    @Override
    public void abc() {

    }
}

public class Demo {
    /*测试实现的接口中有相同的方法*/
    static void testSameInterfaceMethod(){
        Student st=new Student();
        st.sayHello();
        if(st instanceof Hello1){
            System.out.println("abc");
        }
    }
    public static void main(String[] args) {
        testSameInterfaceMethod();
    }
}

