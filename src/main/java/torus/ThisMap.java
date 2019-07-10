package torus;

import java.util.Collection;
import java.util.Map;

public class ThisMap<K, V> {
    private final Map<K, V> map;
    
    public static <K, V> ThisMap<K, V> beginChain(Map<K, V> map) {
        return new ThisMap<>(map);
    }

    public static <K, V, _K extends K, _V extends V> Map<K, V> put(Map<K, V> map, _K key, _V value) {
        map.put(key, value);
        return map;
    }

    public static <K, V> Map<K, V> putAll(Map<K, V> map, Map<? extends K, ? extends V> mapToPut) {
        map.putAll(mapToPut);
        return map;
    }

    public static <K, V, A extends Collection<Pair<? extends K, ? extends V>>> Map<K, V> putAll(
            Map<K, V> map,
            A collectionOfPairsToPut
    ) {
        collectionOfPairsToPut.forEach(pair -> map.put(pair.fst(), pair.snd()));
        return map;
    }

    private ThisMap(Map<K, V> map) {
        this.map = map;
    }

    public Map<K, V> endChain() {
        return this.map;
    }

    public <_K extends K, _V extends V> ThisMap<K, V> put(_K key, _V value) {
        put(this.map, key, value);
        return this;
    }

    public ThisMap<K, V> putAll(Map<? extends K, ? extends V> mapToPut) {
        putAll(this.map, mapToPut);
        return this;
    }

    public <A extends Collection<Pair<? extends K, ? extends V>>> ThisMap<K, V> putAll(A collectionOfPairsToPut) {
        putAll(this.map, collectionOfPairsToPut);
        return this;
    }
}