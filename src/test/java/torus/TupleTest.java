package torus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TupleTest {
    @Test
    void mixedContainerTest() {
        assertThat(
                Stream.of(
                        new Pair<>(1, "a"),
                        new Triplet<>(61, "bsf", 3.0),
                        new Triplet<>(33, "banana", 6.5),
                        new Pair<>(161, "apple")
                ).map(Tuple::size)
        ).isEqualTo(
                Arrays.asList(2, 3, 3, 2)
        );


    }
}