package torus;

import java.util.Collection;
import java.util.function.Predicate;

public class ThisCollection<A, B extends Collection<A>> {
    protected final B collection;

    public static <A, B extends Collection<A>> ThisCollection<A, B> beginChain(B collection) {
        return new ThisCollection<>(collection);
    }

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

    protected ThisCollection(B collection) {
        this.collection = collection;
    }

    public B endChain() {
        return collection;
    }

    public <C extends A> ThisCollection<A, B> add(C elementToAdd) {
        add(this.collection, elementToAdd);
        return this;
    }

    public <C extends Collection<? extends A>> ThisCollection<A, B> addAll(C collectionToAdd) {
        addAll(this.collection, collectionToAdd);
        return this;
    }

    public ThisCollection<A, B> clear() {
        clear(this.collection);
        return this;
    }

    public <C extends A> ThisCollection<A, B> remove(C elementToRemove) {
        remove(this.collection, elementToRemove);
        return this;
    }

    public <C extends Collection<? extends A>> ThisCollection<A, B> removeAll(C collectionToRemove) {
        removeAll(this.collection, collectionToRemove);
        return this;
    }

    public ThisCollection<A, B> removeIf(Predicate<? super A> filter) {
        removeIf(this.collection, filter);
        return this;
    }

    public <C extends Collection<? extends A>> ThisCollection<A, B> retainAll(C collectionToRetain) {
        retainAll(this.collection, collectionToRetain);
        return this;
    }
}