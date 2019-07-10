package torus;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

public class ThisList extends ThisCollection {
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

    private ThisList() {}
}