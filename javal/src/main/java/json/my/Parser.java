package json.my;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: shaoff
 * Date: 2020/11/22 16:19
 * Package: json.my
 * Description:
 * 先忽略数字和\"
 */
public class Parser {
    public static List<JToken> parseToken(String json) {
//        String[] tokens= json.split("[{}\\[\\]:]");
        List<JToken> res = new ArrayList<>();
        while (!json.isEmpty()) {
            if (json.charAt(0) == '{') {
                res.add(new JToken(JType.BEGIN_OBJECT, "{"));
                json = json.substring(1);
            } else if (json.charAt(0) == '}') {
                res.add(new JToken(JType.END_OBJECT, "}"));
                json = json.substring(1);
            } else if (json.charAt(0) == '[') {
                res.add(new JToken(JType.BEGIN_ARRAY, "["));
                json = json.substring(1);
            } else if (json.charAt(0) == ']') {
                res.add(new JToken(JType.END_ARRAY, "]"));
                json = json.substring(1);
            } else if (json.charAt(0) == ':') {
                res.add(new JToken(JType.SEP_COLON, ":"));
                json = json.substring(1);
            } else if (json.charAt(0) == ',') {
                res.add(new JToken(JType.SEP_COMMA, ","));
                json = json.substring(1);
            } else if (json.startsWith("null")) {
                res.add(new JToken(JType.NULL, "null"));
                json = json.substring(4);
            } else if (json.charAt(0) == '"') {
                int j = json.substring(1).indexOf('"') + 1;
                res.add(new JToken(JType.STRING, json.substring(1, j)));
                json = json.substring(j + 1);
            } else if (json.startsWith("true")) {
                res.add(new JToken(JType.BOOLEAN, "true"));
                json = json.substring(4);
            } else if (json.startsWith("false")) {
                res.add(new JToken(JType.BOOLEAN, "false"));
                json = json.substring(5);
            } else {
//                int j = json.indexOf('"');
////                System.out.println(j);
//                res.add(new JToken(JType.STRING, json.substring(0, j)));
//                json = json.substring(j);
            }
        }
        return res;
    }

    public static JObject parseObject(String json) throws JException {
        List<JToken> tokens = parseToken(json);
        Queue<JToken> q = new LinkedList<>(tokens);
        JToken first = q.poll();
        assert first.type == JType.BEGIN_OBJECT;
        return parseObject0(q);
    }

    static JObject parseObject0(Queue<JToken> q) {
        JObject jo = new JObject();
        String key = null;
        while (!q.isEmpty()) {
            JToken t = q.poll();
            switch (t.type) {
                case BEGIN_OBJECT:
                    assert key != null;
                    jo.put(key, parseObject0(q));
                    key = null;
                    break;
                case END_OBJECT:
                    return jo;
                case NULL:
                    assert key != null;
                    jo.put(key, null);
                    key = null;
                    break;
                case BOOLEAN:
                    assert key != null;
                    jo.put(key, t.token);
                    key = null;
                    break;
                case STRING:
                    if (key == null) {
                        key = t.token;
                    } else {
                        jo.put(key, t.token);
                        key = null;
                    }
                    break;
                case SEP_COLON:
                    break;
                case SEP_COMMA:
                    break;
            }
        }
        return jo;
    }

    /*static Map<Integer,String> parseTest(String j){

    }

    static Map<Integer,String> parseTest0(String j,String[] key){
        if(j.charAt(0)>='0'&&j.charAt(0)<='9'){
            key[0]=
        }
    }*/
}
