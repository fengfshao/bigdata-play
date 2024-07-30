package json.my;

/**
 * Author: shaoff
 * Date: 2020/11/22 16:07
 * Package: json.my
 * Description:
 */
public enum JType {
    BEGIN_OBJECT(1),
    END_OBJECT(2),
    BEGIN_ARRAY(4),
    END_ARRAY(8),
    NULL(16),
    NUMBER(32),
    STRING(64),
    BOOLEAN(128),
    SEP_COLON(256),
    SEP_COMMA(512),
    END_DOCUMENT(1024);

    JType(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }
}