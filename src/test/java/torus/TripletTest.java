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
                                Triplet.of(1, 5.9999, 661L),
                                Triplet.of(5, 7.0, 2L),
                                Triplet.of(16, 72.0, 0L),
                                Triplet.of(182, 5.1, 5L),
                                Triplet.of(2, 5.2, 45L),
                                Triplet.of(9, 36.1, 2251L),
                                Triplet.of(854, 613.2, 15L),
                                Triplet.of(1, 7.61, 3L),
                                Triplet.of(7, 2.5, 6L)
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