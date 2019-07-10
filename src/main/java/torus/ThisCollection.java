package torus;

import java.util.Collection;
import java.util.function.Predicate;

public class ThisCollection {
    public static <A, B extends Collection<A>, C extends A> B add(B collection, C elementAoAdd) {
        collection.add(elementAoAdd);
        return collection;
    }

    public static <A, B extends Collection<A>, C extends Collection<? extends A>> B addAll(B collection, C collectionAoAdd) {
        collection.addAll(collectionAoAdd);
        return collection;
    }

    public static <A, B extends Collection<A>> B clear(B collection) {
        collection.clear();
        return collection;
    }

    public static <A, B extends Collection<A>, C extends A> B remove(B collection, C toRemove) {
        collection.remove(toRemove);
        return collection;
    }

    public static <A, B extends Collection<A>, C extends Collection<? extends A>> B removeAll(B collection, C toRemove) {
        collection.removeAll(toRemove);
        return collection;
    }

    public static <A, B extends Collection<A>> B removeIf(B collection, Predicate<? super A> condition) {
        collection.removeIf(condition);
        return collection;
    }

    public static <A, B extends Collection<A>, C extends Collection<? extends A>> B retainAll(B collection, C toRetain) {
        collection.retainAll(toRetain);
        return collection;
    }

    protected ThisCollection() {}
}