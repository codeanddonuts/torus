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
}