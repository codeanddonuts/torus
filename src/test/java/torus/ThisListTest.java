package torus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ThisListTest {
    List<Integer> testList;

    @BeforeEach
    void setUp() {
        testList = new ArrayList<>();
    }

    @Test
    void returnThisTest() {
        assertThat(
                ThisList.addAll(testList, Arrays.asList(2, 3, 5, 7, 35))
                        .stream().map(x -> x * 2)
                        .collect(Collectors.toList())
        ).isEqualTo(
                Arrays.asList(4, 6, 10, 14, 70)
        );
    }

    @Test
    void chainingTest() {
        assertThat(
                ThisList.beginChain(testList)
                        .addAll(Arrays.asList(2, 3, 5, 7, 35))
                        .removeIf(x -> x > 5)
                        .add(99)
                        .reverse()
                        .endChain()
                        .stream().map(x -> x * 2)
        ).isEqualTo(
                Arrays.asList(198, 10, 6, 4)
        );
    }
}