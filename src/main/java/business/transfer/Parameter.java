package business.transfer;

import utility.UnavaliableKeyException;

import java.util.HashMap;
import java.util.Map;

public class Parameter {

    private Map<String, Object> dictionary = new HashMap<>();

    public void setValue(String key, Object value) {
        dictionary.put(key, value);
    }

    public Object getValue(String key) {

        Object result = null;

        if (dictionary.containsKey(key)) {
            result = dictionary.get(key);
        } else {
            throw new UnavaliableKeyException();
        }

        return result;
    }

    public boolean containsKey(String key) {
        return dictionary.containsKey(key);
    }
}
