package torus;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Curry {
    public static <A, B, C> Function<A, Function<B, C>> convert(BiFunction<A, B, C> function) {
        return a -> b -> function.apply(a, b);
    }

    public static <A, B, C, D> Function<A, Function<B, Function<C, D>>> convert(TriFunction<A, B, C, D> function) {
        return a -> b -> c -> function.apply(a, b, c);
    }

    public static <A, B, C, D, E> Function<A, Function<B, Function<C, Function<D, E>>>> convert(
            QuadraFunction<A, B, C, D, E> function
    ) {
        return a -> b -> c -> d -> function.apply(a, b, c, d);
    }

    public static <A, B, C> Function<B, Function<A, C>> flip(Function<A, Function<B, C>> function) {
        return b -> a -> function.apply(a).apply(b);
    }

    public static <A, B, C> Function<B, Function<A, C>> flipConvert(BiFunction<A, B, C> function) {
        return b -> a -> function.apply(a, b);
    }

    private Curry() {}
}