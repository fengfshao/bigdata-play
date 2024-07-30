package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.my.JObject;
import json.my.JToken;
import json.my.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: shaoff
 * Date: 2020/11/22 09:52
 * Package: json
 * Description:
 * 自己解析
 */
public class My {
    private static class PersonalInfo {
        private boolean married;
        private String address;

        public PersonalInfo(boolean married, String address) {
            this.married = married;
            this.address = address;
        }

        public boolean isMarried() {
            return married;
        }

        public void setMarried(boolean married) {
            this.married = married;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    private static class Employee {
        private String id;
        private String name;
        private PersonalInfo personalInfo;
        private Employee(String id, String name,PersonalInfo personalInfo) {
            this.id = id;
            this.name = name;
            this.personalInfo=personalInfo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public PersonalInfo getPersonalInfo() {
            return personalInfo;
        }

        public void setPersonalInfo(PersonalInfo personalInfo) {
            this.personalInfo = personalInfo;
        }
    }

    static Employee oneEmployee() {
        return new Employee("123", "wang", new PersonalInfo(false, "addr1"));
    }


    static void f1() throws Exception {
        Employee employee = oneEmployee();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(employee);

//        List<JToken> tokens = Parser.parseToken(jsonString);
//        System.out.println(tokens);
        JObject jo= Parser.parseObject(jsonString);
        System.out.println(jo);
    }

    public static void main(String[] args) throws Exception {
        f1();
        Map<String, String> a=new HashMap<>();
        a.put("abc", "def");
        System.out.println(a);
    }
}
