package torus;

import java.util.Collection;
import java.util.function.Predicate;

public class ThisCollection {
    public static <A, B extends Collection<A>, C extends A> B add(B collection, C elementToAdd) {
        collection.add(elementToAdd);
        return collection;
    }

    public static <A, B extends Collection<A>, C extends Collection<? extends A>> B addAll(B collection, C collectionToAdd) {
        collection.addAll(collectionToAdd);
        return collection;
    }

    public static <A, B extends Collection<A>> B clear(B collection) {
        collection.clear();
        return collection;
    }

    public static <A, B extends Collection<A>, C extends A> B remove(B collection, C elementToRemove) {
        collection.remove(elementToRemove);
        return collection;
    }

    public static <A, B extends Collection<A>, C extends Collection<? extends A>> B removeAll(B collection, C collectionToRemove) {
        collection.removeAll(collectionToRemove);
        return collection;
    }

    public static <A, B extends Collection<A>> B removeIf(B collection, Predicate<? super A> filter) {
        collection.removeIf(filter);
        return collection;
    }

    public static <A, B extends Collection<A>, C extends Collection<? extends A>> B retainAll(B collection, C collectionToRetain) {
        collection.retainAll(collectionToRetain);
        return collection;
    }

    protected ThisCollection() {}
}