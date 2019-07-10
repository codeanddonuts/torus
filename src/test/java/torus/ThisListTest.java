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
    void equalityTest() {
        assertThat(
                testList == ThisList.add(testList, 1)
                && testList == ThisList.add(testList, 2, 0)
                && testList == ThisList.addAll(testList, Arrays.asList(-1, -2, -3))
                && testList == ThisList.addAll(testList, Arrays.asList(-4, -5, -6), 1)
                && testList == ThisList.remove(testList, 0)
                && testList == ThisList.replaceAll(testList, x -> x + 1)
                && testList == ThisList.sort(testList)
                && testList == ThisList.reverse(testList)
        ).isTrue();
    }

    @Test
    void chainingTest() {
        assertThat(
                ThisList.addAll(testList, Arrays.asList(2, 3, 5, 7, 35))
                        .stream().map(x -> x * 2)
                        .collect(Collectors.toList())
        ).isEqualTo(
                Arrays.asList(4, 6, 10, 14, 70)
        );
    }
}