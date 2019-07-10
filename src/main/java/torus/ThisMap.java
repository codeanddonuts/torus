package torus;

import java.util.Map;

public class ThisMap {
    public static <A, B, C extends A, D extends B> Map<A, B> put(Map<A, B> map, C key, D value) {
        map.put(key, value);
        return map;
    }

    private ThisMap() {}
}