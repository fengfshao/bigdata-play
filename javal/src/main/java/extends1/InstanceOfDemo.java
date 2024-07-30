package extends1;

/**
 * Author: shaoff
 * Date: 2020/9/11 16:56
 * Package: extends1
 * Description:
 */

public class InstanceOfDemo {
    private static class Base{

    }
    private static class Child extends Base{

    }
    public static void main(String[] args) {
        Base b=new Child();
        boolean isIntance=(b instanceof Child);
        System.out.println(isIntance);
    }
}
