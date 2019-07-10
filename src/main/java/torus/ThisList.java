package torus;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

public class ThisList extends ThisCollection {
    public static <T, U extends T> List<T> add(List<T> list, U obj, int index) {
        list.add(index, obj);
        return list;
    }

    public static <T, U extends List<? extends T>> List<T> addAll(List<T> list, U toAdd, int index) {
        list.addAll(index, toAdd);
        return list;
    }

    public static <T> List<T> remove(List<T> list, int index) {
        list.remove(index);
        return list;
    }

    public static <T> List<T> replaceAll(List<T> list, UnaryOperator<T> function) {
        list.replaceAll(function);
        return list;
    }

    public static <T> List<T> reverse(List<T> list) {
        Collections.reverse(list);
        return list;
    }

    public static <T, U extends T> List<T> set(List<T> list, U obj, int index) {
        list.set(index, obj);
        return list;
    }

    public static <T> List<T> shuffle(List<T> list) {
        Collections.shuffle(list);
        return list;
    }

    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        Collections.sort(list);
        return list;
    }

    public static <T> List<T> sort(List<T> list, Comparator<? super T> function) {
        list.sort(function);
        return list;
    }

    private ThisList() {}
}
