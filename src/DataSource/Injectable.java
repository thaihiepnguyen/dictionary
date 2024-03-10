package DataSource;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Injectable {
    private static final Map<String, Object> map = new HashMap<>();
    public static Object get(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            try {
                Class<?> myClass = Class.forName(key);
                Object newInstance = myClass.getDeclaredConstructor().newInstance();
                map.put(key, newInstance);
                return newInstance;
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
