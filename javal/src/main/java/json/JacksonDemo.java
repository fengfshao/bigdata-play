package json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: shaoff
 * Date: 2020/11/22 11:00
 * Package: json
 * Description:
 */
public class JacksonDemo {
    private static class Employee{
        private int id;
        private String name;

        private Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Employee() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            System.out.println("called");
            this.name = name;
        }
    }

    static List<Employee> someEmployees(){
        List<Employee> res=new ArrayList<>();
        res.add(new Employee(123,"wang"));
        res.add(new Employee(456,"lee"));
        return res;
    }


    static void f1() throws IOException {
        List<Employee> employees = someEmployees();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(employees);

        System.out.println(jsonString);
        List<Employee> os = objectMapper.readValue(jsonString, new TypeReference<List<Employee>>(){

        });
        System.out.println(os);
        System.out.println(os.get(0).getClass());
    }

    /*parser*/
    static void f2() throws Exception{
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" :51}";

        JsonFactory factory = new JsonFactory();
        JsonParser parser  = factory.createParser(carJson);

        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();

            System.out.println("jsonToken = " + jsonToken);
        }
    }

    public static void main(String[] args) throws Exception {
        f1();
    }
}
