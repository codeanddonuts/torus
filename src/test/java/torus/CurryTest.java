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
    void flipTest() {
        Function<Integer, Function<Integer, Integer>> g = a -> b -> a - b;
        assertThat(
                g.apply(3).apply(5)
        ).isEqualTo(-2);
        assertThat(
                Curry.flip(g).apply(3).apply(5)
        ).isEqualTo(2);
    }

    @Test
    void flipCurryingTest() {
        BiFunction<Integer, Integer, Double> h = Math::pow;
        assertThat(
                h.apply(5, 2)
        ).isEqualTo(25.0);
        assertThat(
                Curry.convert(h).apply(5).apply(2)
        ).isEqualTo(25.0);
        assertThat(
                Curry.flipConvert(h).apply(5).apply(2)
        ).isEqualTo(32.0);
    }
}