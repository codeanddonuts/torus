package torus;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class CurryTest {
    @Test
    void curryingTest2() {
        BiFunction<Integer, String, Integer> f = (a, b) -> a * b.length();
        assertThat(
                f.apply(1, "to")
        ).isEqualTo(2);
        assertThat(
                Curry.convert(f).apply(1).apply("to")
        ).isEqualTo(2);
    }

    @Test
    void curryingTest3() {
        TriFunction<Integer, String, Integer, Integer> f = (a, b, c) -> a * b.length() + c;
        assertThat(
                f.apply(1, "to", 3)
        ).isEqualTo(5);
        assertThat(
                Curry.convert(f).apply(1).apply("to").apply(3)
        ).isEqualTo(5);
    }

    @Test
    void curryingTest4() {
        QuadraFunction<Integer, String, Integer, String, Integer> f = (a, b, c, d) -> a * b.length() + c * d.length();
        assertThat(
                f.apply(1, "to", 3, "from")
        ).isEqualTo(14);
        assertThat(
                Curry.convert(f).apply(1).apply("to").apply(3).apply("from")
        ).isEqualTo(14);
    }

    @Test
    void flipTest2() {
        Function<Integer, Function<Integer, Integer>> f = a -> b -> a - b;
        assertThat(
                f.apply(3).apply(5)
        ).isEqualTo(-2);
        assertThat(
                Curry.flip(f).apply(3).apply(5)
        ).isEqualTo(2);
    }

    @Test
    void flipTest3() {
        Function<Integer, Function<String, Function<Double, Integer>>> f = a -> b -> c -> a - b.length() + c.intValue();
        assertThat(
                f.apply(3).apply("apple").apply(7.8)
        ).isEqualTo(5);
        assertThat(
                Curry.flip(f).apply("apple").apply(3).apply(7.8)
        ).isEqualTo(5);
    }

    @Test
    void flipTest4() {
        Function<Integer, Function<String, Function<Double, Function<Double, Double>>>> f
                = a -> b -> c -> d -> a - b.length() + c.intValue() - d;
        assertThat(
                f.apply(3).apply("apple").apply(7.8).apply(.5)
        ).isEqualTo(4.5);
        assertThat(
                Curry.flip(f).apply("apple").apply(3).apply(7.8).apply(.5)
        ).isEqualTo(4.5);
    }
}