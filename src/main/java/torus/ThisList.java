package torus;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

public class ThisList<A> extends ThisCollection<A> {
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
        return (List<A>) super.collection;
    }

    public <B extends A> ThisList<A> add(int index, B elementToAdd) {
        add((List<A>) super.collection, index, elementToAdd);
        return this;
    }

    public <B extends Collection<? extends A>> ThisList<A> addAll(int index, B collectionToAdd) {
        addAll((List<A>) super.collection, index, collectionToAdd);
        return this;
    }

    public ThisList<A> replaceAll(UnaryOperator<A> operator) {
        replaceAll((List<A>) super.collection, operator);
        return this;
    }

    public ThisList<A> reverse() {
        reverse((List<A>) super.collection);
        return this;
    }

    public <B extends A> ThisList<A> set(int index, B elementToSet) {
        set((List<A>) super.collection, index, elementToSet);
        return this;
    }

    public ThisList<A> shuffle() {
        reverse((List<A>) super.collection);
        return this;
    }

    public ThisList<A> sort() {
        sort((List) super.collection);
        return this;
    }

    public ThisList<A> sort(Comparator<? super A> comparator) {
        sort((List<A>) super.collection, comparator);
        return this;
    }

    public ThisList<A> swap(int i, int j) {
        swap((List<A>) super.collection, i, j);
        return this;
    }
}