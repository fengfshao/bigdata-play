package json;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: shaoff
 * Date: 2020/11/22 10:00
 * Package: json
 * Description:
 */
public class GsonDemo {

    private static class Employee{
        private int id;
        private String name;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static List<Employee> someEmployees(){
        List<Employee> res=new ArrayList<>();
        res.add(new Employee(123,"wa\"ng"));
        res.add(new Employee(456,"lee"));
        return res;
    }

    static void f1(){
        List<Employee> employees =someEmployees();
        Gson gson = new Gson();
        String jsonString = gson.toJson(employees);
        System.out.println(jsonString);
        List<Employee> objectFromJsonString = gson.fromJson(jsonString, List.class);
        System.out.println(objectFromJsonString);
    }


    public static void main(String[] args) {
        f1();
    }
}
