package torus;

import java.util.Collection;
import java.util.function.Predicate;

public class ThisCollection {
    public static <T> Collection<T> add(Collection<T> collection, T obj) {
        collection.add(obj);
        return collection;
    }

    public static <T> Collection<T> addAll(Collection<T> collection, Collection<T> toAdd) {
        collection.addAll(toAdd);
        return collection;
    }

    public static <T> Collection<T> clear(Collection<T> collection) {
        collection.clear();
        return collection;
    }

    public static <T> Collection<T> remove(Collection<T> collection, T obj) {
        collection.remove(obj);
        return collection;
    }

    public static <T> Collection<T> removeAll(Collection<T> collection, Collection<T> toRemove) {
        collection.removeAll(toRemove);
        return collection;
    }

    public static <T> Collection<T> removeIf(Collection<T> collection, Predicate<? super T> predicate) {
        collection.removeIf(predicate);
        return collection;
    }

    public static <T> Collection<T> retainAll(Collection<T> collection, Collection<T> toRetain) {
        collection.retainAll(toRetain);
        return collection;
    }

    protected ThisCollection() {}
}