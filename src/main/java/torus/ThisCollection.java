package torus;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ThisCollection<A> {
    protected final Collection<A> collection;

    public static <A> ThisCollection<A> beginChain(Collection<A> collection) {
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

    protected ThisCollection(Collection<A> collection) {
        this.collection = collection;
    }

    public Collection<A> endChain() {
        return collection;
    }

    public <B extends A> ThisCollection<A> add(B elementToAdd) {
        add(this.collection, elementToAdd);
        return this;
    }

    public <B extends Collection<? extends A>> ThisCollection<A> addAll(B collectionToAdd) {
        addAll(this.collection, collectionToAdd);
        return this;
    }

    public ThisCollection<A> clear() {
        clear(this.collection);
        return this;
    }

    public <B  extends A> ThisCollection<A> remove(B elementToRemove) {
        remove(this.collection, elementToRemove);
        return this;
    }

    public <B extends Collection<? extends A>> ThisCollection<A> removeAll(B collectionToRemove) {
        removeAll(this.collection, collectionToRemove);
        return this;
    }

    public ThisCollection<A> removeIf(Predicate<? super A> filter) {
        removeIf(this.collection, filter);
        return this;
    }

    public <B extends Collection<? extends A>> ThisCollection<A> retainAll(B collectionToRetain) {
        retainAll(this.collection, collectionToRetain);
        return this;
    }
}