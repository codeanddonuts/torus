package torus;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ThisList<A> extends ThisCollection<A, List<A>> {
    public static <A> ThisList<A> beginChain(List<A> list) {
        return new ThisList<>(list);
    }

    public static <A, B extends A> List<A> add(List<A> list, int index, B elementToAdd) {
        list.add(index, elementToAdd);
        return list;
    }

    public static <A, B extends Collection<? extends A>> List<A> addAll(List<A> list, int index, B collectionToAdd) {
        list.addAll(index, collectionToAdd);
        return list;
    }

    public static <A> List<A> replaceAll(List<A> list, UnaryOperator<A> operator) {
        list.replaceAll(operator);
        return list;
    }

    public static <A> List<A> reverse(List<A> list) {
        Collections.reverse(list);
        return list;
    }

    public static <A, B extends A> List<A> set(List<A> list, int index, B elementToSet) {
        list.set(index, elementToSet);
        return list;
    }

    public static <A> List<A> shuffle(List<A> list) {
        Collections.shuffle(list);
        return list;
    }

    public static <A extends Comparable<A>> List<A> sort(List<A> list) {
        Collections.sort(list);
        return list;
    }

    public static <A> List<A> sort(List<A> list, Comparator<? super A> comparator) {
        list.sort(comparator);
        return list;
    }

    public static <A> List<A> swap(List<A> list, int i, int j) {
        Collections.swap(list, i, j);
        return list;
    }

    private ThisList(List<A> list) {
        super(list);
    }

    @Override
    public List<A> endChain() {
        return super.collection;
    }

    @Override
    public <B extends A> ThisList<A> add(B elementToAdd) {
        return (ThisList<A>) super.add(elementToAdd);
    }

    @Override
    public <B extends Collection<? extends A>> ThisList<A> addAll(B collectionToAdd) {
        return (ThisList<A>) super.addAll(collectionToAdd);
    }

    @Override
    public ThisList<A> clear() {
        return (ThisList<A>) super.clear();
    }

    @Override
    public <B extends A> ThisList<A> remove(B elementToRemove) {
        return (ThisList<A>) super.remove(elementToRemove);
    }

    @Override
    public <B extends Collection<? extends A>> ThisList<A> removeAll(B collectionToRemove) {
        return (ThisList<A>) super.removeAll(collectionToRemove) ;
    }

    @Override
    public ThisList<A> removeIf(Predicate<? super A> filter) {
        return (ThisList<A>) super.removeIf(filter);
    }

    @Override
    public <B extends Collection<? extends A>> ThisList<A> retainAll(B collectionToRetain) {
        return (ThisList<A>) super.retainAll(collectionToRetain);
    }

    public <B extends A> ThisList<A> add(int index, B elementToAdd) {
        add(super.collection, index, elementToAdd);
        return this;
    }

    public <B extends Collection<? extends A>> ThisList<A> addAll(int index, B collectionToAdd) {
        addAll(super.collection, index, collectionToAdd);
        return this;
    }

    public ThisList<A> replaceAll(UnaryOperator<A> operator) {
        replaceAll(super.collection, operator);
        return this;
    }

    public ThisList<A> reverse() {
        reverse(super.collection);
        return this;
    }

    public <B extends A> ThisList<A> set(int index, B elementToSet) {
        set(super.collection, index, elementToSet);
        return this;
    }

    public ThisList<A> shuffle() {
        reverse(super.collection);
        return this;
    }

    public ThisList<A> sort() {
        sort((List) super.collection);
        return this;
    }

    public ThisList<A> sort(Comparator<? super A> comparator) {
        sort(super.collection, comparator);
        return this;
    }

    public ThisList<A> swap(int i, int j) {
        swap(super.collection, i, j);
        return this;
    }
}