package exp;

/**
 * Author: shaoff
 * Date: 2020/12/4 07:24
 * Package: exp
 * Description:
 *
 * https://dzone.com/articles/java-classnotfoundexception-vs-noclassdeffounderro#:~:text=ClassNotFoundException%20is%20an%20exception%20that,was%20missing%20at%20run%20time.
 */

class UserClass{
    static {
        System.out.println("initing");
        int a=1/0;
    }
}
public class NoClassDef {

    public static void main(String[] args) throws Exception {
        Thread.currentThread().getContextClassLoader().loadClass("exp.UserClass");
//        UserClass c=new UserClass();
        Class c;
        try{
            c= Class.forName("exp.UserClass");
        }catch (Exception e){

        }
        c= Class.forName("exp.UserClass");

    }
}
