package torus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TripletTest {
    @Test
    void zipTest() {
        assertThat(
            Triplet.zip(
                    Arrays.asList(1, 2, 3, 4, 5),
                    Arrays.asList("a", "b", "c", "d"),
                    Arrays.asList("가", "나", "다")
            ).map(Triplet::toString)
        ).isEqualTo(
                Arrays.asList(
                        "(1, a, 가)",
                        "(2, b, 나)",
                        "(3, c, 다)"
                )
        );
    }

    @Test
    void unzipTest() {
        assertThat(
                Triplet.unzip(
                        Arrays.asList(
                                new Triplet<>(1, 5.9999, 661L),
                                new Triplet<>(5, 7.0, 2L),
                                new Triplet<>(16, 72.0, 0L),
                                new Triplet<>(182, 5.1, 5L),
                                new Triplet<>(2, 5.2, 45L),
                                new Triplet<>(9, 36.1, 2251L),
                                new Triplet<>(854, 613.2, 15L),
                                new Triplet<>(1, 7.61, 3L),
                                new Triplet<>(7, 2.5, 6L)
                        )
                )
        ).isEqualTo(
                Arrays.asList(
                        1, 5.9999, 661L,
                        5, 7.0, 2L,
                        16, 72.0, 0L,
                        182, 5.1, 5L,
                        2, 5.2, 45L,
                        9, 36.1, 2251L,
                        854, 613.2, 15L,
                        1, 7.61, 3L,
                        7, 2.5, 6L
                )
        );
    }
}