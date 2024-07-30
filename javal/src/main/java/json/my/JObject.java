package json.my;

import java.util.*;

/**
 * Author: shaoff
 * Date: 2020/11/22 16:19
 * Package: json.my
 * Description:
 */
public class JObject implements Map<String, Object> {
    private final Map<String,Object> m;

    public JObject() {
        this.m=new LinkedHashMap<>();
    }

    @Override
    public int size() {
        return m.size();
    }

    @Override
    public boolean isEmpty() {
        return m.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return m.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return m.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return m.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return m.put(key,value);
    }

    @Override
    public Object remove(Object key) {
        return m.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        this.m.putAll(m);
    }

    @Override
    public void clear() {
        m.clear();
    }

    @Override
    public Set<String> keySet() {
        return m.keySet();
    }

    @Override
    public Collection<Object> values() {
        return m.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return m.entrySet();
    }

    @Override
    public String toString() {
        return m.toString();
    }
}
