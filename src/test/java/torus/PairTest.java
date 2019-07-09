package torus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class PairTest {
    @Test
    void zipTest() {
        assertThat(
                Pair.zip(
                        IntStream.range(0, 10).boxed(),
                        IntStream.range(0, 8).mapToObj(i -> String.valueOf((char) (i + 'a')))
                ).map(Pair::toString)
        ).isEqualTo(
                Arrays.asList(
                        "(0, a)",
                        "(1, b)",
                        "(2, c)",
                        "(3, d)",
                        "(4, e)",
                        "(5, f)",
                        "(6, g)",
                        "(7, h)"
                )
        );
    }

    @Test
    void zipAndFillTest() {
        assertThat(
                Pair.zipAndFill(
                        IntStream.range(0, 6).boxed(),
                        IntStream.range(0, 10).mapToObj(i -> String.valueOf((char) (i + 'a')))
                ).map(Pair::toString)
        ).isEqualTo(
                Arrays.asList(
                        "(0, a)",
                        "(1, b)",
                        "(2, c)",
                        "(3, d)",
                        "(4, e)",
                        "(5, f)",
                        "(5, g)",
                        "(5, h)",
                        "(5, i)",
                        "(5, j)"
                )
        );
    }

    @Test
    void zipWithIndexTest() {
        assertThat(
                Pair.zipWithIndex(
                        Arrays.asList("apple", "banana", "kiwi")
                ).map(Pair::snd)
        ).isEqualTo(
                Arrays.asList(0, 1, 2)
        );
    }
}