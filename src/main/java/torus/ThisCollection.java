package torus;

import java.util.Collection;
import java.util.function.Predicate;

public class ThisCollection {
    public static <T, U extends T> Collection<T> add(Collection<T> collection, U obj) {
        collection.add(obj);
        return collection;
    }

    public static <T, U extends Collection<? extends T>> Collection<T> addAll(Collection<T> collection, U toAdd) {
        collection.addAll(toAdd);
        return collection;
    }

    public static <T> Collection<T> clear(Collection<T> collection) {
        collection.clear();
        return collection;
    }

    public static <T, U extends T> Collection<T> remove(Collection<T> collection, U obj) {
        collection.remove(obj);
        return collection;
    }

    public static <T, U extends Collection<? extends T>> Collection<T> removeAll(Collection<T> collection, U toRemove) {
        collection.removeAll(toRemove);
        return collection;
    }

    public static <T> Collection<T> removeIf(Collection<T> collection, Predicate<? super T> condition) {
        collection.removeIf(condition);
        return collection;
    }

    public static <T, U extends Collection<? extends T>> Collection<T> retainAll(Collection<T> collection, U toRetain) {
        collection.retainAll(toRetain);
        return collection;
    }

    protected ThisCollection() {}
}