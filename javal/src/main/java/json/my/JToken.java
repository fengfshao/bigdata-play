package json.my;

/**
 * Author: shaoff
 * Date: 2020/11/22 16:11
 * Package: json.my
 * Description:
 */
public class JToken {
    JType type;
    String token;

    public JToken(JType type, String token) {
        this.type = type;
        this.token = token;
    }

    public JType getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "JToken{" +
                "type=" + type +
                ", token='" + token + '\'' +
                '}';
    }
}
