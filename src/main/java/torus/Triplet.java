package torus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Triplet<A, B, C> implements Tuple<A, B> {
    private static final int SIZE = 3;

    private final A fst;
    private final B snd;
    private final C trd;

    public static <A, B, C> Stream<Triplet<A, B, C>> zip(List<A> a, List<B> b, List<C> c) {
        return IntStream.range(0, Math.min(Math.min(a.size(), b.size()), c.size()))
                        .mapToObj(i -> new Triplet<>(a.get(i), b.get(i), c.get(i)));
    }

    public static <A, B, C> Stream<Triplet<A, B, C>> zip(List<A> a, List<B> b, Stream<C> c) {
        return zip(a, b, c.collect(Collectors.toList()));
    }

    public static <A, B, C> Stream<Triplet<A, B, C>> zip(List<A> a, Stream<B> b, List<C> c) {
        return zip(a, b.collect(Collectors.toList()), c);
    }

    public static <A, B, C> Stream<Triplet<A, B, C>> zip(Stream<A> a, List<B> b, List<C> c) {
        return zip(a.collect(Collectors.toList()), b, c);
    }

    public static <A, B, C> Stream<Triplet<A, B, C>> zip(Stream<A> a, Stream<B> b, List<C> c) {
        return zip(a.collect(Collectors.toList()), b.collect(Collectors.toList()), c);
    }

    public static <A, B, C> Stream<Triplet<A, B, C>> zip(Stream<A> a, List<B> b, Stream<C> c) {
        return zip(a.collect(Collectors.toList()), b, c.collect(Collectors.toList()));
    }

    public static <A, B, C> Stream<Triplet<A, B, C>> zip(List<A> a, Stream<B> b, Stream<C> c) {
        return zip(a, b.collect(Collectors.toList()), c.collect(Collectors.toList()));
    }

    public static <A, B, C> Stream<Triplet<A, B, C>> zip(Stream<A> a, Stream<B> b, Stream<C> c) {
        return zip(
                a.collect(Collectors.toList()),
                b.collect(Collectors.toList()),
                c.collect(Collectors.toList())
        );
    }

    public static <T> Stream<T> unzip(Stream<Triplet<? extends T, ? extends T, ? extends T>> triplets) {
        return triplets.flatMap(triplet -> Stream.of(triplet.fst, triplet.snd, triplet.trd));
    }

    public static <T> Stream<T> unzip(List<Triplet<? extends T, ? extends T, ? extends T>> triplets) {
        return unzip(triplets.stream());
    }

    public Triplet(A fst, B snd, C trd) {
        this.fst = fst;
        this.snd = snd;
        this.trd = trd;
    }

    public A fst() {
        return this.fst;
    }

    public B snd() {
        return this.snd;
    }

    public C trd() {
        return this.trd;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "(" + this.fst.toString() + ", " + this.snd.toString() + ", " + this.trd.toString() + ")";
    }
}