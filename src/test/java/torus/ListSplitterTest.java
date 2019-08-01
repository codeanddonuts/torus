package torus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListSplitterTest {
    @Test
    void of3() {
        assertThat(
            ListSplitter.toChunksOf(
                    3,
                    Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
            )
        ).isEqualTo(
                Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(4, 5, 6),
                        Arrays.asList(7, 8, 9),
                        Arrays.asList(0)
                )
        );
    }

    @Test
    void of4() {
        assertThat(
                ListSplitter.toChunksOf(
                        4,
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
                )
        ).isEqualTo(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4),
                        Arrays.asList(5, 6, 7, 8),
                        Arrays.asList(9, 0)
                )
        );
    }

    @Test
    void mutableArrayTest() {
        List<Integer> src = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        List<List<Integer>> splitted = ListSplitter.toChunksOf(4, src);
        src.set(0, -255);
        assertThat(splitted).isEqualTo(
                Arrays.asList(
                        Arrays.asList(-255, 2, 3, 4),
                        Arrays.asList(5, 6, 7, 8),
                        Arrays.asList(9, 0)
                )
        );
    }

    @Test
    void copiedSplitTest() {
        List<Integer> src = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        List<List<Integer>> splitted = ListSplitter.copiedToChunksOf(4, src);
        src.set(0, -255);
        assertThat(splitted).isEqualTo(
                Arrays.asList(
                        Arrays.asList(1, 2, 3, 4),
                        Arrays.asList(5, 6, 7, 8),
                        Arrays.asList(9, 0)
                )
        );
    }

    @Test
    void sumTest() {
        assertThat(
                ListSplitter.toChunksOf(
                        3,
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
                ).stream().map(chunk ->
                        chunk.stream().reduce(0, Integer::sum)
                )
        ).isEqualTo(
                Arrays.asList(6, 15, 24, 0)
        );
    }
}